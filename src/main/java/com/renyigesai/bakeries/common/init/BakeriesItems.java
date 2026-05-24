package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.FoodData;
import com.renyigesai.bakeries.api.blocks.AbstractPileBlock;
import com.renyigesai.bakeries.api.items.BottleButterItem;
import com.renyigesai.bakeries.api.items.PileItem;
import com.renyigesai.bakeries.api.items.RawItem;
import com.renyigesai.bakeries.common.blocks.fluid.BakeriesFluids;
import com.renyigesai.bakeries.common.items.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.waypoints.Waypoint;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class BakeriesItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(BakeriesMod.MODID);

    public static final DeferredItem<Item> BLENDER;
    public static final DeferredItem<Item> DOUGH_CRAFTING_TABLE;
    public static final DeferredItem<Item> CUPBOARD;
    public static final DeferredItem<Item> OVEN;
    public static final DeferredItem<Item> TOASTER;
    public static final DeferredItem<Item> MOKA_POT;
    public static final DeferredItem<Item> MOKA_POT_FILL;
    public static final DeferredItem<Item> DRINK_CUP;
    public static final DeferredItem<Item> WHOLE_WHEAT_FLOUR_BAG;
    public static final DeferredItem<Item> FLOUR_BAG;
    public static final DeferredItem<Item> FERMENTATION_TANK;
    public static final DeferredItem<Item> YEAST_TANK;
    public static final DeferredItem<Item> MILK_TANK;
    public static final DeferredItem<Item> CHEESE_TANK;
    public static final DeferredItem<Item> MOULD;

    public static final DeferredItem<Item> FLOUR;
    public static final DeferredItem<Item> WHOLE_WHEAT_FLOUR;
    public static final DeferredItem<Item> COCOA_POWDER;
    public static final DeferredItem<Item> MATCHA_POWDER;
    public static final DeferredItem<Item> OLIVE_OIL;
    public static final DeferredItem<Item> BEARNAISE;
    public static final DeferredItem<Item> MEAT_FLOSS;

    public static final DeferredItem<Item> SALT;
    public static final DeferredItem<Item> RAW_SALT_BLOCK;
    public static final DeferredItem<Item> SALT_WATER_BUCKET;
    public static final DeferredItem<Item> BOTTLE_YEAST;

    public static final DeferredItem<Item> BUTTER_CUBE;

    public static final DeferredItem<Item> BOTTLE_MILK;
    public static final DeferredItem<Item> BOTTLE_CREAM;
    public static final DeferredItem<Item> BOTTLE_BUTTER;

    public static final DeferredItem<Item> FOAMED_CREAM;

    public static final DeferredItem<Item> CHEESE_CREAM;

    public static final DeferredItem<Item> BUTTER_FLOUR_SAND;

    public static final DeferredItem<Item> HONEY_BUTTER;

    public static final DeferredItem<Item> WHOLE_EGG;

    public static final DeferredItem<Item> RAW_PROTEIN;

    public static final DeferredItem<Item> RAW_EGG_YOLK;

    public static final DeferredItem<Item> SALT_YOLK;

    public static final DeferredItem<Item> CHEESE_CUBE;

    public static final DeferredItem<Item> FRESH_CHEESE_CUBE;

    public static final DeferredItem<Item> BROWN_SUGAR_CUBE;

    public static final DeferredItem<Item> RAW_COFFEE_BEAN;

    public static final DeferredItem<Item> COFFEE_BEAN;

    public static final DeferredItem<Item> GROUND_COFFEE;

    public static final DeferredItem<Item> TOMATO;
    public static final DeferredItem<Item> OLIVE;
    public static final DeferredItem<Item> TARO;
    public static final DeferredItem<Item> COOKED_TARO;
    public static final DeferredItem<Item> MASHED_TARO;

    public static final DeferredItem<Item> WOOD_TRAY;
    public static final DeferredItem<Item> COFFEE_TABLE;

    public static final DeferredItem<Item> BREAD_RACK;
    public static final DeferredItem<Item> GLASS_BREAD_RACK;

    public static final DeferredItem<Item> SOFA_WHITE;
    public static final DeferredItem<Item> SOFA_RED;
    public static final DeferredItem<Item> SOFA_LIGHT_GRAY;

    public static final DeferredItem<Item> WOOD_COUNTER;
    public static final DeferredItem<Item> CASH_REGISTER_COMPUTER;
    public static final DeferredItem<Item> LUMINOUS_LIGHT_SIGN;
    public static final DeferredItem<Item> BREAD_BASKET;

    public static final DeferredItem<Item> BAGEL;
    public static final DeferredItem<Item> WHOLE_WHEAT_BAGEL;
    public static final DeferredItem<Item> ROUND_BREAD;
    public static final DeferredItem<Item> BERRY_BREAD;
    public static final DeferredItem<Item> CHEESE_CREAM_BREAD;
    public static final DeferredItem<Item> BROWN_SUGAR_ROLL;
    public static final DeferredItem<Item> PINEAPPLE_BUN;
    public static final DeferredItem<Item> PINEAPPLE_OIL;
    public static final DeferredItem<Item> MEAT_FLOSS_BREAD_ROLL;
    public static final DeferredItem<Item> CROISSANT;
    public static final DeferredItem<Item> DIRTY_CHOCO_CROISSANT;
    public static final DeferredItem<Item> SALT_CROISSANT;
    public static final DeferredItem<Item> FLAT_CROISSANT;

    /**���Ʒ������*/
    public static final DeferredItem<Item> SWEET_DOUGH;

    public static final DeferredItem<Item> COCOA_DOUGH;

    public static final DeferredItem<Item> SALTED_DOUGH;

    public static final DeferredItem<Item> WHOLE_WHEAT_DOUGH;

    public static final DeferredItem<Item> PASTRY;

    public static final DeferredItem<Item> EGG_TART_SHELL;

    public static final DeferredItem<Item> RAW_EGG_TART;

    public static final DeferredItem<Item> BAGEL_DOUGH;

    public static final DeferredItem<Item> WHOLE_WHEAT_BAGEL_DOUGH;

    public static final DeferredItem<Item> ROUND_BREAD_DOUGH;

    public static final DeferredItem<Item> BROWN_SUGAR_ROLL_DOUGH;

    public static final DeferredItem<Item> PINEAPPLE_BUN_DOUGH;

    public static final DeferredItem<Item> CROISSANT_DOUGH;

    public static final DeferredItem<Item> SALT_CROISSANT_DOUGH;

    public static final DeferredItem<Item> BAGUETTE_DOUGH;

    public static final DeferredItem<Item> CIABATTA_DOUGH;

    public static final DeferredItem<Item> FOCACCIA_DOUGH;

    public static final DeferredItem<Item> MOULD_TOAST_DOUGH;

    public static final DeferredItem<Item> MOULD_CHEESE_COCOA_TOAST_DOUGH;

    public static final DeferredItem<Item> COUNTRY_BREAD_DOUGH;

//    public static final DeferredItem<Item> MOULD_TOAST_DOUGH;

    public static final DeferredItem<Item> TOAST;
    public static final DeferredItem<Item> SLICED_TOAST;
    public static final DeferredItem<Item> BAKE_SLICED_TOAST;
    public static final DeferredItem<Item> HONEY_BUTTER_SPREAD_TOAST;
    public static final DeferredItem<Item> CHEESE_COCOA_TOAST;
    public static final DeferredItem<Item> SLICED_CHEESE_COCOA_TOAST;
//    public static final DeferredItem<Item> BAGUETTE;
//    public static final DeferredItem<Item> COUNTRY_BREAD;
    public static final DeferredItem<Item> COUNTRY_BREAD_SLICE;
    public static final DeferredItem<Item> HONEY_BUTTER_SPREAD_COUNTRY_BREAD;
    public static final DeferredItem<Item> MOULD_TOAST;
    public static final DeferredItem<Item> MOULD_CHEESE_COCOA_TOAST;
    public static final DeferredItem<Item> CIABATTA;
    public static final DeferredItem<Item> FOCACCIA;
    public static final DeferredItem<Item> BERRY_BAGEL;
    public static final DeferredItem<Item> BAGEL_FILLED_SAUCE;
    public static final DeferredItem<Item> BAGUETTE_WITH_FILLING;
    public static final DeferredItem<Item> TOMATO_CHEESE_CROISSANT_SANDWICH;
    public static final DeferredItem<Item> BAGUETTE;
    public static final DeferredItem<Item> COUNTRY_BREAD;
    public static final DeferredItem<Item> EGG_TART;
    public static final DeferredItem<Item> TARO_SALT_YOLK_BREAD;

    public static final DeferredItem<Item> ICED_AMERICAN;
    public static final DeferredItem<Item> ICED_LATTE;
    public static final DeferredItem<Item> BROWN_SUGAR_LATTE;
    public static final DeferredItem<Item> CREAM_BINGLE_COFFEE;
    public static final DeferredItem<Item> MATCHA_LATTE;
    public static final DeferredItem<Item> MATCHA_PARFAIT;
    public static final DeferredItem<Item> TARO_MILK;

    public static final DeferredItem<Item> BREAD_KNIFE;
    public static final DeferredItem<Item> FLOUR_SIEVE;
    public static final DeferredItem<Item> ETERNAL_BAGUETTE;


    static {

        BLENDER = block(BakeriesBlocks.BLENDER);
        DOUGH_CRAFTING_TABLE = block(BakeriesBlocks.DOUGH_CRAFTING_TABLE);
        CUPBOARD = block(BakeriesBlocks.CUPBOARD);
        OVEN = block(BakeriesBlocks.OVEN);
        TOASTER = block(BakeriesBlocks.TOASTER);
        MOKA_POT = block(BakeriesBlocks.MOKA_POT);
        MOKA_POT_FILL = REGISTER.register("moka_pot_fill",MokaPotFillItem::new);
        DRINK_CUP = block(BakeriesBlocks.DRINK_CUP);
        WHOLE_WHEAT_FLOUR_BAG = block(BakeriesBlocks.WHOLE_WHEAT_FLOUR_BAG);
        FLOUR_BAG = block(BakeriesBlocks.FLOUR_BAG);

        FERMENTATION_TANK = block(BakeriesBlocks.FERMENTATION_TANK);
        YEAST_TANK = block(BakeriesBlocks.YEAST_TANK);
        MILK_TANK = block(BakeriesBlocks.MILK_TANK);
        CHEESE_TANK = block(BakeriesBlocks.CHEESE_TANK);
        MOULD = block(BakeriesBlocks.MOULD);

        FLOUR = item("flour");
        WHOLE_WHEAT_FLOUR = item("whole_wheat_flour");
        COCOA_POWDER = item("cocoa_powder");
        MATCHA_POWDER = item("matcha_powder");
        OLIVE_OIL = REGISTER.register("olive_oil",OliveOilItem::new);
        BEARNAISE = REGISTER.register("bearnaise",()->new PileItem(BakeriesBlocks.BEARNAISE.get(),new PileItem.PileProperties().placeSound(SoundEvents.GLASS_PLACE).itemProperties(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).setId(modItemId("bearnaise")))));
        MEAT_FLOSS = foodItem("meat_floss",BakeriesFoodProperties.MEAT_FLOSS);
        FOAMED_CREAM = foodItem("foamed_cream",BakeriesFoodProperties.FOAMED_CREAM);
        CHEESE_CREAM = foodItem("cheese_cream",BakeriesFoodProperties.FOAMED_CREAM);
        BUTTER_FLOUR_SAND = item("butter_flour_sand");
        HONEY_BUTTER = item("honey_butter");
        WHOLE_EGG = REGISTER.register("whole_egg", WholeEggItem::new);
        RAW_PROTEIN = item("raw_protein");
        RAW_EGG_YOLK = item("raw_egg_yolk");
        SALT_YOLK = item("salt_yolk");

        SALT = item("salt");
        RAW_SALT_BLOCK = block(BakeriesBlocks.RAW_SALT_BLOCK);
        SALT_WATER_BUCKET = REGISTER.register("salt_water_bucket",()-> new BucketItem(BakeriesFluids.SALT_WATER.get(),new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).setId(modItemId("salt_water_bucket"))));
        BOTTLE_YEAST = REGISTER.register("bottle_yeast",()-> new Item(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).setId(modItemId("bottle_yeast"))));
        BUTTER_CUBE = item("butter_cube");

        BOTTLE_MILK = REGISTER.register("bottle_milk",()-> new ShakeItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).setId(modItemId("bottle_milk")),BakeriesItems.BOTTLE_CREAM));
        BOTTLE_CREAM = REGISTER.register("bottle_cream",()-> new ShakeItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).setId(modItemId("bottle_cream")),BakeriesItems.BOTTLE_BUTTER));
        BOTTLE_BUTTER = REGISTER.register("bottle_butter", BottleButterItem::new);

        CHEESE_CUBE = foodItem("cheese_cube",BakeriesFoodProperties.CHEESE_CUBE);
        FRESH_CHEESE_CUBE = foodItem("fresh_cheese_cube",BakeriesFoodProperties.CHEESE_CUBE);
        BROWN_SUGAR_CUBE = item("brown_sugar_cube");

        RAW_COFFEE_BEAN = REGISTER.register("raw_coffee_bean",()-> new BlockItem(BakeriesBlocks.COFFEE_PLANT.get(), new Item.Properties().setId(modItemId("raw_coffee_bean")).useBlockDescriptionPrefix()));
        COFFEE_BEAN = item("coffee_bean");
        GROUND_COFFEE = item("ground_coffee");



        TOMATO = REGISTER.register("tomato",()-> new BlockItem(BakeriesBlocks.TOMATO.get(),new Item.Properties().food(BakeriesFoodProperties.TOMATO).useBlockDescriptionPrefix().setId(modItemId("tomato"))));
        OLIVE = item("olive");
        TARO = REGISTER.register("taro",()-> new BlockItem(BakeriesBlocks.TARO.get(),new Item.Properties().useBlockDescriptionPrefix().setId(modItemId("taro"))));
        COOKED_TARO = foodItem("cooked_taro",BakeriesFoodProperties.COOKED_TARO);
        MASHED_TARO = foodItem("mashed_taro",BakeriesFoodProperties.MASHED_TARO);


        WOOD_TRAY = item("wood_tray");

        COFFEE_TABLE = block(BakeriesBlocks.COFFEE_TABLE);

        BREAD_RACK = block(BakeriesBlocks.BREAD_RACK);
//        GLASS_BREAD_RACK = block(BakeriesBlocks.GLASS_BREAD_RACK);
//        CARVED_PUMPKIN = registerBlock(Blocks.CARVED_PUMPKIN, (UnaryOperator)((p) -> Waypoint.addHideAttribute(p).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD).setSwappable(false).setCameraOverlay(Identifier.withDefaultNamespace("misc/pumpkinblur")).build())));
        GLASS_BREAD_RACK = REGISTER.register("glass_bread_rack",()-> new BlockItem(BakeriesBlocks.GLASS_BREAD_RACK.get(),new Item.Properties().useBlockDescriptionPrefix().setId(modItemId("glass_bread_rack"))));

        SOFA_WHITE = block(BakeriesBlocks.SOFA_WHITE);
        SOFA_RED = block(BakeriesBlocks.SOFA_RED);
        SOFA_LIGHT_GRAY = block(BakeriesBlocks.SOFA_LIGHT_GRAY);
        WOOD_COUNTER = REGISTER.register("wood_counter",()-> new  BlockItem(BakeriesBlocks.WOOD_COUNTER.get(),new Item.Properties().useBlockDescriptionPrefix().setId(modItemId("wood_counter"))){
            @Override
            public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                builder.accept(Component.translatable("tooltips.bakeries.wood_counter").withStyle(ChatFormatting.GRAY));
            }
        });

        CASH_REGISTER_COMPUTER = block(BakeriesBlocks.CASH_REGISTER_COMPUTER);
        LUMINOUS_LIGHT_SIGN = block(BakeriesBlocks.LUMINOUS_LIGHT_SIGN);
        BREAD_BASKET = block(BakeriesBlocks.BREAD_BASKET);

        BAGEL = foodBreadBlock(BakeriesBlocks.BAGEL,BakeriesFoodProperties.BAGEL);
        WHOLE_WHEAT_BAGEL = foodBreadBlock(BakeriesBlocks.WHOLE_WHEAT_BAGEL,FoodData.create(BakeriesFoodProperties.WHOLE_WHEAT_BAGEL,BakeriesConsumables.WHOLE_WHEAT_BAGEL));
        ROUND_BREAD = foodBreadBlock(BakeriesBlocks.ROUND_BREAD, BakeriesFoodProperties.ROUND_BREAD);
        BERRY_BREAD = foodBreadBlock(BakeriesBlocks.BERRY_BREAD, BakeriesFoodProperties.BERRY_BREAD);
        CHEESE_CREAM_BREAD = foodBreadBlock(BakeriesBlocks.CHEESE_CREAM_BREAD, defaultFoodBread(FoodData.create(BakeriesFoodProperties.CHEESE_CREAM_BREAD,BakeriesConsumables.CHEESE_CREAM_BREAD)).rarity(BakeriesRarity.getAdvanced()));
        BROWN_SUGAR_ROLL = foodBreadBlock(BakeriesBlocks.BROWN_SUGAR_ROLL,FoodData.create(BakeriesFoodProperties.BROWN_SUGAR_ROLL,BakeriesConsumables.BROWN_SUGAR_ROLL));
        PINEAPPLE_BUN = foodBreadBlock(BakeriesBlocks.PINEAPPLE_BUN,FoodData.create(BakeriesFoodProperties.PINEAPPLE_BUN,BakeriesConsumables.PINEAPPLE_BUN));
        PINEAPPLE_OIL = foodBreadBlock(BakeriesBlocks.PINEAPPLE_OIL,FoodData.create(BakeriesFoodProperties.PINEAPPLE_OIL,BakeriesConsumables.PINEAPPLE_OIL));
        MEAT_FLOSS_BREAD_ROLL = foodBreadBlock(BakeriesBlocks.MEAT_FLOSS_BREAD_ROLL, defaultFoodBread(FoodData.create(BakeriesFoodProperties.MEAT_FLOSS_BREAD)).rarity(BakeriesRarity.getAdvanced()));
        CROISSANT = foodBreadBlock(BakeriesBlocks.CROISSANT, FoodData.create(BakeriesFoodProperties.CROISSANT,BakeriesConsumables.CROISSANT));
        DIRTY_CHOCO_CROISSANT = foodBreadBlock(BakeriesBlocks.DIRTY_CHOCO_CROISSANT, FoodData.create(BakeriesFoodProperties.DIRTY_CHOCO_CROISSANT,BakeriesConsumables.DIRTY_CHOCO_CROISSANT));
        SALT_CROISSANT = foodBreadBlock(BakeriesBlocks.SALT_CROISSANT, FoodData.create(BakeriesFoodProperties.SALT_CROISSANT,BakeriesConsumables.SALT_CROISSANT));
        FLAT_CROISSANT = foodBreadBlock(BakeriesBlocks.FLAT_CROISSANT,defaultFoodBread(FoodData.create(BakeriesFoodProperties.FLAT_CROISSANT,BakeriesConsumables.FLAT_CROISSANT)).rarity(BakeriesRarity.getAdvanced()));
        CIABATTA = foodBreadBlock(BakeriesBlocks.CIABATTA, BakeriesFoodProperties.CIABATTA);
        FOCACCIA = foodBreadBlock(BakeriesBlocks.FOCACCIA, FoodData.create(BakeriesFoodProperties.FOCACCIA,BakeriesConsumables.FOCACCIA));
        BERRY_BAGEL = foodBreadBlock(BakeriesBlocks.BERRY_BAGEL, defaultFoodBread(FoodData.create(BakeriesFoodProperties.BERRY_BAGEL)).rarity(BakeriesRarity.getAdvanced()));
        BAGEL_FILLED_SAUCE = foodBreadBlock(BakeriesBlocks.BAGEL_FILLED_SAUCE, defaultFoodBread(FoodData.create(BakeriesFoodProperties.BAGEL_FILLED_SAUCE)).rarity(BakeriesRarity.getAdvanced()));
        BAGUETTE_WITH_FILLING = foodBreadBlock(BakeriesBlocks.BAGUETTE_WITH_FILLING, defaultFoodBread(FoodData.create(BakeriesFoodProperties.BAGUETTE_WITH_FILLING)).rarity(BakeriesRarity.getAdvanced()));
        TOMATO_CHEESE_CROISSANT_SANDWICH = foodBreadBlock(BakeriesBlocks.TOMATO_CHEESE_CROISSANT_SANDWICH, defaultFoodBread(FoodData.create(BakeriesFoodProperties.TOMATO_CHEESE_CROISSANT_SANDWICH,BakeriesConsumables.TOMATO_CHEESE_CROISSANT_SANDWICH)).rarity(BakeriesRarity.getAdvanced()));
        BAGUETTE = REGISTER.register("baguette", BaguetteItem::new);
        COUNTRY_BREAD = block(BakeriesBlocks.COUNTRY_BREAD);
        COUNTRY_BREAD_SLICE = foodItem("country_bread_slice",BakeriesFoodProperties.COUNTRY_BREAD_SLICE);
        HONEY_BUTTER_SPREAD_COUNTRY_BREAD = foodItem("honey_butter_spread_country_bread",BakeriesFoodProperties.HONEY_BUTTER_SPREAD_COUNTRY_BREAD);
        MOULD_TOAST = REGISTER.register("mould_toast",()-> new MouldBlockItem(BakeriesBlocks.MOULD_TOAST.get(),new Item.Properties().setId(modItemId("mould_toast")).useBlockDescriptionPrefix()));
        MOULD_CHEESE_COCOA_TOAST = REGISTER.register("mould_cheese_cocoa_toast",()-> new MouldBlockItem(BakeriesBlocks.MOULD_CHEESE_COCOA_TOAST.get(),new Item.Properties().setId(modItemId("mould_cheese_cocoa_toast")).useBlockDescriptionPrefix()));
        EGG_TART = foodBreadBlock(BakeriesBlocks.EGG_TART,defaultFoodBread(FoodData.create(BakeriesFoodProperties.EGG_TART,BakeriesConsumables.EGG_TART)));
        TARO_SALT_YOLK_BREAD = foodBreadBlock(BakeriesBlocks.TARO_SALT_YOLK_BREAD,defaultFoodBread(FoodData.create(BakeriesFoodProperties.TARO_SALT_YOLK_BREAD,BakeriesConsumables.TARO_SALT_YOLK_BREAD)).rarity(BakeriesRarity.getTaro()));

        TOAST = block(BakeriesBlocks.TOAST);
        CHEESE_COCOA_TOAST = block(BakeriesBlocks.CHEESE_COCOA_TOAST);

        SLICED_TOAST = foodItem("sliced_toast",BakeriesFoodProperties.SLICED_TOAST);
        BAKE_SLICED_TOAST = foodItem("bake_sliced_toast",BakeriesFoodProperties.SLICED_TOAST);
        HONEY_BUTTER_SPREAD_TOAST = REGISTER.register("honey_butter_spread_toast",()-> new Item(new Item.Properties().food(BakeriesFoodProperties.HONEY_BUTTER_SPREAD_TOAST).setId(modItemId("honey_butter_spread_toast"))));
        SLICED_CHEESE_COCOA_TOAST = foodItem("sliced_cheese_cocoa_toast",BakeriesFoodProperties.SLICED_CHEESE_COCOA_TOAST,BakeriesConsumables.SLICED_CHEESE_COCOA_TOAST);

        ICED_AMERICAN = drinkItem(BakeriesBlocks.ICED_AMERICAN,FoodData.create(BakeriesFoodProperties.ICED_AMERICAN,BakeriesConsumables.ICED_AMERICAN) ,1);
        ICED_LATTE = drinkItem(BakeriesBlocks.ICED_LATTE,FoodData.create(BakeriesFoodProperties.ICED_LATTE,BakeriesConsumables.ICED_LATTE) ,4);
        BROWN_SUGAR_LATTE = drinkItem(BakeriesBlocks.BROWN_SUGAR_LATTE,FoodData.create(BakeriesFoodProperties.BROWN_SUGAR_LATTE,BakeriesConsumables.BROWN_SUGAR_LATTE) ,2);
        CREAM_BINGLE_COFFEE = drinkItem(BakeriesBlocks.CREAM_BINGLE_COFFEE,FoodData.create(BakeriesFoodProperties.CREAM_BINGLE_COFFEE,BakeriesConsumables.CREAM_BINGLE_COFFEE) ,4);
        MATCHA_LATTE = drinkItem(BakeriesBlocks.MATCHA_LATTE,FoodData.create(BakeriesFoodProperties.MATCHA_LATTE,BakeriesConsumables.MATCHA_LATTE) ,3);
        MATCHA_PARFAIT = drinkItem(BakeriesBlocks.MATCHA_PARFAIT,FoodData.create(BakeriesFoodProperties.MATCHA_PARFAIT,BakeriesConsumables.MATCHA_PARFAIT) ,4);
        TARO_MILK = REGISTER.register("taro_milk",()-> new DrinkItem(BakeriesBlocks.TARO_MILK.get(),new Item.Properties().food(BakeriesFoodProperties.TARO_MILK,BakeriesConsumables.TARO_MILK).durability(6).craftRemainder(BakeriesItems.DRINK_CUP.get()).rarity(BakeriesRarity.getTaro()).setId(modItemId("taro_milk")).useBlockDescriptionPrefix(),4));

        SWEET_DOUGH = item("sweet_dough");
        SALTED_DOUGH = item("salted_dough");
        COCOA_DOUGH = item("cocoa_dough");
        WHOLE_WHEAT_DOUGH = item("whole_wheat_dough");
        PASTRY = item("pastry");
        EGG_TART_SHELL = item("egg_tart_shell");
        RAW_EGG_TART = rawItem("raw_egg_tart",180,200);
        BAGEL_DOUGH = rawItem("bagel_dough",200,225);
        WHOLE_WHEAT_BAGEL_DOUGH = rawItem("whole_wheat_bagel_dough",200,225);
        ROUND_BREAD_DOUGH = rawItem("round_bread_dough", 155,180);
        BROWN_SUGAR_ROLL_DOUGH = rawItem("brown_sugar_roll_dough",155,170);
        PINEAPPLE_BUN_DOUGH = rawItem("pineapple_bun_dough",170,180);
        CROISSANT_DOUGH = rawItem("croissant_dough",175,180);
        SALT_CROISSANT_DOUGH = rawItem("salt_croissant_dough",180,180);
        BAGUETTE_DOUGH = rawItem("baguette_dough",230,240);
        CIABATTA_DOUGH = rawItem("ciabatta_dough",210,220);
        FOCACCIA_DOUGH = rawItem("focaccia_dough",230,240);
        MOULD_TOAST_DOUGH = rawItem("mould_toast_dough",135);
        MOULD_CHEESE_COCOA_TOAST_DOUGH = rawItem("mould_cheese_cocoa_toast_dough",135);
        COUNTRY_BREAD_DOUGH = rawItem("country_bread_dough",225);

        BREAD_KNIFE = REGISTER.register("bread_knife",()-> new BreadKnifeItem(ToolMaterial.IRON,"bread_knife"));
        FLOUR_SIEVE = REGISTER.register("flour_sieve",FlourSieveItem::new);
        ETERNAL_BAGUETTE = REGISTER.register("eternal_baguette",EternalBaguetteItem::new);
    }

    private static DeferredItem<Item> drinkItem(Holder<Block> block,FoodData foodData,int upEffect){
        Identifier identifier = block.unwrapKey().get().identifier();
        return REGISTER.register(identifier.getPath(),()-> new DrinkItem(block.value(),new Item.Properties().food(foodData.foodProperties,foodData.consumable).durability(6).craftRemainder(BakeriesItems.DRINK_CUP.get()).setId(ResourceKey.create(Registries.ITEM,identifier)).useBlockDescriptionPrefix(),upEffect));
    }

    public static DeferredItem<Item> foodBreadBlock(Holder<Block> block, Item.Properties properties){
        return REGISTER.register(block.unwrapKey().orElseThrow().identifier().getPath(),()-> new PileItem(block.value(),properties.setId(ResourceKey.create(Registries.ITEM,block.unwrapKey().orElseThrow().identifier())).useBlockDescriptionPrefix()));
    }

    public static DeferredItem<Item> foodBreadBlock(Holder<Block> block, FoodProperties foodProperties){
        Identifier identifier = block.unwrapKey().get().identifier();
        return REGISTER.register(identifier.getPath(),()-> new PileItem(block.value(),defaultFoodBread(FoodData.create(foodProperties)).useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM,identifier))));
    }

    public static DeferredItem<Item> foodBreadBlock(Holder<Block> block, FoodData foodData){
        Identifier identifier = block.unwrapKey().get().identifier();
        return REGISTER.register(identifier.getPath(),()-> new PileItem(block.value(),defaultFoodBread(foodData).useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM,identifier))));
    }

    private static Item.Properties defaultFoodBread(FoodData foodData){
        return new Item.Properties().food(foodData.foodProperties,foodData.consumable).useBlockDescriptionPrefix();
    }

    public static DeferredItem<Item> foodItem(String name,FoodProperties foodProperties){
        return REGISTER.register(name,()-> new Item(new Item.Properties().food(foodProperties, Consumable.builder().build()).setId(ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(BakeriesMod.MODID,name)))));
    }

    public static DeferredItem<Item> foodItem(String name,FoodProperties foodProperties,Consumable consumable){
        return REGISTER.register(name,()-> new Item(new Item.Properties().food(foodProperties,consumable).setId(ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(BakeriesMod.MODID,name)))));
    }

    public static DeferredItem<Item> item(String name){
        return REGISTER.registerSimpleItem(name);
//        return REGISTER.register(name,()-> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(BakeriesMod.MODID,name)))));
    }

    private static DeferredItem<Item> rawItem(String pName, int temperature) {
        return REGISTER.register(pName, () -> new RawItem(new Item.Properties().setId(modItemId(pName)),temperature));
    }

    private static DeferredItem<Item> rawItem(String pName, int temperature,int perfectTemperature) {
        return REGISTER.register(pName, () -> new RawItem(new Item.Properties().setId(modItemId(pName)),temperature,perfectTemperature,pName));
    }

//    public static DeferredItem<Item> item(String name,Function<Item.Properties, Item> itemFactory){
//        return REGISTER.register(name,itemFactory.);
////        return REGISTER.register(name,()-> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(BakeriesMod.MODID,name)))));
//    }


    private static Item registerItem(ResourceKey<Item> key, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static ResourceKey<Item> modItemId(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BakeriesMod.MODID,name));
    }

    public static DeferredItem<Item> block(Holder<Block> block){
        Identifier identifier = block.unwrapKey().get().identifier();
        return REGISTER.register(identifier.getPath(),()-> new BlockItem(block.value(),new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM,identifier))));
    }
}
