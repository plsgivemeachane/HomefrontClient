package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;
import me.quclient.EventHandler.EventCancelable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class BreakEvent extends EventCancelable {

	
	public BlockPos pos;
	public EnumFacing face;
	
	public BreakEvent(BlockPos pos, EnumFacing face) {
		this.pos = pos;
		this.face = face;
	}
	
	
}
