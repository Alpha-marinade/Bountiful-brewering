package com.trophy.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.client.ItemTooltipCallback;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class foodToolTip {
    @Environment(EnvType.CLIENT)
    public static void init(){
        ItemTooltipCallback.EVENT.register(((stack, context, tooltip) ->{
            NbtCompound nbt = stack.getSubNbt("Expiration");
            if (nbt !=null){

                tooltip.add(Text.translatable("itemTooltip.bountiful_brewering.food_create",nbt.getLong("craftDate")).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("itemTooltip.bountiful_brewering.food_end",nbt.getLong("ExpirationDate")-nbt.getLong("craftDate")).formatted(Formatting.GRAY));
            }

        } ));
    }
}
