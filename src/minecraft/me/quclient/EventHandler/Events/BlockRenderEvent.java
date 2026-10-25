package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.EventCancelable;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class BlockRenderEvent extends EventCancelable {

	public Block block;
	public BlockPos pos;
	
	public BlockRenderEvent(Block b, BlockPos s) {
		this.block = b;
		this.pos = s;
	}
	
}
