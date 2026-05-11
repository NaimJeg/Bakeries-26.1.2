package com.renyigesai.bakeries.common.init;

import com.mojang.serialization.Codec;
import com.renyigesai.bakeries.BakeriesMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class BakeriesDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE,BakeriesMod.MODID);
    public static final Supplier<DataComponentType<Boolean>> PERFECT;

    static {
        PERFECT = DATA_COMPONENT_TYPE.register("perfect", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
    }
}
