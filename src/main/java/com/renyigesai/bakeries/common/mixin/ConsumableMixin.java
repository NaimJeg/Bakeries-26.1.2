package com.renyigesai.bakeries.common.mixin;

import com.renyigesai.bakeries.common.items.RepeatEatItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Consumable.class)
public class ConsumableMixin {

    @Inject(method = "onConsume",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;gameEvent(Lnet/minecraft/core/Holder;)V"), cancellable = true)
   public void onConsume(Level level, LivingEntity user, ItemStack stack, CallbackInfoReturnable<ItemStack> cir){
        if (stack.getItem() instanceof RepeatEatItem){
            if (RepeatEatItem.isRepeatEat(stack)){
                RepeatEatItem.eatOrBreak(user,stack);
            }
            cir.setReturnValue(stack);
        }
   }

}
