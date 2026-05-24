package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.blocks.fluid.BakeriesFluids;
import com.renyigesai.bakeries.common.client.model.BlenderModel;
import com.renyigesai.bakeries.common.client.model.GlassBreadRackDoorModel;
import com.renyigesai.bakeries.common.client.model.MokaPotModel;
import com.renyigesai.bakeries.common.client.model.OvenModel;
import com.renyigesai.bakeries.common.client.renderer.blockentity.blender.BlenderRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.bread_rack.BreadRackRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.luminous_light_sign.LuminousLightSignRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.mix_block.MixBlockRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.moka_pot.MokaPotRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.oven.OvenRender;
import com.renyigesai.bakeries.common.client.renderer.blockentity.toaster.ToasterRender;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;

import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT,modid = BakeriesMod.MODID)
public class BakeriesClientHandler {
    public static List<RecipeHolder<BlenderRecipe>> BLENDERS;
    @SubscribeEvent
    public static void onRenders(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.MIX_BLOCK_ENTITY.get(), MixBlockRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.BLENDER_ENTITY.get(), BlenderRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.OVEN_ENTITY.get(), OvenRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.BREAD_RACK_ENTITY.get(), BreadRackRender::new);
//        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.TOASTER_ENTITY.get(), ToasterRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.MOKA_POT_ENTITY.get(), MokaPotRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.TOASTER_ENTITY.get(), ToasterRender::new);
//        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.MENU_ENTITY.get(), MenuRender::new);
        event.registerBlockEntityRenderer(BakeriesBlocks.Entities.LUMINOUS_LIGHT_SIGN_ENTITY.get(), LuminousLightSignRender::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BlenderModel.BLENDER, BlenderModel::createBodyLayer);
        event.registerLayerDefinition(OvenModel.OVEN, OvenModel::createBodyLayer);
        event.registerLayerDefinition(GlassBreadRackDoorModel.LAYER_LOCATION, GlassBreadRackDoorModel::createBodyLayer);
        event.registerLayerDefinition(MokaPotModel.LAYER_LOCATION, MokaPotModel::createBodyLayer);
//        event.registerLayerDefinition(GlassBreadRackDoorModel.LAYER_LOCATION, GlassBreadRackDoorModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerFluidModels(RegisterFluidModelsEvent event){
        Material still = new Material(ResourceLocation.fromNamespaceAndPath("bakeries", "block/salt_water_still"));
        Material flow = new Material(ResourceLocation.fromNamespaceAndPath("bakeries", "block/salt_water_flow"));
        FluidModel.Unbaked fluidModel = new FluidModel.Unbaked(still,flow,still,null,null);
        event.register(fluidModel, BakeriesFluids.SALT_WATER,BakeriesFluids.FLOWING_SALT_WATER);
    }
}
