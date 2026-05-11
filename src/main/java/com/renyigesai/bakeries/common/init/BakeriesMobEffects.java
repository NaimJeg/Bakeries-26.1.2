package com.renyigesai.bakeries.common.init;

import com.renyigesai.bakeries.BakeriesMod;
import com.renyigesai.bakeries.common.potion.BakeriesMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BakeriesMobEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT,BakeriesMod.MODID);

    public static final Holder<MobEffect> COCOA_MANIA = EFFECTS.register("cocoa_mania", ()->
            new BakeriesMobEffect(MobEffectCategory.BENEFICIAL, -3972305).addAttributeModifier(Attributes.ATTACK_SPEED, Identifier.withDefaultNamespace("effect.cocoa_mania"), 0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> CHEESE_POWER = EFFECTS.register("cheese_power", ()->
            new BakeriesMobEffect(MobEffectCategory.BENEFICIAL, -13312).addAttributeModifier(Attributes.ATTACK_DAMAGE,Identifier.withDefaultNamespace("effect.cheese_power"),5.0,  AttributeModifier.Operation.ADD_VALUE));

    public static final Holder<MobEffect> ENJOY = EFFECTS.register("enjoy", ()-> new BakeriesMobEffect(MobEffectCategory.BENEFICIAL, -13312));

    public static final Holder<MobEffect> SOFT = EFFECTS.register("soft", ()-> new BakeriesMobEffect(MobEffectCategory.BENEFICIAL, -3972305).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,Identifier.withDefaultNamespace("effect.cheese_power"),0.5,AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR,Identifier.withDefaultNamespace("effect.cheese_power"),5,AttributeModifier.Operation.ADD_VALUE));

}
