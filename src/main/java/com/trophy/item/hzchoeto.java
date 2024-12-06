package com.trophy.item;

import com.trophy.common.itemReg;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.trophy.block.sporeFlowerFruct.SPORE;

public class hzchoeto extends Item {
    public hzchoeto(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        //System.out.println(context.getWorld().getBlockState(context.getBlockPos()));
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        world.setBlockState(pos.up(), itemReg.SPOREFLOWERFRUCT.getDefaultState().with(SPORE,true),3);
        return ActionResult.SUCCESS;
    }
}
