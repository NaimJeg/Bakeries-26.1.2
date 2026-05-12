package com.renyigesai.bakeries.common.items;

import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.BreadKnifeRecipe;
import com.renyigesai.bakeries.common.utils.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class BreadKnifeItem extends Item {

    public BreadKnifeItem(ToolMaterial toolMaterial,String name) {
        super(new Item.Properties().sword(toolMaterial, 0.5F,-0.2F).setId(BakeriesItems.modItemId(name)));
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack hand = pPlayer.getItemInHand(pUsedHand);

        HitResult raytraceresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.NONE);
        if (!(raytraceresult instanceof BlockHitResult)){
            return super.use(pLevel, pPlayer, pUsedHand);
        }

        BlockHitResult ray = (BlockHitResult) raytraceresult;
        Vec3 hitVec = ray.getLocation();
        AABB bb = new AABB(hitVec, hitVec).inflate(1f);
        ItemEntity resultItemEntity = null;
        for (ItemEntity e : pLevel.getEntitiesOfClass(ItemEntity.class, bb)) {
            if (e.getItem().getCount() == 1) {
                resultItemEntity = e;
                break;
            }
        }

        if (resultItemEntity == null){
            return super.use(pLevel, pPlayer, pUsedHand);
        }
        if (!(pLevel instanceof ServerLevel serverLevel)){
            return InteractionResult.SUCCESS;
        }
        double x = resultItemEntity.getX();
        double y = resultItemEntity.getY();
        double z = resultItemEntity.getZ();
        Optional<RecipeHolder<BreadKnifeRecipe>> optional = getBreadKnifeRecipe(serverLevel,resultItemEntity.getItem());
        RandomSource random = RandomSource.create();
        if (optional.isPresent()){
            hand.hurtAndBreak(1,pPlayer,pUsedHand);
            optional.get().value().getAllResults().forEach(item -> {
                ItemStack results = item.create();
                ItemUtils.spawnItemEntity(pLevel, results, x,y,z, new Vec3(0.0,0.0,0.0));
                pLevel.addParticle(new ItemParticleOption(ParticleTypes.ITEM, ItemStackTemplate.fromNonEmptyStack(results)),x,y+0.5,z,((double)random.nextFloat() - 0.5D) * 0.08D, ((double)random.nextFloat() - 0.5D) * 0.08D, ((double)random.nextFloat() - 0.5D) * 0.08D);
            });
            pLevel.playSound(null,new BlockPos((int) x,(int)y,(int)z), SoundEvents.WOOL_BREAK, SoundSource.BLOCKS);
            resultItemEntity.remove(Entity.RemovalReason.KILLED);
        }
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResult.SUCCESS;
    }


    private Optional<RecipeHolder<BreadKnifeRecipe>> getBreadKnifeRecipe(ServerLevel level, ItemStack stack) {
        if (level == null) {
            return Optional.empty();
        }
        return RecipeManager.createCheck(BakeriesRecipes.BREAD_KNIFE_TYPE.get()).getRecipeFor(new SingleRecipeInput(stack),level);
    }

    @Override
    public boolean canDestroyBlock(ItemStack itemStack, BlockState state, Level level, BlockPos pos, LivingEntity user) {
        return user instanceof Player player && !player.isCreative();
    }

    @Override
    public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
        return new ItemStackTemplate(this);
    }

    @Override
    public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
        return !enchantment.is(Enchantments.SWEEPING_EDGE) && super.isPrimaryItemFor(stack, enchantment);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return !enchantment.is(Enchantments.SWEEPING_EDGE) && super.supportsEnchantment(stack, enchantment);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
//    @Override
//    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
//        tooltipComponents.add(Component.translatable(UtilTranslatable.setTooltips(BakeriesMod.MODID, "bread_knife")).withStyle(ChatFormatting.BLUE));
//    }
}
