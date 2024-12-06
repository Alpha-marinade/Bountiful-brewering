package com.trophy.mixin.foodMixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class foodAdditives {

    @Inject(at = @At("HEAD"), method = "finishUsing")
    public void eat(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){

    }
}
