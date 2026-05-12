package com.renyigesai.bakeries.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;

public abstract class MultiOutputSingleItemRecipe implements Recipe<SingleRecipeInput> {

    protected final Ingredient ingredient;
    protected final NonNullList<ItemStackTemplate> results;  // 改为 ItemStackTemplate
    private final RecipeType<? extends MultiOutputSingleItemRecipe> type;
    private final RecipeSerializer<? extends MultiOutputSingleItemRecipe> serializer;

    public MultiOutputSingleItemRecipe(
            Ingredient ingredient,
            NonNullList<ItemStackTemplate> results,
            RecipeType<? extends MultiOutputSingleItemRecipe> type,
            RecipeSerializer<? extends MultiOutputSingleItemRecipe> serializer
    ) {
        this.ingredient = ingredient;
        this.results = results;
        this.type = type;
        this.serializer = serializer;
    }

    // 返回所有模板（供子类或外部使用）
    public NonNullList<ItemStackTemplate> getAllResults() {
        return results;
    }

    // 将模板转换为 ItemStack（常用辅助方法）
    public NonNullList<ItemStack> getAllResultStacks() {
        NonNullList<ItemStack> stacks = NonNullList.create();
        for (ItemStackTemplate template : results) {
            stacks.add(template.create());
        }
        return stacks;
    }

    // 组装第一个结果
    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return results.isEmpty() ? ItemStack.EMPTY : results.getFirst().create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(this.ingredient);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return serializer;
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return type;
    }

    // ---------- 静态工厂：Codec & StreamCodec ----------
    public static <T extends MultiOutputSingleItemRecipe> MapCodec<T> mapCodec(Factory<T> factory) {
        return RecordCodecBuilder.mapCodec(
                i -> i.group(
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(r -> r.ingredient),
                        ItemStackTemplate.CODEC.listOf().xmap(
                                list -> {
                                    NonNullList<ItemStackTemplate> nonNull = NonNullList.create();
                                    nonNull.addAll(list);
                                    return nonNull;
                                },
                                NonNullList::copyOf
                        ).fieldOf("results").forGetter(r -> r.results)
                ).apply(i, factory::create)
        );
    }

    public static <T extends MultiOutputSingleItemRecipe> StreamCodec<RegistryFriendlyByteBuf, T> streamCodec(Factory<T> factory) {
        return StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC,
                r -> r.ingredient,
                ByteBufCodecs.collection(NonNullList::createWithCapacity, ItemStackTemplate.STREAM_CODEC),
                r -> r.results,
                factory::create
        );
    }

    @FunctionalInterface
    public interface Factory<T extends MultiOutputSingleItemRecipe> {
        T create(Ingredient ingredient, NonNullList<ItemStackTemplate> results);
    }
}