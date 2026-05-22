package com.renyigesai.bakeries.common.manager;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;

import java.util.Map;

public interface ClientAdvancementsManager {
    Map<AdvancementHolder, AdvancementProgress> getProgress();
}
