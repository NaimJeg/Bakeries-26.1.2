package com.renyigesai.bakeries.common.recipe;

import com.mojang.serialization.MapCodec;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class FlourSieveRecipe extends MultiOutputSingleItemRecipe {

    public static final MapCodec<MultiOutputSingleItemRecipe> MAP_CODEC = mapCodec(FlourSieveRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, MultiOutputSingleItemRecipe> STREAM_CODEC = streamCodec(FlourSieveRecipe::new);
    public static final RecipeSerializer<FlourSieveRecipe> SERIALIZER = new RecipeSerializer(MAP_CODEC, STREAM_CODEC);

    public FlourSieveRecipe(Ingredient ingredient, NonNullList<ItemStackTemplate> results) {
        super(ingredient, results, BakeriesRecipes.FLOUR_SIEVE_TYPE.get(), BakeriesRecipes.FLOUR_SIEVE_SERIALIZER.get());
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.item());
    }

}
