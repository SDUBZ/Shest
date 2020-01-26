package shest.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import shest.inventory.ShestInventory;
import shest.registry.EntityRegistry;
import spinnery.util.InventoryUtilities;

public class ShestBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
	public ShestInventory shestInventory = new ShestInventory(27);

	public ShestBlockEntity() {
		super(EntityRegistry.ENTITY_SHEST);
	}


	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		shestInventory = new ShestInventory(27);
		InventoryUtilities.read(shestInventory, tag);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag = InventoryUtilities.write(shestInventory);
		super.toTag(tag);
		return tag;
	}

	@Override
	public void fromClientTag(CompoundTag tag) {
		super.fromTag(tag);
		InventoryUtilities.read(shestInventory, tag);
	}

	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		tag = InventoryUtilities.write(shestInventory);
		super.toTag(tag);
		return tag;
	}
}
