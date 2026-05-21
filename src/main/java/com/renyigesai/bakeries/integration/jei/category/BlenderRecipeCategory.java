package com.renyigesai.bakeries.integration.jei.category;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import org.jspecify.annotations.Nullable;

public class BlenderRecipeCategory implements IRecipeCategory<RecipeHolder<BlenderRecipe>> {

    public final static Identifier UID = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "blender");
    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/jei_blender_gui.png");
    private final IDrawable icon;

    public BlenderRecipeCategory(IGuiHelper helper) {
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BakeriesItems.BLENDER.get()));
    }

    @Override
    public IRecipeType<RecipeHolder<BlenderRecipe>> getRecipeType() {
        return BakeriesRecipes.JEI.BLENDER;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Blender");
    }

    @Override
    public int getWidth() {
        return 90;
    }

    @Override
    public int getHeight() {
        return 69;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<BlenderRecipe> recipe, IFocusGroup focuses) {
        BakeriesMod.LOGGER.debug("setRecipe");
        NonNullList<Ingredient> recipeIngredients = recipe.value().getInputItems();
        BlenderRecipe value = recipe.value();
        RecipeDisplay display = value.display().getFirst();
        int borderSlotSize = 18;
        //x��y��ĳ�ʼ���꣬ȡֵΪgui��ͼ��x,y��ʼλ�ü�һ
        int x = 4;
        int y = 7;
        //���ԭ�ϲ�
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipeIngredients.size()) {
                    builder.addSlot(RecipeIngredientRole.INPUT,x + (column * borderSlotSize) + 1, y + (row * borderSlotSize) + 1).add(recipeIngredients.get(inputIndex));
                }
            }
        }
        //���������
        if (recipe.value().getContainer() != null){
            builder.addSlot(RecipeIngredientRole.CRAFTING_STATION,67,8).add(recipe.value().getContainer());
        }

        //��������
        builder.addSlot(RecipeIngredientRole.OUTPUT,67,43).add(recipe.value().result().create());
    }
}
