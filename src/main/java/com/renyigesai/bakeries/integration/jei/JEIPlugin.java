package com.renyigesai.bakeries.integration.jei;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.client.gui.blender.BlenderScreen;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import com.renyigesai.bakeries.integration.jei.category.BlenderRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.common.Internal;
import mezz.jei.common.util.ErrorUtil;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.*;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private @Nullable BlenderRecipeCategory blenderCategory;

    public JEIPlugin() {
    }

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(BakeriesMod.MODID,"jei");
    }

    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registration.addRecipeCategories(new BlenderRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeMap clientSyncedRecipes = Internal.getClientSyncedRecipes();
        registration.addRecipes(BakeriesRecipes.JEI.BLENDER,getBlenderRecipes(clientSyncedRecipes,new BlenderRecipeCategory(registration.getJeiHelpers().getGuiHelper())));
//        if (!clientSyncedRecipes.values().isEmpty()) {
//            ErrorUtil.checkNotNull(this.blenderCategory, "blenderCategory");
//
//
//        }
        IModPlugin.super.registerRecipes(registration);
    }

    @Override
    @SuppressWarnings("removal")
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(BakeriesItems.BLENDER,BakeriesRecipes.JEI.BLENDER);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration){
//        registration.addRecipeClickArea(OvenScreen.class, 110, 16, 8, 54, BakeriesRecipeTypes.JEI.OVEN);
        registration.addRecipeClickArea(BlenderScreen.class, 136, 38, 20, 20, BakeriesRecipes.JEI.BLENDER);
//        registration.addRecipeClickArea(FermentationBoxScreen.class, 121, 49, 25, 16, BakeriesRecipeTypes.JEI.FERMENTATION_BOX);
    }

    public List<RecipeHolder<BlenderRecipe>> getBlenderRecipes(RecipeMap clientSyncedRecipes, IRecipeCategory<RecipeHolder<BlenderRecipe>> blenderCategory) {
        return getHandledRecipes(clientSyncedRecipes, BakeriesRecipes.BLENDER_TYPE.get(), blenderCategory);
    }

    public static <C extends RecipeInput, T extends Recipe<C>> List<RecipeHolder<T>> getHandledRecipes(RecipeMap syncedRecipes, RecipeType<T> recipeType, IRecipeCategory<RecipeHolder<T>> recipeCategory) {
        Stream var10000 = syncedRecipes.byType(recipeType).stream();
        Objects.requireNonNull(recipeCategory);
        return var10000.toList();
    }
}
