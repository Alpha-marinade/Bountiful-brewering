package com.trophy.mixin;

import com.trophy.block.truffleCandle;
import com.trophy.common.itemReg;
import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.RailPlacementHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static com.trophy.block.truffleCandle.LIGHT;
@Mixin(PotionEntity.class)
public class extinguishCandle  extends ThrownItemEntity  implements FlyingItemEntity {
    public extinguishCandle(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(at = @At("HEAD"), method = "extinguishFire")
    private void fire(BlockPos pos, CallbackInfo ci) {
        BlockState blockState = this.getWorld().getBlockState(pos);
        if (blockState== itemReg.TRUFFLE_CANDLE.getDefaultState().with(LIGHT ,true)) {
            this.getWorld().syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, pos, 0);
            this.getWorld().setBlockState(pos, blockState.with(LIGHT, Boolean.valueOf(false)));
            this.getWorld().playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 1.0F);
        }
    }
    @Override
    public Item getDefaultItem() {
        return Items.SPLASH_POTION;
    }
}



