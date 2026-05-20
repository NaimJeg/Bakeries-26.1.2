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
import org.jetbrains.annotations.Nullable;

public class BlenderRecipeCategory extends AbstractRecipeCategory<RecipeHolder<BlenderRecipe>> {

    public final static Identifier UID = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "blender");
    public static final Identifier TEXTURE = ResourceLocation.fromNamespaceAndPath(BakeriesMod.MODID, "textures/gui/jei_blender_gui.png");

//    public final IDrawable back;
//    public final IDrawable icon;



    public BlenderRecipeCategory(IGuiHelper helper) {
        super(BakeriesRecipes.JEI.BLENDER, Component.translatable(""), helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BakeriesItems.BLENDER.get())), 90, 69);
    }


//    public BlenderRecipeCategory(IGuiHelper helper) {
//        super();
//        this.back = helper.createDrawable(TEXTURE,0, 0, 90, 69);
//        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(BakeriesItems.BLENDER.get()));
//
//    }



    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<BlenderRecipe> recipe, IFocusGroup focuses) {
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
                    builder.addInputSlot(x + (column * borderSlotSize) + 1, y + (row * borderSlotSize) + 1).add(recipeIngredients.get(inputIndex));
                }
            }
        }
        //���������
        builder.addSlot(RecipeIngredientRole.INPUT,67,8).add(recipe.value().getContainer());
        //��������
        builder.addSlot(RecipeIngredientRole.OUTPUT,67,43).add(recipe.value().result().create());
    }
}
