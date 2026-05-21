
package com.renyigesai.bakeries.common.blocks.fluid;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;

public class BakeriesFluidType extends FluidType {
    public BakeriesFluidType() {
        super(Properties.create().fallDistanceModifier(0F).canExtinguish(true).supportsBoating(true).canHydrate(true).motionScale(0.007D).canConvertToSource(true).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));
    }

//    @Override
//    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
//        consumer.accept(new IClientFluidTypeExtensions() {
//            private static final ResourceLocation STILL_TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID,"block/salt_water_still"), FLOWING_TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID,"block/salt_water_flow");
//
//            @Override
//            public ResourceLocation getStillTexture() {
//                return STILL_TEXTURE;
//            }
//
//            @Override
//            public ResourceLocation getFlowingTexture() {
//                return FLOWING_TEXTURE;
//            }
//        });
//    }
}
