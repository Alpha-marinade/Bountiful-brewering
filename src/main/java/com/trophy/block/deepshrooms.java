package com.trophy.block;

import com.trophy.common.itemReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class deepshrooms extends Block {
    public deepshrooms(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (world.getBlockState(pos.down())== Blocks.DEEPSLATE.getDefaultState()||
                world.getBlockState(pos.down())== Blocks.STONE.getDefaultState()||
                world.getBlockState(pos.down())== Blocks.DIRT.getDefaultState()||
                world.getBlockState(pos.down())== Blocks.DIORITE.getDefaultState()||
                world.getBlockState(pos.down())== Blocks.ANDESITE.getDefaultState()||
                world.getBlockState(pos.down())== Blocks.GRANITE.getDefaultState()||
                world.getBlockState(pos.down())== itemReg.SHROOMSSLATE.getDefaultState()||
                world.getBlockState(pos.down())== Blocks.GRASS_BLOCK.getDefaultState()){
            return true;
        }
        return false;
    }
}
