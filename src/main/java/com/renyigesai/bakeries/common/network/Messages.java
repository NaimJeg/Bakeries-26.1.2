package com.renyigesai.bakeries.common.network;


import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.network.payload.OvenButtonPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


@EventBusSubscriber
public class Messages {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(BakeriesMod.MODID).versioned("1.0");


        registrar.playToServer(OvenButtonPayload.TYPE, OvenButtonPayload.STREAM_CODEC, OvenButtonPayload::handle);
    }

}