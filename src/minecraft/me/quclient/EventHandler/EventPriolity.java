package me.quclient.EventHandler;

public enum EventPriolity {
	
	HIGHEST(0),HIGH(1),NORMAL(2),LOW(3),IDLE(4);
	
	private int p;
	
	private EventPriolity(int p) {
		this.p = p;
	}

}
