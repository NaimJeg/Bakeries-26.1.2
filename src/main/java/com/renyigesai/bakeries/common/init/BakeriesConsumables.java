package com.renyigesai.bakeries.common.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class BakeriesConsumables {
    public static final Consumable WHOLE_WHEAT_BAGEL;
    public static final Consumable BROWN_SUGAR_ROLL;
    public static final Consumable CROISSANT;
    public static final Consumable PINEAPPLE_BUN;
    public static final Consumable SALT_CROISSANT;
    public static final Consumable SLICED_CHEESE_COCOA_TOAST;
    public static final Consumable FOCACCIA;
    public static final Consumable ICED_AMERICAN;
    public static final Consumable ICED_LATTE;
    public static final Consumable BROWN_SUGAR_LATTE;
    public static final Consumable CREAM_BINGLE_COFFEE;
    public static final Consumable MATCHA_LATTE;
    public static final Consumable MATCHA_PARFAIT;
    public static final Consumable DIRTY_CHOCO_CROISSANT;
    public static final Consumable TOMATO_CHEESE_CROISSANT_SANDWICH;
    public static final Consumable BERRY_BAGEL;
    public static final Consumable CHEESE_CREAM_BREAD;
    public static final Consumable EGG_TART;
    public static final Consumable PINEAPPLE_OIL;
    public static final Consumable FLAT_CROISSANT;
    public static final Consumable TARO_SALT_YOLK_BREAD;
    public static final Consumable TARO_MILK;
    public static final Consumable RICE_BREAD;
    public static final Consumable ORANGE_AMERICAN;
    public static final Consumable GARLIC_FLAVORED_BAGUETTE;

    static {
        WHOLE_WHEAT_BAGEL = defaultEffectFood(new MobEffectInstance(MobEffects.SATURATION, 20)).build();
        BROWN_SUGAR_ROLL = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.ENJOY, 600)).build();
        CROISSANT = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.ENJOY, 1200)).build();
        PINEAPPLE_BUN = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.ENJOY, 1200)).build();

        SALT_CROISSANT = Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(BakeriesMobEffects.ENJOY, 600)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.STRENGTH, 600)))
                .build();

        SLICED_CHEESE_COCOA_TOAST = Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(BakeriesMobEffects.COCOA_MANIA, 600)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(BakeriesMobEffects.CHEESE_POWER, 600)))
                .build();

        FOCACCIA = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.ENJOY, 600, 1)).build();

        ICED_AMERICAN = defaultEffectFood(new MobEffectInstance(MobEffects.SPEED, 600)).consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false).build();
        ICED_LATTE = defaultEffectFood(new MobEffectInstance(MobEffects.SPEED, 600)).consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false).build();
        BROWN_SUGAR_LATTE = defaultEffectFood(new MobEffectInstance(MobEffects.SPEED, 600)).consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false).build();
        CREAM_BINGLE_COFFEE = defaultEffectFood(new MobEffectInstance(MobEffects.SPEED, 600)).consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false).build();

        MATCHA_LATTE = Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 600)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 600)))
                .consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK)
                .hasConsumeParticles(false)
                .build();

        MATCHA_PARFAIT = Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 600)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 600)))
                .consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK)
                .hasConsumeParticles(false)
                .build();

        DIRTY_CHOCO_CROISSANT = Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(BakeriesMobEffects.ENJOY, 1200)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(BakeriesMobEffects.COCOA_MANIA, 1200)))
                .build();

        TOMATO_CHEESE_CROISSANT_SANDWICH = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.ENJOY, 6000, 2)).build();
        BERRY_BAGEL = defaultEffectFood(new MobEffectInstance(MobEffects.REGENERATION, 1200)).build();

        CHEESE_CREAM_BREAD = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.CHEESE_POWER, 1200)).build();
        EGG_TART = defaultEffectFood(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1)).build();
        PINEAPPLE_OIL = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.ENJOY, 1800, 1)).build();

        FLAT_CROISSANT = Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(BakeriesMobEffects.ENJOY, 600)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.RESISTANCE, 600)))
                .build();

        TARO_SALT_YOLK_BREAD = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.ENJOY, 1800)).build();

        TARO_MILK = Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 600)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 600)))
                .consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK)
                .hasConsumeParticles(false)
                .build();

        RICE_BREAD = defaultEffectFood(new MobEffectInstance(BakeriesMobEffects.ENJOY, 600)).build();

        ORANGE_AMERICAN = Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 600)))
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 600)))
                .build();

        GARLIC_FLAVORED_BAGUETTE = defaultEffectFood(new MobEffectInstance(MobEffects.RESISTANCE, 600)).build();
    }

    // 便捷方法：创建仅包含单个药水效果的 Consumable
    private static Consumable.Builder defaultEffectFood(MobEffectInstance effect) {
        return Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(effect));
    }

    public static Consumable.Builder defaultShake() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
    }
}