package me.quclient.Utils;

public class Animations {
	
	private int tick;
	private boolean isEnable;
	private int Duration;
	private boolean Turn;
	
	public Animations(int dur) {
		this.Duration = dur;
		tick = 0;
		isEnable = false;
	}
	
	public Animations(int dur,boolean t) {
		this.Duration = dur;
		tick = 0;
		isEnable = false;
		Turn = t;
	}
	
	
	public void reset() {
		isEnable = false;
		tick = 0;
	}
	
	public boolean isDone() {
		return tick == 0;
	}
	
	public void enable() {
		if(!this.isEnable) {
			this.isEnable = true;
			this.tick = this.Duration;
		}
	}
	
	public int getUp() {
		return this.Duration - this.tick;
	}
	
	public int getUp(int min,int max) {
		return (int) MathUtils.map(this.Duration - this.tick,0,Duration,min,max);
	}
	
	public float getUp(float min,float max) {
		return (float) MathUtils.map(this.Duration - this.tick,0,Duration,min,max);
	}
	
	public int getDown(int min,int max) {
		return (int) MathUtils.map(this.tick,0,Duration,min,max);
	}
	
	public float getDown(float min,float max) {
		return (float) MathUtils.map(this.tick,0,Duration,min,max);
	}
	
	public int getDown() {
		return this.tick;
	}
	
	public void StartOrTickAnimations() {
		if(isEnable) {
			if(tick > 0) {
				tick -= 1;
			} else if(!Turn) {
				isEnable = false;
			}
		}
	}

}
