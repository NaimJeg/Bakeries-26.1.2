package com.renyigesai.bakeries.common.recipe;

import com.mojang.serialization.MapCodec;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class BreadKnifeRecipe extends MultiOutputSingleItemRecipe {
    public static final MapCodec<MultiOutputSingleItemRecipe> MAP_CODEC = mapCodec(BreadKnifeRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, MultiOutputSingleItemRecipe> STREAM_CODEC = streamCodec(BreadKnifeRecipe::new);
    public static final RecipeSerializer<BreadKnifeRecipe> SERIALIZER = new RecipeSerializer(MAP_CODEC, STREAM_CODEC);
    public BreadKnifeRecipe(Ingredient ingredient, NonNullList<ItemStackTemplate> results) {
        super(ingredient, results, BakeriesRecipes.BREAD_KNIFE_TYPE.get(), BakeriesRecipes.BREAD_KNIFE_SERIALIZER.get());
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.item());
    }
}
