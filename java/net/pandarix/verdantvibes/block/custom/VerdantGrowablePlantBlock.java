package net.pandarix.verdantvibes.block.custom;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;

public class VerdantGrowablePlantBlock extends VerdantPlantBlock {
	private final VerdantTallPlantBlock grownPlant;

	public VerdantGrowablePlantBlock(Settings settings, List<Block> mayPlaceOn, VoxelShape voxelShape, VerdantTallPlantBlock grownPlant) {
		super(settings, mayPlaceOn, voxelShape);
		this.grownPlant = grownPlant;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		if (world.getBlockState(pos.up()).isAir()) {
			world.setBlockState(pos, this.grownPlant.getDefaultState()
					.with(FACING, state.get(FACING))
					.with(VerdantTallPlantBlock.HALF, DoubleBlockHalf.LOWER), Block.NOTIFY_ALL);
			world.setBlockState(pos.up(), this.grownPlant.getDefaultState()
					.with(FACING, state.get(FACING))
					.with(VerdantTallPlantBlock.HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
		}
		// Kept from the 1.20.1 build: the parent also drops an item.
		super.grow(world, random, pos, state);
	}
}
