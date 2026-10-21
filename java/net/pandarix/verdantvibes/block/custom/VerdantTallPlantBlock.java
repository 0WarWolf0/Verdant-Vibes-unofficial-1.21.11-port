package net.pandarix.verdantvibes.block.custom;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class VerdantTallPlantBlock extends TallFlowerBlock {
	public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;

	private final VoxelShape voxelShape;
	private final List<Block> mayPlaceOn;

	public VerdantTallPlantBlock(Settings settings, List<Block> mayPlaceOn, VoxelShape voxelShape) {
		super(settings);
		this.mayPlaceOn = mayPlaceOn;
		this.voxelShape = voxelShape;
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}

	// Behaviour kept exactly as in the 1.20.1 build (note: it tests the block being placed, not the one below).
	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		if (this.mayPlaceOn.contains(state.getBlock())) {
			return state.isOf(Blocks.WATER) ? !world.getBlockState(pos.down()).isOf(Blocks.WATER) : true;
		}
		return super.canPlaceAt(state, world, pos);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d offset = state.getModelOffset(pos);
		return this.voxelShape.offset(offset.x, offset.y, offset.z);
	}

	/** Slows entities walking through the plant. */
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity,
			EntityCollisionHandler handler, boolean flag) {
		double d0 = Math.abs(entity.getVelocity().y);
		if (d0 < 0.1 && !entity.isSneaking()) {
			double d1 = 0.4 + d0 * 0.8;
			entity.setVelocity(entity.getVelocity().multiply(d1, 1.0, d1));
		}
		super.onEntityCollision(state, world, pos, entity, handler, flag);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return ctx.getWorld().isInBuildLimit(ctx.getBlockPos().up())
				&& ctx.getWorld().getBlockState(ctx.getBlockPos().up()).canReplace(ctx)
				? this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
				: super.getPlacementState(ctx);
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(FACING);
	}
}
