package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.recipe.BlenderRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BakeriesRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, BakeriesMod.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(Registries.RECIPE_TYPE, BakeriesMod.MODID);

    public static final DeferredHolder<RecipeType<?>,RecipeType<BlenderRecipe>> BLENDER_TYPE = RECIPE_TYPE.register("blender",()-> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID,"blender")));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BlenderRecipe>> BLENDER_SERIALIZER = SERIALIZERS.register("blender", () -> BlenderRecipe.SERIALIZER);

    public static void getRegister(IEventBus bus){
        SERIALIZERS.register(bus);
        RECIPE_TYPE.register(bus);
    }
}
