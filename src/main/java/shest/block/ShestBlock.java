package shest.block;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import shest.entity.ShestBlockEntity;
import shest.registry.ContainerRegistry;

public class ShestBlock extends Block implements BlockEntityProvider {
	ShestBlockEntity shestBlockEntity;

	public ShestBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		shestBlockEntity = new ShestBlockEntity();
		return shestBlockEntity;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			ContainerProviderRegistry.INSTANCE.openContainer(ContainerRegistry.SHEST_BLOCK_CONTAINER, player, (buffer) -> buffer.writeBlockPos(pos));
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient() && !player.isCreative()) {
			for (int i = 0; i <  shestBlockEntity.shestInventory.getInvSize(); ++i) {
				player.dropItem(shestBlockEntity.shestInventory.getInvStack(i).copy(), false);
			}
		}

		super.onBreak(world, pos, state, player);
	}
}
