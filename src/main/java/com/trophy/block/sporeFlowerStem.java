package com.trophy.block;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.trophy.common.itemReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import static com.trophy.block.sporeFlowerFruct.SPORE;
import static net.minecraft.registry.tag.BlockTags.CLIMBABLE;

public class sporeFlowerStem extends Block  implements Fertilizable {
    public static final IntProperty STAGE = IntProperty.of("stage",0,25);
    public sporeFlowerStem(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(STAGE,0));
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if(world.getBlockState(pos.down())== Blocks.NETHERRACK.getDefaultState()||world.getBlockState(pos.down())== Blocks.CRIMSON_NYLIUM.getDefaultState()||world.getBlockState(pos.down())== itemReg.SPOREFLOWERSTEM.getDefaultState() ){
            return true;
        }
        return false;
    }
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if(world.getBlockState(pos.down())==Blocks.AIR.getDefaultState()){
            world.breakBlock(pos,false);
        }
    }
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.AIR && !this.collidable ? true : super.canPathfindThrough(state, world, pos, type);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        if (world.getBlockState(pos.up())==Blocks.AIR.getDefaultState()){
            return true;
        }
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos.up())==Blocks.AIR.getDefaultState()){
            return true;
        }
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos.up(),itemReg.SPOREFLOWERSTEM.getDefaultState());
        if (world.getBlockState(pos.up(2))==itemReg.SPOREFLOWERFRUCT.getDefaultState().with(SPORE,true)){
            world.setBlockState(pos.up(2),itemReg.SPOREFLOWERFRUCT.getDefaultState().with(SPORE,true));
        }




    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(state.get(STAGE)<25 && canGrow(world,random,pos,state)){
            int sta = state.get(STAGE)+1;
            world.setBlockState(pos,world.getBlockState(pos).with(STAGE,sta));
        }
        if(state.get(STAGE)>=25){
            world.setBlockState(pos,world.getBlockState(pos).with(STAGE,0));
            this.grow(world,random,pos,state);
        }
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STAGE);

    }

}
