package com.renyigesai.bakeries.common.potion;

import com.renyigesai.bakeries.common.init.BakeriesMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

import java.util.ArrayList;
import java.util.Iterator;

public class EnjoyMobEffect extends MobEffect {
    public EnjoyMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -13312);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return amplifier >= 0;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(serverLevel, pLivingEntity, pAmplifier);
        if (pLivingEntity.getHealth() < pLivingEntity.getMaxHealth()) {
            float livingEntityHealth = pLivingEntity.getHealth();
            int lv = pAmplifier==0?1:pAmplifier;
            if (livingEntityHealth > 0.0F) {
                pLivingEntity.setHealth(livingEntityHealth + (float) 0.05*lv);
            }
        }
        Iterator<MobEffectInstance> itr = pLivingEntity.getActiveEffects().iterator();
        ArrayList<Holder<MobEffect>> compatibleEffects = new ArrayList<>();
        MobEffectInstance selectedEffect;
        while(itr.hasNext()) {
            selectedEffect = itr.next();
            MobEffect effect = selectedEffect.getEffect().value();

            if (effect.getCategory() == MobEffectCategory.HARMFUL) {
                compatibleEffects.add(selectedEffect.getEffect());
            }
        }
        if (!compatibleEffects.isEmpty()){
            for (Holder<MobEffect> compatibleEffect : compatibleEffects) {
                pLivingEntity.removeEffect(compatibleEffect);
                break;
            }
        }
        return true;
    }

    @EventBusSubscriber
    public static class EnjoyMobPotion{

        @SubscribeEvent
        public static void onLivingDamageEvent(LivingDamageEvent.Pre event){
            Entity entity = event.getSource().getEntity();
            float originalDamage = event.getOriginalDamage();
            if (entity instanceof LivingEntity living){
                if (living.hasEffect(BakeriesMobEffects.ENJOY)){
                    int amplifier = living.getEffect(BakeriesMobEffects.ENJOY).getAmplifier();
                    float newDamage =  originalDamage + (2f * (amplifier + 1));
                    event.setNewDamage(newDamage);
                }
            }
        }
    }

}
