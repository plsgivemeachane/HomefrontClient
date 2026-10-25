package me.quclient.Utils;

public class Timers {
	
	private long start;
	
	public Timers() {
		start = System.currentTimeMillis();
	}
	
	public boolean hasPassed(long time) {
		if(System.currentTimeMillis() - start > time) {
			return true;
		}
		
		return false;
	}
	
	public void reset() {
		start = System.currentTimeMillis();
	}

}
