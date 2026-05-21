package com.renyigesai.bakeries.integration.jei.category;

import com.renyigesai.bakeries.common.client.gui.blender.BlenderMenu;
import com.renyigesai.bakeries.common.recipe.blender.BlenderRecipe;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.transfer.*;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class BlenderRecipeTransferHandler implements IRecipeTransferHandler<BlenderMenu, BlenderRecipe> {

    @Override
    public Class<? extends BlenderMenu> getContainerClass() {
        return null;
    }

    @Override
    public Optional<MenuType<BlenderMenu>> getMenuType() {
        return Optional.empty();
    }

    @Override
    public IRecipeType<BlenderRecipe> getRecipeType() {
        return null;
    }

    @Override
    public @Nullable IRecipeTransferError transferRecipe(BlenderMenu blenderMenu, BlenderRecipe blenderRecipe, IRecipeSlotsView iRecipeSlotsView, Player player, boolean b, boolean b1) {
        return null;
    }
}
