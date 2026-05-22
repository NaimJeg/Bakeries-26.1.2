package com.renyigesai.bakeries.integration.jei.category;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.DoughCraftingTableRecipe;
import com.renyigesai.bakeries.common.recipe.OvenRecipe;
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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoughCraftingTableRecipeCategory extends AbstractRecipeCategory<RecipeHolder<DoughCraftingTableRecipe>> {

    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/jei_dough_crafting_table_gui.png");
    public static final DrawableResource DRAWABLE = new DrawableResource(TEXTURE,0,0,98, 46,0,0,0,0,256,256);

    public DoughCraftingTableRecipeCategory(IGuiHelper helper) {
        super(BakeriesRecipes.JEI.DOUGH_CRAFTING_TABLE, Component.translatable("container.bakeries.dough_crafting_table"), helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BakeriesItems.DOUGH_CRAFTING_TABLE.get())), 98, 46);
    }

    @Override
    public void draw(RecipeHolder<DoughCraftingTableRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        DRAWABLE.draw(guiGraphics);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<DoughCraftingTableRecipe> recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 12,15).add(recipe.value().input());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 66, 15).add(recipe.value().resultDisplay());
    }
}
