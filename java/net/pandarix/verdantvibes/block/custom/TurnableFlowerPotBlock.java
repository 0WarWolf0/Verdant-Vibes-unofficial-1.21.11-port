package net.pandarix.verdantvibes.block.custom;

import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.pandarix.verdantvibes.VerdantVibes;
import org.jetbrains.annotations.Nullable;

/** A flower pot that can be turned by sneaking + right-clicking with an empty hand. */
public class TurnableFlowerPotBlock extends FlowerPotBlock {
	/** Granted from onUse; the advancement uses the "impossible" trigger. */
	private static final Identifier ADVANCEMENT_ID = Identifier.of(VerdantVibes.MOD_ID, "potters_pivot");

	public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;

	public TurnableFlowerPotBlock(Block content, Settings settings) {
		super(content, settings);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (player.isSneaking()) {
			if (world.getServer() != null) {
				AdvancementEntry advancement = world.getServer().getAdvancementLoader().get(ADVANCEMENT_ID);
				if (advancement != null && player instanceof ServerPlayerEntity serverPlayer) {
					serverPlayer.getAdvancementTracker().grantCriterion(advancement, "planted_pot");
				}
			}
			world.setBlockState(pos, state.rotate(BlockRotation.CLOCKWISE_90), Block.NOTIFY_ALL);
			world.playSound(null, pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 0.25F, 1.5F);
			return ActionResult.SUCCESS;
		}
		return super.onUse(state, world, pos, player, hit);
	}

	/**
	 * Plays the planting sound when a pot is filled. (1.21.5+ no longer passes the new state to
	 * onStateReplaced, so this uses onBlockAdded, like the Forge version of the mod.)
	 */
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if (oldState.getBlock() instanceof FlowerPotBlock && oldState.getBlock() != state.getBlock()) {
			world.playSound(null, pos, SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS);
		}
		super.onBlockAdded(state, world, pos, oldState, notify);
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

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}
}
