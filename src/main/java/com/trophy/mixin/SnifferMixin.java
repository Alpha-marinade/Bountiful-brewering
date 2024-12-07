package com.trophy.mixin;

import com.trophy.common.itemReg;
import com.trophy.entity.goal.findTruffleGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.GoToBedAndSleepGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(SnifferEntity.class)
public class SnifferMixin  extends AnimalEntity {

    protected SnifferMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return EntityType.SNIFFER.create(world);
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new TemptGoal(this, 1.25, Ingredient.ofItems(itemReg.TRUFFLE), false));
        this.goalSelector.add(3, new findTruffleGoal(this, 2, 96));
    }
}
