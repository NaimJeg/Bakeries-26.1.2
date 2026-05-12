package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.api.FoodData;
import com.renyigesai.bakeries.api.blocks.AbstractPileBlock;
import com.renyigesai.bakeries.api.items.BottleButterItem;
import com.renyigesai.bakeries.api.items.PileItem;
import com.renyigesai.bakeries.common.items.BreadKnifeItem;
import com.renyigesai.bakeries.common.items.FlourSieveItem;
import com.renyigesai.bakeries.common.items.ShakeItem;
import com.renyigesai.bakeries.common.items.WholeEggItem;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class BakeriesItems {
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(BakeriesMod.MODID);

    public static final DeferredItem<Item> BLENDER;
    public static final DeferredItem<Item> FERMENTATION_TANK;
    public static final DeferredItem<Item> YEAST_TANK;
    public static final DeferredItem<Item> MILK_TANK;
    public static final DeferredItem<Item> CHEESE_TANK;

    public static final DeferredItem<Item> FLOUR;
    public static final DeferredItem<Item> WHOLE_WHEAT_FLOUR;
    public static final DeferredItem<Item> COCOA_POWDER;
    public static final DeferredItem<Item> MATCHA_POWDER;

    public static final DeferredItem<Item> SALT;
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

    public static final DeferredItem<Item> TOMATO;


    public static final DeferredItem<Item> WOOD_TRAY;

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
//    public static final DeferredItem<Item> BAGUETTE;
//    public static final DeferredItem<Item> COUNTRY_BREAD;
    public static final DeferredItem<Item> COUNTRY_BREAD_SLICE;
    public static final DeferredItem<Item> HONEY_BUTTER_SPREAD_COUNTRY_BREAD;
    public static final DeferredItem<Item> CIABATTA;
    public static final DeferredItem<Item> FOCACCIA;
    public static final DeferredItem<Item> BERRY_BAGEL;
    public static final DeferredItem<Item> BAGEL_FILLED_SAUCE;
    public static final DeferredItem<Item> BAGUETTE_WITH_FILLING;
    public static final DeferredItem<Item> TOMATO_CHEESE_CROISSANT_SANDWICH;
    public static final DeferredItem<Item> EGG_TART;
    public static final DeferredItem<Item> TARO_SALT_YOLK_BREAD;

    public static final DeferredItem<Item> BREAD_KNIFE;
    public static final DeferredItem<Item> FLOUR_SIEVE;


    static {

        BLENDER = block(BakeriesBlocks.BLENDER);

        FERMENTATION_TANK = block(BakeriesBlocks.FERMENTATION_TANK);
        YEAST_TANK = block(BakeriesBlocks.YEAST_TANK);
        MILK_TANK = block(BakeriesBlocks.MILK_TANK);
        CHEESE_TANK = block(BakeriesBlocks.CHEESE_TANK);

        FLOUR = item("flour");
        WHOLE_WHEAT_FLOUR = item("whole_wheat_flour");
        COCOA_POWDER = item("cocoa_powder");
        MATCHA_POWDER = item("matcha_powder");
        FOAMED_CREAM = foodItem("foamed_cream",BakeriesFoodProperties.FOAMED_CREAM);
        CHEESE_CREAM = foodItem("cheese_cream",BakeriesFoodProperties.FOAMED_CREAM);
        BUTTER_FLOUR_SAND = item("butter_flour_sand");
        HONEY_BUTTER = item("honey_butter");
        WHOLE_EGG = REGISTER.register("whole_egg", WholeEggItem::new);
        RAW_PROTEIN = item("raw_protein");
        RAW_EGG_YOLK = item("raw_egg_yolk");
        SALT_YOLK = item("salt_yolk");

        SALT = item("salt");
        BOTTLE_YEAST = REGISTER.register("bottle_yeast",()-> new Item(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).setId(modItemId("bottle_yeast"))));
        BUTTER_CUBE = item("butter_cube");

        BOTTLE_MILK = REGISTER.register("bottle_milk",()-> new ShakeItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).setId(modItemId("bottle_milk")),BakeriesItems.BOTTLE_CREAM));
        BOTTLE_CREAM = REGISTER.register("bottle_cream",()-> new ShakeItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).setId(modItemId("bottle_cream")),BakeriesItems.BOTTLE_BUTTER));
        BOTTLE_BUTTER = REGISTER.register("bottle_butter", BottleButterItem::new);

        CHEESE_CUBE = foodItem("cheese_cube",BakeriesFoodProperties.CHEESE_CUBE);
        FRESH_CHEESE_CUBE = foodItem("fresh_cheese_cube",BakeriesFoodProperties.CHEESE_CUBE);
        BROWN_SUGAR_CUBE = item("brown_sugar_cube");

        TOMATO = REGISTER.register("tomato",()-> new BlockItem(BakeriesBlocks.TOMATO.get(),new Item.Properties().food(BakeriesFoodProperties.TOMATO).useBlockDescriptionPrefix().setId(modItemId("tomato"))));


        WOOD_TRAY = block(BakeriesBlocks.WOOD_TRAY);

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
//        BAGUETTE = REGISTER.register("baguette",()-> new BaguetteItem(BakeriesBlocks.BAGUETTE.get(),new Item.Properties().durability(4).food(BakeriesFoodProperties.BAGUETTE).attributes(BaguetteItem.createAttributes())));
//        COUNTRY_BREAD = block(BakeriesBlocks.COUNTRY_BREAD);
        COUNTRY_BREAD_SLICE = foodItem("country_bread_slice",BakeriesFoodProperties.COUNTRY_BREAD_SLICE);
        HONEY_BUTTER_SPREAD_COUNTRY_BREAD = foodItem("honey_butter_spread_country_bread",BakeriesFoodProperties.HONEY_BUTTER_SPREAD_COUNTRY_BREAD);
//        MOULD_TOAST = mouldBlock(BakeriesBlocks.MOULD_TOAST);
//        MOULD_CHEESE_COCOA_TOAST = mouldBlock(BakeriesBlocks.MOULD_CHEESE_COCOA_TOAST);
        EGG_TART = foodBreadBlock(BakeriesBlocks.EGG_TART,defaultFoodBread(FoodData.create(BakeriesFoodProperties.EGG_TART,BakeriesConsumables.EGG_TART)));
        TARO_SALT_YOLK_BREAD = foodBreadBlock(BakeriesBlocks.TARO_SALT_YOLK_BREAD,defaultFoodBread(FoodData.create(BakeriesFoodProperties.TARO_SALT_YOLK_BREAD,BakeriesConsumables.TARO_SALT_YOLK_BREAD)).rarity(BakeriesRarity.getTaro()));

        BREAD_KNIFE = REGISTER.register("bread_knife",()-> new BreadKnifeItem(ToolMaterial.IRON,"bread_knife"));
        FLOUR_SIEVE = REGISTER.register("flour_sieve",FlourSieveItem::new);
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
