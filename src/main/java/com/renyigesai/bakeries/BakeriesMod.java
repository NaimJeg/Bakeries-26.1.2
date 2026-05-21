package com.renyigesai.bakeries;

import com.mojang.logging.LogUtils;
import com.renyigesai.bakeries.common.blocks.fluid.BakeriesFluidTypes;
import com.renyigesai.bakeries.common.blocks.fluid.BakeriesFluids;
import com.renyigesai.bakeries.common.init.*;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BakeriesMod.MODID)
public class BakeriesMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "bakeries";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public BakeriesMod(IEventBus modEventBus) {
        BakeriesDataComponents.DATA_COMPONENT_TYPE.register(modEventBus);
        BakeriesMobEffects.EFFECTS.register(modEventBus);
        BakeriesEntityTypes.ENTITY.register(modEventBus);
        BakeriesFluids.REGISTRY.register(modEventBus);
        BakeriesFluidTypes.REGISTRY.register(modEventBus);
        BakeriesItems.REGISTER.register(modEventBus);
        BakeriesBlocks.BLOCKS.register(modEventBus);
        BakeriesBlocks.Entities.REGISTER.register(modEventBus);
        BakeriesRecipes.getRegister(modEventBus);
        BakeriesMenuType.MENU.register(modEventBus);
        BakeriesCreativeModeTabs.REGISTER.register(modEventBus);
        BakeriesSounds.REGISTRY.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
//        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

//        if (Config.logDirtBlock) LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
//        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
//        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
