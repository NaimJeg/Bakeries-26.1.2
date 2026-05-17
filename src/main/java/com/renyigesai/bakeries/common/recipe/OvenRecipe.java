package com.renyigesai.bakeries.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class OvenRecipe implements Recipe<SingleRecipeInput> {

    private final Ingredient input;
    private final ItemStackTemplate result;
    private final int cookingTime;
    private final int maxTemperature;
    private final int minTemperature;
    private final int perfectTemperature;

    // 构造函数
    public OvenRecipe(
            Ingredient input,
            ItemStackTemplate result,
            int cookingTime,
            int maxTemperature,
            int minTemperature,
            int perfectTemperature
    ) {
        this.input = input;
        this.result = result;
        this.cookingTime = cookingTime;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.perfectTemperature = perfectTemperature;
    }

    // ---------- 字段访问器 ----------
    public Ingredient getInput() {
        return input;
    }

    public ItemStackTemplate getResult() {
        return result;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getPerfectTemperature() {
        return perfectTemperature;
    }

    public boolean isPresentPerfect(){
        return perfectTemperature != -1;
    }


    // ---------- Recipe 接口实现 ----------
    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.input.test(input.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "";
    }


    @Override
    public RecipeSerializer<OvenRecipe> getSerializer() {
        return BakeriesRecipes.OVEN_SERIALIZER.get();
    }

    @Override
    public RecipeType<OvenRecipe> getType() {
        return BakeriesRecipes.OVEN_TYPE.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(this.input);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    // ---------- 静态工厂方法 (用于 Codec 创建) ----------
    public static <T extends OvenRecipe> MapCodec<T> mapCodec(OvenRecipe.Factory<T> factory) {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(OvenRecipe::getInput),
                ItemStackTemplate.CODEC.fieldOf("result").forGetter(OvenRecipe::getResult),
                Codec.INT.fieldOf("time").forGetter(OvenRecipe::getCookingTime),
                Codec.INT.fieldOf("max").forGetter(OvenRecipe::getMaxTemperature),
                Codec.INT.fieldOf("min").forGetter(OvenRecipe::getMinTemperature),
                Codec.INT.optionalFieldOf("perfect",-1).forGetter(OvenRecipe::getPerfectTemperature)
        ).apply(instance, factory::create));
    }

    public static <T extends OvenRecipe> StreamCodec<RegistryFriendlyByteBuf, T> streamCodec(OvenRecipe.Factory<T> factory) {
        return StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC,        OvenRecipe::getInput,
                ItemStackTemplate.STREAM_CODEC,          OvenRecipe::getResult,
                ByteBufCodecs.INT,                       OvenRecipe::getCookingTime,
                ByteBufCodecs.INT,                       OvenRecipe::getMaxTemperature,
                ByteBufCodecs.INT,                       OvenRecipe::getMinTemperature,
                ByteBufCodecs.INT,                       OvenRecipe::getPerfectTemperature,
                factory::create
        );
    }

    @FunctionalInterface
    public interface Factory<T extends OvenRecipe> {
        T create(
                Ingredient input,
                ItemStackTemplate result,
                int cookingTime,
                int maxTemperature,
                int minTemperature,
                int perfectTemperature
        );
    }

    // ---------- 内置的序列化器实例 ----------
    public static final MapCodec<OvenRecipe> CODEC = mapCodec(OvenRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, OvenRecipe> STREAM_CODEC = streamCodec(OvenRecipe::new);
    public static final RecipeSerializer<OvenRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);
}