package com.renyigesai.bakeries.common.utils;

import net.minecraft.client.Minecraft;

public interface ITextMeasurer {

    int getLength(String string,int maxLength);

    int getLength(String string);

}
