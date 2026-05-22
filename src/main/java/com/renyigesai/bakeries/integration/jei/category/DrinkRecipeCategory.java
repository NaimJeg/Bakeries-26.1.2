package com.renyigesai.bakeries.integration.jei.category;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.OvenRecipe;
import com.renyigesai.bakeries.common.recipe.drink.DrinkRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.common.gui.elements.DrawableResource;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DrinkRecipeCategory extends AbstractRecipeCategory<RecipeHolder<DrinkRecipe>> {

    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/jei_drink_gui.png");
    public static final DrawableResource DRAWABLE = new DrawableResource(TEXTURE,0,0,109, 21,0,0,0,0,256,256);

    public DrinkRecipeCategory(IGuiHelper helper) {
        super(BakeriesRecipes.JEI.DRINK, Component.translatable("container.bakeries.drink"), helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BakeriesItems.OVEN.get())), 109, 21);
    }

    @Override
    public void draw(RecipeHolder<DrinkRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        DRAWABLE.draw(guiGraphics);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<DrinkRecipe> recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.value().getInputItems();
        int borderSlotSize = 18;
        //x和y轴的初始坐标，取值为gui贴图的x,y初始位置减一
        int x = 2;
        int y = 2;
        //添加原料槽
        for (int row = 0; row < 4; ++row) {
            if (row < recipeIngredients.size()) {
                builder.addSlot(RecipeIngredientRole.INPUT, x + (row * borderSlotSize) + 1, y)
                        .add(recipeIngredients.get(row));
            }
        }
        //添加输出槽
        builder.addSlot(RecipeIngredientRole.OUTPUT,99,1).add(recipe.value().result());
    }
}
