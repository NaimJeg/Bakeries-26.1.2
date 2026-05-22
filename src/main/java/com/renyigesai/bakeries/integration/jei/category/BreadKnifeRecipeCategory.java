package com.renyigesai.bakeries.integration.jei.category;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.BreadKnifeRecipe;
import com.renyigesai.bakeries.common.recipe.DoughCraftingTableRecipe;
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
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.RecipeHolder;

public class BreadKnifeRecipeCategory extends AbstractRecipeCategory<RecipeHolder<BreadKnifeRecipe>> {

    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/jei_single_recipe.png");
    public static final DrawableResource DRAWABLE = new DrawableResource(TEXTURE,0,0,109, 21,0,0,0,0,256,256);

    public BreadKnifeRecipeCategory(IGuiHelper helper) {
        super(BakeriesRecipes.JEI.BREAD_KNIFE, Component.translatable("container.bakeries.bread_knife"), helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BakeriesItems.BREAD_KNIFE.get())), 109, 21);
    }

    @Override
    public void draw(RecipeHolder<BreadKnifeRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        DRAWABLE.draw(guiGraphics);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<BreadKnifeRecipe> recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 26,2).add(recipe.value().getIngredient());
        NonNullList<ItemStackTemplate> allResults = recipe.value().getAllResults();
        for (int i = 0; i < allResults.size(); i++) {
            builder.addSlot(RecipeIngredientRole.OUTPUT,74 + (i * 16),2).add(allResults.get(i));
        }
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY,3,3).add(new ItemStack(BakeriesItems.BREAD_KNIFE.get()));
    }
}
