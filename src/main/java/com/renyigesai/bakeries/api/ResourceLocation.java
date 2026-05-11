package com.renyigesai.bakeries.api;

import net.minecraft.resources.Identifier;

public class ResourceLocation {
    public static Identifier fromNamespaceAndPath(String namespace, String path){
        return Identifier.fromNamespaceAndPath(namespace,path);
    }
}
