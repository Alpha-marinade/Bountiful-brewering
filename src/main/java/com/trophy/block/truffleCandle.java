package com.trophy.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class truffleCandle extends Block implements Waterloggable {
    private static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);
    protected final ParticleEffect particle;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty LIGHT =BooleanProperty.of("light");
    public truffleCandle(Settings settings, ParticleEffect particle) {
        super(settings);
        this.particle = particle;
        this.setDefaultState(this.stateManager.getDefaultState().with(LIGHT, false).with(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIGHT)) {
            double d = (double)pos.getX() + 0.5;
            double e = (double)pos.getY() + 0.6;
            double f = (double)pos.getZ() + 0.5;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);
        }

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT,WATERLOGGED);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).isOf(Items.FLINT_AND_STEEL )&&!state.get(LIGHT)) {
            world.setBlockState(pos,state.with(LIGHT,true),3);
            return ActionResult.SUCCESS;
        }
        if (player.getStackInHand(hand).isOf(Items.WATER_BUCKET )&&state.get(LIGHT)){
            world.setBlockState(pos,state.with(LIGHT,false),3);
            return ActionResult.SUCCESS;

        }
        return ActionResult.FAIL;
    }
    public static int getLuminance(BlockState currentBlockState) {
        boolean activated = currentBlockState.get(LIGHT);
        return activated ? 15 : 0;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down())!= Blocks.AIR.getDefaultState();
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        boolean bl = worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER;
        return this.getDefaultState()
                .with(WATERLOGGED, Boolean.valueOf(bl))
                .with(LIGHT, Boolean.valueOf(false));
    }



}
