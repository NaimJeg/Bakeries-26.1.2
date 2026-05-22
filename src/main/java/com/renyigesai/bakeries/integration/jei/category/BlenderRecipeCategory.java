package com.renyigesai.bakeries.integration.jei.category;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.common.gui.elements.DrawableResource;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

public class BlenderRecipeCategory extends AbstractRecipeCategory<RecipeHolder<BlenderRecipe>> {

    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/jei_blender_gui.png");
    public static final DrawableResource DRAWABLE = new DrawableResource(TEXTURE,0,0,90,69,0,0,0,0,256,256);

    public BlenderRecipeCategory(IGuiHelper helper) {
        super(BakeriesRecipes.JEI.BLENDER, Component.translatable("container.bakeries.blender"), helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BakeriesItems.BLENDER.get())), 90, 69);
    }

    @Override
    public void draw(RecipeHolder<BlenderRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        DRAWABLE.draw(guiGraphics);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<BlenderRecipe> recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.value().getInputItems();
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
