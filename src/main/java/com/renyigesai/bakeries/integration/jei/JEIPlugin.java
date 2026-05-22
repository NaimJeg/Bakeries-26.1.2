package com.renyigesai.bakeries.integration.jei;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.client.gui.blender.BlenderScreen;
import com.renyigesai.bakeries.common.client.gui.oven.OvenScreen;
import com.renyigesai.bakeries.common.init.BakeriesClientHandler;
import com.renyigesai.bakeries.common.init.BakeriesItems;
import com.renyigesai.bakeries.common.init.BakeriesRecipes;
import com.renyigesai.bakeries.integration.jei.category.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import net.minecraft.resources.Identifier;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

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
        registration.addRecipeCategories(new OvenRecipeCategory(guiHelper));
        registration.addRecipeCategories(new DoughCraftingTableRecipeCategory(guiHelper));
        registration.addRecipeCategories(new BreadKnifeRecipeCategory(guiHelper));
        registration.addRecipeCategories(new DrinkRecipeCategory(guiHelper));
        registration.addRecipeCategories(new FlourSieveRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(BakeriesRecipes.JEI.BLENDER, JeiRecipeManager.BLENDERS);
        registration.addRecipes(BakeriesRecipes.JEI.OVEN, JeiRecipeManager.OVENS);
        registration.addRecipes(BakeriesRecipes.JEI.DOUGH_CRAFTING_TABLE, JeiRecipeManager.DOUGH_CRAFTING_TABLES);
        registration.addRecipes(BakeriesRecipes.JEI.BREAD_KNIFE, JeiRecipeManager.BREAD_KNIFES);
        registration.addRecipes(BakeriesRecipes.JEI.DRINK, JeiRecipeManager.DRINKS);
        registration.addRecipes(BakeriesRecipes.JEI.FLOUR_SIEVE, JeiRecipeManager.FLOUR_SIEVE);
    }

    @Override
    @SuppressWarnings("removal")
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(BakeriesItems.BLENDER,BakeriesRecipes.JEI.BLENDER);
        registration.addRecipeCatalyst(BakeriesItems.OVEN,BakeriesRecipes.JEI.OVEN);
        registration.addRecipeCatalyst(BakeriesItems.DOUGH_CRAFTING_TABLE,BakeriesRecipes.JEI.DOUGH_CRAFTING_TABLE);
        registration.addRecipeCatalyst(BakeriesItems.BREAD_KNIFE,BakeriesRecipes.JEI.BREAD_KNIFE);
        registration.addRecipeCatalyst(BakeriesItems.DRINK_CUP,BakeriesRecipes.JEI.DRINK);
        registration.addRecipeCatalyst(BakeriesItems.FLOUR_SIEVE,BakeriesRecipes.JEI.FLOUR_SIEVE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration){
        registration.addRecipeClickArea(BlenderScreen.class, 136, 38, 20, 20, BakeriesRecipes.JEI.BLENDER);
        registration.addRecipeClickArea(OvenScreen.class, 110, 16, 8, 54, BakeriesRecipes.JEI.OVEN);
    }
}
