package com.trophy.block;

import com.trophy.common.itemReg;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class sporeFlowerFruct  extends Block implements Fertilizable{
    public static final BooleanProperty SPORE = BooleanProperty.of("spore");
    public static final IntProperty STAGE = IntProperty.of("stage",0,25);
    public sporeFlowerFruct(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(SPORE, false).with(STAGE,0));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.AIR && !this.collidable ? true : super.canPathfindThrough(state, world, pos, type);
    }


    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        if(world.getBlockState(pos.up())==Blocks.AIR.getDefaultState()&&(world.getBlockState(pos.down(2))!=itemReg.SPOREFLOWERSTEM.getDefaultState())){
            if((world.getBlockState(pos.down())==Blocks.NETHERRACK.getDefaultState())||world.getBlockState(pos.down())==Blocks.CRIMSON_NYLIUM.getDefaultState()||world.getBlockState(pos.down())==itemReg.SPOREFLOWERSTEM.getDefaultState()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        if(world.getBlockState(pos.up())==Blocks.AIR.getDefaultState()&&(world.getBlockState(pos.down(2))!=itemReg.SPOREFLOWERSTEM.getDefaultState())){
            if((world.getBlockState(pos.down())==Blocks.NETHERRACK.getDefaultState())||world.getBlockState(pos.down())==Blocks.CRIMSON_NYLIUM.getDefaultState()||world.getBlockState(pos.down())==itemReg.SPOREFLOWERSTEM.getDefaultState()){
                return true;
            }
        }
       return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if(state.get(SPORE)) {
            world.setBlockState(pos.up(), this.getDefaultState().with(SPORE, true), 3);
        }
        else{   world.setBlockState(pos.up(), this.getDefaultState().with(SPORE, false), 3);}
        world.setBlockState(pos,itemReg.SPOREFLOWERSTEM.getDefaultState(),3);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if(world.getBlockState(pos.down())==Blocks.AIR.getDefaultState()){
            world.breakBlock(pos,false);
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if(!world.isClient()&&state.get(SPORE)){
            EntityType.BAT.spawn((ServerWorld) world,null,null,pos, SpawnReason.TRIGGERED,true,true);
            EntityType.BAT.spawn((ServerWorld) world,null,null,pos, SpawnReason.TRIGGERED,true,true);
            EntityType.BAT.spawn((ServerWorld) world,null,null,pos, SpawnReason.TRIGGERED,true,true);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if(world.getBlockState(pos.down())!=Blocks.AIR.getDefaultState()&&world.getBlockState(pos.down())!=itemReg.SPOREFLOWERFRUCT.getDefaultState()){
            return true;
        }
        return false;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SPORE,STAGE);

    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(state.get(STAGE)<25 && canGrow(world,random,pos,state)){
            int sta = state.get(STAGE)+1;
            world.setBlockState(pos,world.getBlockState(pos).with(STAGE,sta));
            }
        if(state.get(STAGE)>=25){
            this.grow(world,random,pos,state);
        }
        }
}


