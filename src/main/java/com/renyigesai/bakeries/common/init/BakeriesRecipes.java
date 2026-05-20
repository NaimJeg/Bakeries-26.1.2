package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.recipe.DoughCraftingTableRecipe;
import com.renyigesai.bakeries.common.recipe.FlourSieveRecipe;
import com.renyigesai.bakeries.common.recipe.OvenRecipe;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import com.renyigesai.bakeries.common.recipe.BreadKnifeRecipe;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BakeriesRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, BakeriesMod.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, BakeriesMod.MODID);

    public static final Supplier<RecipeType<BlenderRecipe>> BLENDER_TYPE = RECIPE_TYPE.register("blender",()-> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID,"blender")));

    public static final Supplier<RecipeSerializer<BlenderRecipe>> BLENDER_SERIALIZER = SERIALIZERS.register("blender", () -> BlenderRecipe.SERIALIZER);

    public static final Supplier<RecipeType<BreadKnifeRecipe>> BREAD_KNIFE_TYPE = RECIPE_TYPE.register("bread_knife",()-> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID,"bread_knife")));

    public static final Supplier<RecipeSerializer<BreadKnifeRecipe>> BREAD_KNIFE_SERIALIZER = SERIALIZERS.register("bread_knife", () -> BreadKnifeRecipe.SERIALIZER);

    public static final Supplier<RecipeType<FlourSieveRecipe>> FLOUR_SIEVE_TYPE = RECIPE_TYPE.register("flour_sieve",()-> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID,"flour_sieve")));

    public static final Supplier<RecipeSerializer<FlourSieveRecipe>> FLOUR_SIEVE_SERIALIZER = SERIALIZERS.register("flour_sieve", () -> FlourSieveRecipe.SERIALIZER);

    public static final Supplier<RecipeType<DoughCraftingTableRecipe>> DOUGH_CRAFTING_TABLE_TYPE = RECIPE_TYPE.register("dough_crafting_table",()-> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID,"dough_crafting_table")));

    public static final Supplier<RecipeSerializer<DoughCraftingTableRecipe>> DOUGH_CRAFTING_TABLE_SERIALIZER = SERIALIZERS.register("dough_crafting_table", () -> DoughCraftingTableRecipe.SERIALIZER);

    public static final Supplier<RecipeType<OvenRecipe>> OVEN_TYPE = RECIPE_TYPE.register("oven",()-> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID,"oven")));

    public static final Supplier<RecipeSerializer<OvenRecipe>> OVEN_SERIALIZER = SERIALIZERS.register("oven", () -> OvenRecipe.SERIALIZER);

    public static void getRegister(IEventBus bus){
        SERIALIZERS.register(bus);
        RECIPE_TYPE.register(bus);
    }

    public static class JEI {
        public static final IRecipeType<RecipeHolder<BlenderRecipe>> BLENDER = IRecipeType.create(BLENDER_TYPE.get());
    }
}
