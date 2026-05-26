package com.renyigesai.bakeries.common.event;

import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.api.event.AnvilLandingEvent;
import com.renyigesai.bakeries.api.event.PlayerLookBlockEvent;
import com.renyigesai.bakeries.api.items.PileItem;
import com.renyigesai.bakeries.common.client.LookBlockEntityRegistries;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.items.FlourSieveItem;
import com.renyigesai.bakeries.common.utils.WorldUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber
public class BakeriesEvents {
    private static int onPlayerLookBlockTime;

    @SubscribeEvent
    public static void onPileItemUseOn(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        ItemStack handItem = event.getItemStack();
        InteractionHand hand = event.getHand();
        if (level.isClientSide()){
            return;
        }
        if (!player.isShiftKeyDown()){
            return;
        }
        if (!(handItem.getItem() instanceof PileItem pileItem)){
            return;
        }
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
        UseOnContext context = new UseOnContext(level, player, hand, handItem, event.getHitVec());
        InteractionResult result = pileItem.pileUseOn(context);
        if (result == InteractionResult.PASS) {

        }
    }

    @SubscribeEvent
    public static void onFlourSieveUse(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        if (level.isClientSide()){
            return;
        }
        if (player.getOffhandItem().getItem() instanceof FlourSieveItem && player.getMainHandItem().getItem() instanceof BlockItem){
            event.setCanceled(true);
            player.startUsingItem(InteractionHand.OFF_HAND);
        }
    }

    @SubscribeEvent
    public static void onAnvilLanding(AnvilLandingEvent event){
        Entity entity = event.getEntity();
        Level level = event.getLevel();
        List<Entity> entities = level.getEntities(entity, entity.getBoundingBox(), (e) -> e instanceof ItemEntity);
        for (Entity item : entities) {
            if (item instanceof ItemEntity itemEntity && itemEntity.getItem().is(BakeriesItems.CROISSANT.get())){
                double x = itemEntity.getX();
                double y = itemEntity.getY();
                double z = itemEntity.getZ();
                if (itemEntity.getItem().getCount() == 1){
                    itemEntity.remove(Entity.RemovalReason.DISCARDED);
                }else {
                    ItemStack itemStack = new ItemStack(BakeriesItems.CROISSANT.get());
                    itemStack.setCount(itemEntity.getItem().getCount()-1);
                    itemEntity.setItem(itemStack);
                }
                level.addFreshEntity(new ItemEntity(level,x,y,z,new ItemStack(BakeriesItems.FLAT_CROISSANT.get())));
                List<Player> players = level.getEntitiesOfClass(Player.class,entity.getBoundingBox().inflate(6));
                players.forEach(player -> {
                    if (!WorldUtils.isDoneAdvancement(player,level, ResourceLocation.fromNamespaceAndPath("bakeries","get_flat_croissant"))){
                        if (player instanceof ServerPlayer serverPlayer){
                            AdvancementHolder advancement = serverPlayer.server.getAdvancements().get(ResourceLocation.fromNamespaceAndPath("bakeries","get_flat_croissant"));
                            if (advancement != null) {
                                serverPlayer.getAdvancements().award(advancement, "witness_anvil_fall");
                            }
                        }
                    }
                });
                break;
            }
        }
    }

    @SubscribeEvent
    public static void onLookBlock(PlayerLookBlockEvent event){
        Level level = event.getPlayer().level();
        Player player = event.getPlayer();
        BlockPos blockPos = event.getBlockPos();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity != null){
            LookBlockEntityRegistries.setBlocks(player,blockEntity);
            return;
        }
        Map<UUID, BlockEntity> blocks = LookBlockEntityRegistries.getBlocks();
        if (blocks.get(player.getUUID()) != null){
            blocks.remove(player.getUUID());
        }
    }


    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event){
        event.sendRecipes(
                BakeriesRecipes.BLENDER_TYPE.get(),
                BakeriesRecipes.DOUGH_CRAFTING_TABLE_TYPE.get(),
                BakeriesRecipes.OVEN_TYPE.get(),
                BakeriesRecipes.BREAD_KNIFE_TYPE.get(),
                BakeriesRecipes.DRINK_TYPE.get(),
                BakeriesRecipes.FLOUR_SIEVE_TYPE.get()
        );
    }
}
