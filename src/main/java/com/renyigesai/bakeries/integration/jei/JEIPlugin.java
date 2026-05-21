package com.renyigesai.bakeries.integration.jei;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.client.gui.blender.BlenderScreen;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.integration.jei.category.BlenderRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

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
        JeiRecipeManager jeiRecipeManager = new JeiRecipeManager();
        registration.addRecipes(BakeriesRecipes.JEI.BLENDER,jeiRecipeManager.getAllBlenderRecipe());
    }

    @Override
    @SuppressWarnings("removal")
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(BakeriesItems.BLENDER,BakeriesRecipes.JEI.BLENDER);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration){
        registration.addRecipeClickArea(BlenderScreen.class, 136, 38, 20, 20, BakeriesRecipes.JEI.BLENDER);
    }
}
