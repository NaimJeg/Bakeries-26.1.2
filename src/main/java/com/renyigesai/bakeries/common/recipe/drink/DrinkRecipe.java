package com.renyigesai.bakeries.common.recipe.drink;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;

import java.util.ArrayList;
import java.util.List;

public class DrinkRecipe implements Recipe<DrinkInput> {
    public static final MapCodec<DrinkRecipe> MAP_CODEC = drinkMapCodec(DrinkRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, DrinkRecipe> STREAM_CODEC = drinkStreamCodec(DrinkRecipe::new);
    public static final RecipeSerializer<DrinkRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    private final NonNullList<Ingredient> inputItems;
    private final ItemStackTemplate result;

    public DrinkRecipe(NonNullList<Ingredient> ingredients,
                       ItemStackTemplate result) {
        this.inputItems = ingredients;
        this.result = result;
    }


    @Override
    public boolean matches(DrinkInput inv, Level level) {
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < 9; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }
        return i == this.inputItems.size() && RecipeMatcher.findMatches(inputs, this.inputItems) != null;
    }

    @Override
    public ItemStack assemble(DrinkInput input) {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    public NonNullList<Ingredient> getInputItems() {
        return inputItems;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(this.inputItems);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    @Override
    public RecipeType<DrinkRecipe> getType() {
        return BakeriesRecipes.DRINK_TYPE.get();
    }

    public ItemStackTemplate result(){
        return result;
    }

    @Override
    public RecipeSerializer<DrinkRecipe> getSerializer() {
        return BakeriesRecipes.DRINK_SERIALIZER.get();
    }
    public static <T extends DrinkRecipe> MapCodec<T> drinkMapCodec(DrinkRecipe.Factory<T> factory) {
        return RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Ingredient.CODEC.listOf().xmap(
                                list -> {
                                    NonNullList<Ingredient> nonNull = NonNullList.create();
                                    nonNull.addAll(list);
                                    return nonNull;
                                },
                                ArrayList::new
                        ).fieldOf("ingredients").forGetter(DrinkRecipe::getInputItems),
                        ItemStackTemplate.CODEC.fieldOf("result").forGetter(DrinkRecipe::result)
                ).apply(i, factory::create)
        );
    }

    public static <T extends DrinkRecipe> StreamCodec<RegistryFriendlyByteBuf, T> drinkStreamCodec(DrinkRecipe.Factory<T> factory) {
        return StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)),
                DrinkRecipe::getInputItems,
                ItemStackTemplate.STREAM_CODEC,
                DrinkRecipe::result,
                factory::create
        );
    }

    @FunctionalInterface
    public interface Factory<T extends DrinkRecipe> {
        T create(
                NonNullList<Ingredient> ingredients,
                ItemStackTemplate result
        );
    }
}