package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.api.event.PlayerLookBlockEvent;
import com.renyigesai.bakeries.common.blocks.fluid.BakeriesFluids;
import com.renyigesai.bakeries.common.client.LookBlockEntityRegistries;
import com.renyigesai.bakeries.common.client.gui.overlay.ILookOverlay;
import com.renyigesai.bakeries.common.client.model.BlenderModel;
import com.renyigesai.bakeries.common.client.model.GlassBreadRackDoorModel;
import com.renyigesai.bakeries.common.client.model.MokaPotModel;
import com.renyigesai.bakeries.common.client.model.OvenModel;
import com.renyigesai.bakeries.common.client.renderer.blockentity.blender.BlenderRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.bread_rack.BreadRackRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.letter_tile.LetterTileRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.luminous_light_sign.LuminousLightSignRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.menu.MenuRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.mix_block.MixBlockRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.moka_pot.MokaPotRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.oven.OvenRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.toaster.ToasterRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(value = Dist.CLIENT,modid = BakeriesMod.MODID)
public class BakeriesClientHandler {
    private static int onPlayerLookBlockTime;
    @SubscribeEvent
    public static void onRenders(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.MIX_BLOCK_ENTITY.get(), MixBlockRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.BLENDER_ENTITY.get(), BlenderRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.OVEN_ENTITY.get(), OvenRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.BREAD_RACK_ENTITY.get(), BreadRackRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.MOKA_POT_ENTITY.get(), MokaPotRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.TOASTER_ENTITY.get(), ToasterRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.MENU_ENTITY.get(), MenuRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.LUMINOUS_LIGHT_SIGN_ENTITY.get(), LuminousLightSignRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.LETTER_TILE_ENTITY.get(), LetterTileRender::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BlenderModel.BLENDER, BlenderModel::createBodyLayer);
        event.registerLayerDefinition(OvenModel.OVEN, OvenModel::createBodyLayer);
        event.registerLayerDefinition(GlassBreadRackDoorModel.LAYER_LOCATION, GlassBreadRackDoorModel::createBodyLayer);
        event.registerLayerDefinition(MokaPotModel.LAYER_LOCATION, MokaPotModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerFluidModels(RegisterFluidModelsEvent event){
        Material still = new Material(ResourceLocation.fromNamespaceAndPath("bakeries", "block/salt_water_still"));
        Material flow = new Material(ResourceLocation.fromNamespaceAndPath("bakeries", "block/salt_water_flow"));
        FluidModel.Unbaked fluidModel = new FluidModel.Unbaked(still,flow,still,null,null);
        event.register(fluidModel, BakeriesFluids.SALT_WATER,BakeriesFluids.FLOWING_SALT_WATER);
    }

    @SubscribeEvent
    public static void onPlayerLookBlock(PlayerTickEvent.Pre event){
        if (onPlayerLookBlockTime == 10) {
            onPlayerLookBlockTime = 0;
            Minecraft mc = Minecraft.getInstance();
            HitResult hitResult = mc.hitResult;
            Level level = event.getEntity().level();
            if (level.isClientSide()) {
                if (hitResult instanceof BlockHitResult blockHitResult) {
                    BlockPos blockPos = blockHitResult.getBlockPos();
                    BlockState blockState = level.getBlockState(blockPos);
                    if (!blockState.isAir()) {
                        NeoForge.EVENT_BUS.post(new PlayerLookBlockEvent(event.getEntity(), blockPos, blockState));
                    }
                }
            }
        }else {
            onPlayerLookBlockTime ++;
        }
    }

    @SubscribeEvent
    public static void renderOverlay(RenderGuiEvent.Post event){
        Minecraft mc = Minecraft.getInstance();
        Player localPlayer = mc.player;
        if (localPlayer == null){
            return;
        }
        BlockPos blockPos = LookBlockEntityRegistries.getBlocks().get(localPlayer.getUUID());
        if (blockPos != null){
            BlockEntity blockEntity = localPlayer.level().getBlockEntity(blockPos);
            if (blockEntity != null){
                ILookOverlay iLookOverlay = LookBlockEntityRegistries.getRegister().get(blockEntity.getClass());
                if (iLookOverlay != null) {
                    if (iLookOverlay.isOverlay(blockEntity,localPlayer,mc)) {
                        iLookOverlay.create(event, blockEntity, localPlayer, mc);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        Player player = event.getPlayer();
        if (player != null) {
            LookBlockEntityRegistries.removeFlag(player);
        }
    }

}
