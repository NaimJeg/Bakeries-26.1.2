package com.renyigesai.bakeries.common.mixin;

import com.renyigesai.bakeries.common.manager.ClientAdvancementsManager;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.multiplayer.ClientAdvancements;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ClientAdvancements.class)
public class ClientAdvancementsMixin implements ClientAdvancementsManager {

    @Shadow
    @Final
    @Mutable
    private Map<AdvancementHolder, AdvancementProgress> progress;

    @Override
    public Map<AdvancementHolder, AdvancementProgress> getProgress() {
        return this.progress;
    }
}
