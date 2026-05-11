package com.renyigesai.bakeries.common.recipe;

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

public class BlenderRecipe implements Recipe<BlenderInput> {
    public static final MapCodec<BlenderRecipe> MAP_CODEC = blenderMapCodec(BlenderRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, BlenderRecipe> STREAM_CODEC = blenderStreamCodec(BlenderRecipe::new);
    public static final RecipeSerializer<BlenderRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    private final NonNullList<Ingredient> inputItems;
    private final ItemStackTemplate result;
    private final ItemStack container;

    public BlenderRecipe(NonNullList<Ingredient> ingredients,
                         ItemStackTemplate result, ItemStack container) {
        this.inputItems = ingredients;
        this.result = result;
        this.container = container.isEmpty() ? ItemStack.EMPTY : container;
    }


    @Override
    public boolean matches(BlenderInput inv, Level level) {
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
    public ItemStack assemble(BlenderInput input) {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    public ItemStack getContainer() {
        return container.copy();
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
    public RecipeType<BlenderRecipe> getType() {
        return BakeriesRecipes.BLENDER_TYPE.get();
    }

    public ItemStackTemplate result(){
        return result;
    }

    @Override
    public RecipeSerializer<BlenderRecipe> getSerializer() {
        return SERIALIZER;
    }
    public static <T extends BlenderRecipe> MapCodec<T> blenderMapCodec(BlenderRecipe.Factory<T> factory) {
        return RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Ingredient.CODEC.listOf().xmap(
                                list -> {
                                    NonNullList<Ingredient> nonNull = NonNullList.create();
                                    nonNull.addAll(list);
                                    return nonNull;
                                },
                                ArrayList::new
                        ).fieldOf("ingredients").forGetter(BlenderRecipe::getInputItems),
                        ItemStackTemplate.CODEC.fieldOf("output").forGetter(BlenderRecipe::result),
                        ItemStack.CODEC.fieldOf("container").forGetter(BlenderRecipe::getContainer)
                ).apply(i, factory::create)
        );
    }

    public static <T extends BlenderRecipe> StreamCodec<RegistryFriendlyByteBuf, T> blenderStreamCodec(BlenderRecipe.Factory<T> factory) {
        return StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)),
                BlenderRecipe::getInputItems,
                ItemStackTemplate.STREAM_CODEC,
                BlenderRecipe::result,
                ItemStack.OPTIONAL_STREAM_CODEC,
                BlenderRecipe::getContainer,
                factory::create
        );
    }

    @FunctionalInterface
    public interface Factory<T extends BlenderRecipe> {
        T create(
                NonNullList<Ingredient> ingredients,
                ItemStackTemplate result,
                ItemStack container
        );
    }
}