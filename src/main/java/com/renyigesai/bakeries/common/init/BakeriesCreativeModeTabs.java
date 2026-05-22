package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class BakeriesCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,BakeriesMod.MODID);
    public static final Supplier<CreativeModeTab> BAKERIES_TAB = REGISTER.register(
            "0_bakeries_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.bakeries.bakeries_tab"))
                    .icon(() -> BakeriesItems.BAGEL.get().getDefaultInstance())
                    .displayItems((_, output) -> {

                        output.accept(BakeriesItems.BLENDER);
                        output.accept(BakeriesItems.DOUGH_CRAFTING_TABLE);
                        output.accept(BakeriesItems.CUPBOARD);
                        output.accept(BakeriesItems.OVEN);
                        output.accept(BakeriesItems.MOKA_POT);
                        output.accept(BakeriesItems.MOKA_POT_FILL);

                        output.accept(BakeriesItems.FERMENTATION_TANK);
                        output.accept(BakeriesItems.YEAST_TANK);
                        output.accept(BakeriesItems.MILK_TANK);
                        output.accept(BakeriesItems.CHEESE_TANK);

                        output.accept(BakeriesItems.DRINK_CUP);

                        output.accept(BakeriesItems.WOOD_TRAY);
                        output.accept(BakeriesItems.WOOD_COUNTER);
                        output.accept(BakeriesItems.COFFEE_TABLE);
                        output.accept(BakeriesItems.BREAD_RACK);
                        output.accept(BakeriesItems.GLASS_BREAD_RACK);

                        output.accept(BakeriesItems.SOFA_WHITE);
                        output.accept(BakeriesItems.SOFA_RED);
                        output.accept(BakeriesItems.SOFA_LIGHT_GRAY);

                        output.accept(BakeriesItems.BREAD_KNIFE);
                        output.accept(BakeriesItems.FLOUR_SIEVE);

                        output.accept(BakeriesItems.WHOLE_WHEAT_FLOUR);
                        output.accept(BakeriesItems.FLOUR);
                        output.accept(BakeriesItems.COCOA_POWDER);
                        output.accept(BakeriesItems.MATCHA_POWDER);

                        output.accept(BakeriesItems.OLIVE_OIL);
                        output.accept(BakeriesItems.BEARNAISE);
                        output.accept(BakeriesItems.MEAT_FLOSS);

                        output.accept(BakeriesItems.FOAMED_CREAM);
                        output.accept(BakeriesItems.CHEESE_CREAM);
                        output.accept(BakeriesItems.BUTTER_FLOUR_SAND);
                        output.accept(BakeriesItems.HONEY_BUTTER);
                        output.accept(BakeriesItems.WHOLE_EGG);
                        output.accept(BakeriesItems.RAW_PROTEIN);
                        output.accept(BakeriesItems.RAW_EGG_YOLK);
                        output.accept(BakeriesItems.SALT_YOLK);

                        output.accept(BakeriesItems.CHEESE_CUBE);
                        output.accept(BakeriesItems.FRESH_CHEESE_CUBE);
                        output.accept(BakeriesItems.BROWN_SUGAR_CUBE);
                        output.accept(BakeriesItems.RAW_COFFEE_BEAN);
                        output.accept(BakeriesItems.COFFEE_BEAN);
                        output.accept(BakeriesItems.GROUND_COFFEE);
                        output.accept(BakeriesItems.TOMATO);
                        output.accept(BakeriesItems.OLIVE);
                        output.accept(BakeriesItems.TARO);
                        output.accept(BakeriesItems.COOKED_TARO);
                        output.accept(BakeriesItems.MASHED_TARO);

                        output.accept(BakeriesItems.SALT);
                        output.accept(BakeriesItems.RAW_SALT_BLOCK);
                        output.accept(BakeriesItems.SALT_WATER_BUCKET);
                        output.accept(BakeriesItems.BOTTLE_YEAST);

                        output.accept(BakeriesItems.BOTTLE_MILK);
                        output.accept(BakeriesItems.BOTTLE_CREAM);
                        output.accept(BakeriesItems.BOTTLE_BUTTER);
                        output.accept(BakeriesItems.BUTTER_CUBE);

                        output.accept(BakeriesItems.BAGEL);
                        output.accept(BakeriesItems.WHOLE_WHEAT_BAGEL);
                        output.accept(BakeriesItems.ROUND_BREAD);
                        output.accept(BakeriesItems.BERRY_BREAD);
                        output.accept(BakeriesItems.CHEESE_CREAM_BREAD);
                        output.accept(BakeriesItems.BROWN_SUGAR_ROLL);
                        output.accept(BakeriesItems.PINEAPPLE_BUN);
                        output.accept(BakeriesItems.PINEAPPLE_OIL);
                        output.accept(BakeriesItems.MEAT_FLOSS_BREAD_ROLL);
                        output.accept(BakeriesItems.CROISSANT);
                        output.accept(BakeriesItems.DIRTY_CHOCO_CROISSANT);
                        output.accept(BakeriesItems.SALT_CROISSANT);
                        output.accept(BakeriesItems.FLAT_CROISSANT);
                        output.accept(BakeriesItems.COUNTRY_BREAD_SLICE);
                        output.accept(BakeriesItems.TOAST.get());
                        output.accept(BakeriesItems.SLICED_TOAST.get());
                        output.accept(BakeriesItems.BAKE_SLICED_TOAST.get());
                        output.accept(BakeriesItems.HONEY_BUTTER_SPREAD_TOAST.get());
                        output.accept(BakeriesItems.CHEESE_COCOA_TOAST.get());
                        output.accept(BakeriesItems.SLICED_CHEESE_COCOA_TOAST.get());
                        output.accept(BakeriesItems.HONEY_BUTTER_SPREAD_COUNTRY_BREAD);
                        output.accept(BakeriesItems.CIABATTA);
                        output.accept(BakeriesItems.FOCACCIA);
                        output.accept(BakeriesItems.BAGUETTE);
                        output.accept(BakeriesItems.BERRY_BAGEL);
                        output.accept(BakeriesItems.BAGEL_FILLED_SAUCE);
                        output.accept(BakeriesItems.BAGUETTE_WITH_FILLING);
                        output.accept(BakeriesItems.TOMATO_CHEESE_CROISSANT_SANDWICH);
                        output.accept(BakeriesItems.EGG_TART);
                        output.accept(BakeriesItems.TARO_SALT_YOLK_BREAD);

                        output.accept(BakeriesItems.ICED_AMERICAN);
                        output.accept(BakeriesItems.ICED_LATTE);
                        output.accept(BakeriesItems.BROWN_SUGAR_LATTE);
                        output.accept(BakeriesItems.CREAM_BINGLE_COFFEE);
                        output.accept(BakeriesItems.MATCHA_LATTE);
                        output.accept(BakeriesItems.MATCHA_PARFAIT);
                        output.accept(BakeriesItems.TARO_MILK);
                            }
                    ).build());

    public static final Supplier<CreativeModeTab> SFP_TAB = REGISTER.register(
            "1_bakeries_sfp_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.bakeries.bakeries_sfp_tab"))
                    .icon(() -> BakeriesItems.ROUND_BREAD.get().getDefaultInstance())
                    .displayItems((_, output) ->
                            {
                                output.accept(BakeriesItems.SWEET_DOUGH);
                                output.accept(BakeriesItems.SALTED_DOUGH);
                                output.accept(BakeriesItems.COCOA_DOUGH);
                                output.accept(BakeriesItems.WHOLE_WHEAT_DOUGH);
                                output.accept(BakeriesItems.PASTRY);
                                output.accept(BakeriesItems.EGG_TART_SHELL);
                                output.accept(BakeriesItems.RAW_EGG_TART);
                                output.accept(BakeriesItems.BAGEL_DOUGH);
                                output.accept(BakeriesItems.WHOLE_WHEAT_BAGEL_DOUGH);
                                output.accept(BakeriesItems.ROUND_BREAD_DOUGH);
                                output.accept(BakeriesItems.BROWN_SUGAR_ROLL_DOUGH);
                                output.accept(BakeriesItems.PINEAPPLE_BUN_DOUGH);
                                output.accept(BakeriesItems.CROISSANT_DOUGH);
                                output.accept(BakeriesItems.SALT_CROISSANT_DOUGH);
                                output.accept(BakeriesItems.BAGUETTE_DOUGH);
                                output.accept(BakeriesItems.CIABATTA_DOUGH);
                                output.accept(BakeriesItems.FOCACCIA_DOUGH);
                                output.accept(BakeriesItems.MOULD_TOAST_DOUGH);
                                output.accept(BakeriesItems.MOULD_CHEESE_COCOA_TOAST_DOUGH);
                                output.accept(BakeriesItems.FOCACCIA_DOUGH);
                                output.accept(BakeriesItems.COUNTRY_BREAD_DOUGH);
                            }
                    ).build());

}
