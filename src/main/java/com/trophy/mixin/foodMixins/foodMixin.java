package com.trophy.mixin.foodMixins;

import com.trophy.common.itemReg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class foodMixin {
    private long containerDate;
    private long startDate;
    private long endDate;
    public long expirationTime;


    public foodMixin () {
        this.containerDate = 0;
        this.startDate=0;
        this.endDate=0;

    }
    public long getcontainerDate () {
        return containerDate;
    }
    public long getStartDate() {
        return startDate;
    }
    public long getEndDateDate() {
        return endDate;
    }
    public void endDataSave(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateSubNbt("Expiration");
        nbt.putLong("ExpirationDate", endDate);
    }

    public void endDateLoad(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateSubNbt("Expiration");
        if (nbt!=null){
            endDate = nbt.getLong("ExpirationDate");
        }
    }
    public void containerDateSave(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateSubNbt("Expiration");
        nbt.putLong("ExpirationCounter", containerDate);
    }

    public void containerDateLoad(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateSubNbt("Expiration");
        if (nbt!=null){
            containerDate = nbt.getLong("ExpirationCounter");
        }
    }
    public void startDataSave(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateSubNbt("Expiration");
        nbt.putLong("craftDate", startDate);
    }

    public void startDateLoad(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateSubNbt("Expiration");
        if (nbt!=null){
            startDate = nbt.getLong("craftDate");
        }
    }
    @Shadow public abstract boolean isFood();
    @Inject(at = @At("HEAD"), method = "onCraft")
    public void onCraft(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci){
        if(this.isFood()){
            this.startDate=world.getTime()/24000;
            startDataSave(stack);



            Identifier identifier = Registries.ITEM.getId(stack.getItem());
            System.out.println(identifier.getPath());


        }
    }
    @Inject(at = @At("HEAD"), method = "inventoryTick")
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci){
        if (this.isFood()){

            if(!stack.getOrCreateSubNbt("Expiration").contains("craftDate", NbtElement.LONG_TYPE)){
                System.out.println("NULL");
                this.startDate=world.getTime()/24000;
                startDataSave(stack);
            }
            endDateLoad(stack);
            containerDateLoad(stack);
            endDate =startDate+10-containerDate/2;
            if (endDate<=world.getTime()/24000){
                if(entity.isPlayer()){
                    PlayerEntity player = (PlayerEntity) entity;
                    player.getInventory().setStack(slot,new ItemStack(itemReg.ROT,stack.getCount()));

                }
            }
            endDataSave(stack);






        }
    }
}
