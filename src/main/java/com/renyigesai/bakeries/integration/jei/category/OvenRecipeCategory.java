package com.renyigesai.bakeries.integration.jei.category;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.ResourceLocation;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.OvenRecipe;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
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
import java.util.List;
import java.util.Optional;

public class OvenRecipeCategory extends AbstractRecipeCategory<RecipeHolder<OvenRecipe>> {

    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/jei_oven_gui.png");
    public static final DrawableResource DRAWABLE = new DrawableResource(TEXTURE,0,0,58,63,0,0,0,0,256,256);

    public OvenRecipeCategory(IGuiHelper helper) {
        super(BakeriesRecipes.JEI.OVEN, Component.translatable("container.bakeries.oven"), helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BakeriesItems.OVEN.get())), 58, 63);
    }

    @Override
    public void draw(RecipeHolder<OvenRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        DRAWABLE.draw(guiGraphics);
        int cookTime = recipe.value().getCookingTime();
        Minecraft minecraft = Minecraft.getInstance();
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            Component timeString = Component.translatable("gui.jei.category.smelting.time.seconds", new Object[]{cookTimeSeconds});
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            guiGraphics.text(fontRenderer, timeString, 39 - stringWidth, 27, -8355712, false);
        }
        if (mouseX >= 44 && mouseX <= 53 && mouseY >= 7 && mouseY <= 55){
            renderTemperatureTooltip(minecraft,guiGraphics,mouseX,mouseY,recipe.value());
        }
    }

    protected void renderTemperatureTooltip(Minecraft minecraft, GuiGraphicsExtractor gui, double mouseX, double mouseY, OvenRecipe recipe) {
        if (minecraft != null && minecraft.player != null) {
            List<Component> tooltip = new ArrayList<>();
            tooltip.add(Component.literal("Min " + recipe.getMinTemperature()+ "\u00b0C").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Max " + recipe.getMaxTemperature() + "\u00b0C").withStyle(ChatFormatting.BLUE));
            Optional<TooltipComponent> optional = Optional.empty();
            gui.setTooltipForNextFrame(minecraft.font,tooltip, optional,(int) mouseX,(int) mouseY);
        }
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<OvenRecipe> recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 15, 8).add(recipe.value().getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 15, 38).add(recipe.value().getResult().create());
    }
}
