package com.trophy.block;

import com.trophy.common.itemReg;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BlackGrapeVine extends  MultifaceGrowthBlock implements Fertilizable {
    public static final IntProperty STAGE = IntProperty.of("stage",0,25);
    private final LichenGrower grower = new LichenGrower(this);


    public BlackGrapeVine(Settings settings) {
        super(settings);

    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(STAGE);

    }


    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {

         return Direction.stream().anyMatch(direction -> this.grower.canGrow(state, world, pos, direction.getOpposite()));

    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        if (state.get(STAGE)>=25){
            return false;
        }
        return true;

    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (random.nextBetweenExclusive(0,100)>90) {
            this.grower.grow(state, world, pos, random);

        }

    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (state.get(STAGE)<25) {
            world.setBlockState(pos, state.with(STAGE, state.get(STAGE) + 1));
        }
        this.grow(world,random,pos,state);
    }


    @Override
    public LichenGrower getGrower() {
       return this.grower;
    }



}
