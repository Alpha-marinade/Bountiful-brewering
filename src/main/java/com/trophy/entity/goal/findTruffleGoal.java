package com.trophy.entity.goal;

import com.trophy.common.itemReg;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

import java.util.EnumSet;

public class findTruffleGoal extends MoveToTargetPosGoal {

    public findTruffleGoal(PathAwareEntity mob, double speed, int range) {
        super(mob, speed, range);
        this.lowestY = -60;

        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos)== itemReg.SHROOMSSLATE.getDefaultState();
    }

    @Override
    protected boolean findTargetPos() {
        for(int x=-32; x<=32; x++){
            for (int z=-32; z<=32; z++){
              for(int y = -60; y<=320; y++){
                  BlockPos pos = this.mob.getBlockPos().add(x,y,z);
                  if (this.isTargetPos(this.mob.getWorld(), pos)){
                      this.targetPos=pos;
                      return true;
                  }
              }
            }
        }
        return false;
    }

    @Override
    public void tick() {
        System.out.println(this.targetPos);
        super.tick();
        if(this.mob.getPos().getX()==this.targetPos.getX()&&this.mob.getPos().getZ()==this.targetPos.getZ()){

        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    protected void startMovingToTarget() {
        super.startMovingToTarget();
    }

    @Override
    public boolean canStart() {
        return this.findTargetPos();
    }
}
