package me.quclient.Utils;

import java.util.List;

import com.google.common.base.Predicates;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class Rotations {
	public float yaw,pitch;

	public Rotations(float yaw, float pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public static Rotations transfer(float[] rotations) {
		return new Rotations(rotations);
	}


	public Rotations() {
		this.yaw = 0f;
		this.pitch = 0f;
	}
	
	public Rotations(float[] src) {
		this.yaw = src[0];
		this.pitch = src[1];
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
	public float[] getValue() {
		return new float[] {this.yaw,this.pitch};
	}
	
	/**
	 * 
	 * Vulcan check GCD of rotation whether the sensitivity > 200 
	 * 
	 * @param previous : event.yaw, event.pitch
	 * @param now : new rotation
	 * @return actual (?) rotation
	 */	
	public static Rotations fixRotation(Rotations prev, Rotations now) {
		//Create Rotations Object
		Rotations rotation = new Rotations();
		
		//Simulate the vanilla sensitivity handling
		float sensitivity = Minecraft.getMinecraft().gameSettings.mouseSensitivity;
		float f = sensitivity * 0.6F + 0.2F;
		float gcd = f * f * f * 1.2F;
		
		//get real Yaw
		float dYaw = now.yaw - prev.yaw;
		dYaw -= dYaw % gcd;
		rotation.yaw = prev.yaw + dYaw;
		
		//get real Pitch
		float dPitch = now.pitch - prev.pitch;
		dPitch -= dPitch % gcd;
		rotation.pitch = prev.pitch + dPitch;
		
		return rotation;
	}
	
	
	/**
	 * Get Head Position of Player
	 * 
	 * @param Bounding box
	 * @return Vector Hed
	 */
	public static Vec3 getHead(AxisAlignedBB bb) {
		return new Vec3(bb.minX + (bb.maxX - bb.minX) * 0.5, bb.maxY - 0.4, bb.minZ + (bb.maxZ - bb.minZ) * 0.5);
	}
	
	/**
	 * Get Rotations Vector
	 * 
	 * @param Rotations
	 * @return Vector
	 */
	public static Vec3 rotationVec(Rotations rots) {
		final float yaw = rots.yaw;
		final float pitch = rots.pitch;
		float f = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3((double)(f1 * f2), (double)f3, (double)(f * f2));
	}
	
	/**
	 * Limit the angle from being overload
	 * 
	 * @param currentRotation
	 * @param targetRotation
	 * @param turnSpeed
	 * 
	 * @return new Rotations
	 */
	public static Rotations limitAngleChange(Rotations currentRotation, Rotations targetRotation, float turnSpeed) {
        final float yawDifference = getAngleDifference(targetRotation.yaw, currentRotation.yaw);
        final float pitchDifference = getAngleDifference(targetRotation.pitch, currentRotation.pitch);
        return new Rotations(currentRotation.yaw + ((yawDifference > turnSpeed) ? turnSpeed : Math.max(yawDifference, -turnSpeed)), currentRotation.pitch + ((pitchDifference > turnSpeed) ? turnSpeed : Math.max(pitchDifference, -turnSpeed)));
    }
	
	public static float getDifferent(Rotations r1,Rotations r2) {
		final float yawDifference = getAngleDifference(r1.yaw, r2.yaw);
        final float pitchDifference = getAngleDifference(r1.pitch, r2.pitch);
        
        return yawDifference + pitchDifference;
	}

	
	/**
	 * Get the different between 2 angle (yaw || float)
	 * 
	 * @param angle a
	 * @param angle b
	 * 
	 * @return different (float)
	 */
	public static float getAngleDifference(final float a, final float b) {
        return ((a - b) % 360.0f + 540.0f) % 360.0f - 180.0f;
    }
	
	
	/**
	 * Smooth the angle src to dst with some speed
	 * 
	 * 
	 * @param dst
	 * @param src
	 * @param speed
	 * 
	 * @return new Rotations
	 */
	public static Rotations smoothAngle(Rotations dst, Rotations src, float speed) {
        float[] smoothedAngle = { src.yaw - dst.yaw, src.pitch - dst.pitch };
        smoothedAngle = constrainAngle(smoothedAngle);
        smoothedAngle[0] = src.yaw - smoothedAngle[0] / 100.0F * speed;
        smoothedAngle[1] = src.pitch - smoothedAngle[1] / 100.0F * speed;
        //System.out.println(smoothedAngle[0]);
        return new Rotations(smoothedAngle);
    }
	
	
	/**
	 * constrain the angle
	 * 
	 * 
	 * @param vector in
	 * @return vector out
	 */
	private static float[] constrainAngle(float[] vector) {
    	vector[0] = vector[0] % 360.0F;
        vector[1] = vector[1] % 360.0F;
        while (vector[0] <= -180.0F)
          vector[0] = vector[0] + 360.0F; 
        while (vector[1] <= -180.0F)
          vector[1] = vector[1] + 360.0F; 
        while (vector[0] > 180.0F)
          vector[0] = vector[0] - 360.0F; 
        while (vector[1] > 180.0F)
          vector[1] = vector[1] - 360.0F; 
        return vector;
    }
	
	/**
	 * Get rotations from entity
	 * 
	 * 
	 * @param entity
	 * 
	 * @return the Rotations object
	 */
	public static Rotations getRotations(EntityLivingBase entity) {
		double diffX = entity.posX - Minecraft.getMinecraft().thePlayer.posX + (entity.posX - entity.lastTickPosX) * 1;
	    double diffY = entity.posY - Minecraft.getMinecraft().thePlayer.posY - 1.25;
	    double diffZ = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ + (entity.posZ - entity.lastTickPosZ) * 1;
	    double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
	    float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
	    float pitch = (float)-(Math.atan2(diffY, dist) * 180.0D / Math.PI);
	    return new Rotations( yaw, pitch );
	}
	
	/**
	 * Idk
	 * 
	 * @param block
	 * @param face
	 * @return float[] of 4 elements
	 */
	public static float[] aimBlock(final BlockPos block, final EnumFacing face) {
		Vec3 vec = new Vec3(block);
		double xx = 0;double yy = 0;double zz = 0;
		double xxx = 0.5;double yyy = 0.5;double zzz = 0.5;
		if(face == EnumFacing.DOWN) {
			yy = -0.5;
		}else if(face == EnumFacing.UP) {
			yy = 0.5;
		}else if(face == EnumFacing.WEST) {
			xx = -0.5;
			zzz = Minecraft.getMinecraft().thePlayer.posZ - vec.zCoord;
		}else if(face == EnumFacing.NORTH) {	
			xxx = Minecraft.getMinecraft().thePlayer.posX - vec.xCoord;
			zz = -0.5;			
		}else if(face == EnumFacing.EAST) {
			zzz = Minecraft.getMinecraft().thePlayer.posZ - vec.zCoord;
			xx = 0.5;
		}else if(face == EnumFacing.SOUTH) {
			xxx = Minecraft.getMinecraft().thePlayer.posX - vec.xCoord;
			zz = 0.5;
		}
		double diffX = vec.xCoord + xxx + xx - Minecraft.getMinecraft().thePlayer.posX;
		double diffY = vec.yCoord + 0.5 + yy
				- (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		double diffZ = vec.zCoord + zzz + zz - Minecraft.getMinecraft().thePlayer.posZ;
		
		diffY += 0.25;
		
		double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
		float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] {
				(float) (Minecraft.getMinecraft().thePlayer.rotationYaw
						+ (yaw - Minecraft.getMinecraft().thePlayer.rotationYaw)),
				(float) (Minecraft.getMinecraft().thePlayer.rotationPitch
						+ (pitch - Minecraft.getMinecraft().thePlayer.rotationPitch)),(float) (vec.xCoord + 0.5 + xx),(float) (vec.yCoord + 0.5 + yy),(float) (vec.zCoord + 0.5 + zz) };
    }
	
	
	/** 
	 * Return true if EntityBeSeen by thePlayer
	 * @param entity (Entity)
	 * 
	 * @return boolean
	 */
	public static boolean canEntityBeSeen(Entity entity) {
	    Vec3 vec1 = new Vec3(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight(), Minecraft.getMinecraft().thePlayer.posZ);
	    AxisAlignedBB box = entity.getEntityBoundingBox();
	    Vec3 vec2 = new Vec3(entity.posX, entity.posY + (entity.getEyeHeight() / 1.32F), entity.posZ);
	    double minx = entity.posX - 0.25D;
	    double maxx = entity.posX + 0.25D;
	    double miny = entity.posY;
	    double maxy = entity.posY + Math.abs(entity.posY - box.maxY);
	    double minz = entity.posZ - 0.25D;
	    double maxz = entity.posZ + 0.25D;
	    boolean see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    vec2 = new Vec3(maxx, miny, minz);
	    see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    vec2 = new Vec3(minx, miny, minz);
	    see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    vec2 = new Vec3(minx, miny, maxz);
	    see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    vec2 = new Vec3(maxx, miny, maxz);
	    see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    vec2 = new Vec3(maxx, maxy, minz);
	    see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    vec2 = new Vec3(minx, maxy, minz);
	    see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    vec2 = new Vec3(minx, maxy, maxz - 0.1D);
	    see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    vec2 = new Vec3(maxx, maxy, maxz);
	    see = (Minecraft.getMinecraft().theWorld.rayTraceBlocks(vec1, vec2) == null);
	    if (see)
	      return true; 
	    return false;
	  }
	
	public static Entity raycastEntity(double range,float yaw,float pitch) {
		return raycastEntity(range,yaw,pitch,new ObjEntityFillter());
	}
	
	private static Entity raycastEntity(final double range, final float yaw, final float pitch, final IEntityFilter entityFilter) {
		final Minecraft mc = Minecraft.getMinecraft();
        final Entity renderViewEntity = mc.getRenderViewEntity();

        if(renderViewEntity != null && mc.theWorld != null) {
            double blockReachDistance = range;
            final Vec3 eyePosition = renderViewEntity.getPositionEyes(1f);

            final float yawCos = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
            final float yawSin = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
            final float pitchCos = -MathHelper.cos(-pitch * 0.017453292F);
            final float pitchSin = MathHelper.sin(-pitch * 0.017453292F);

            final Vec3 entityLook = new Vec3(yawSin * pitchCos, pitchSin, yawCos * pitchCos);
            final Vec3 vector = eyePosition.addVector(entityLook.xCoord * blockReachDistance, entityLook.yCoord * blockReachDistance, entityLook.zCoord * blockReachDistance);
            final List<Entity> entityList = mc.theWorld.getEntitiesWithinAABBExcludingEntity(renderViewEntity, renderViewEntity.getEntityBoundingBox().addCoord(entityLook.xCoord * blockReachDistance, entityLook.yCoord * blockReachDistance, entityLook.zCoord * blockReachDistance).expand(1D, 1D, 1D));

            Entity pointedEntity = null;

            for(final Entity entity : entityList) {
                if(!entityFilter.canRaycast(entity))
                    continue;

                final float collisionBorderSize = entity.getCollisionBorderSize();
                final AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox().expand(collisionBorderSize, collisionBorderSize, collisionBorderSize);
                final MovingObjectPosition movingObjectPosition = axisAlignedBB.calculateIntercept(eyePosition, vector);

                if(axisAlignedBB.isVecInside(eyePosition)) {
                    if(blockReachDistance >= 0.0D) {
                        pointedEntity = entity;
                        blockReachDistance = 0.0D;
                    }
                }else if(movingObjectPosition != null) {
                    final double eyeDistance = eyePosition.distanceTo(movingObjectPosition.hitVec);

                    if(eyeDistance < blockReachDistance || blockReachDistance == 0.0D) {
                        if(entity == renderViewEntity.ridingEntity) {
                            if(blockReachDistance == 0.0D)
                                pointedEntity = entity;
                        }else{
                            pointedEntity = entity;
                            blockReachDistance = eyeDistance;
                        }
                    }
                }
            }

            return pointedEntity;
        }

        return null;
    }
	
	public interface IEntityFilter {
        default boolean canRaycast(final Entity entity) {
        	return true;
        };
    }
	
	public static class ObjEntityFillter implements IEntityFilter{
		
		public ObjEntityFillter() {
			
		}
		
		@Override
		public boolean canRaycast(Entity entity) {
			return IEntityFilter.super.canRaycast(entity);
		}
		
	}

	public static Rotations transfer(float f, float g) {
		return new Rotations(f,g);
	}
	
	public static float getAngleDifference2(final float a, final float b) {
        return ((((a - b) % 360F) + 540F) % 360F) - 180F;
    }
	
	/**
     * Calculate difference between two rotations
     *
     * @param a rotation
     * @param b rotation
     * @return difference between rotation
     */
    public static double getRotationDifference(final Rotations a, final Rotations b) {
        return Math.hypot(getAngleDifference2(a.getYaw(), b.getYaw()), a.getPitch() - b.getPitch());
    }
    
    public static Rotations getRotationsEntity(EntityLivingBase entity) {
    	return Rotations.getRotations(entity.posX, entity.posY + entity.getEyeHeight() - 0.4, entity.posZ);
    }
    
    /**
    *
    * @param posX
    * @param posY
    * @param posZ
    * @return
    */
	   public static Rotations getRotations(double posX, double posY, double posZ) {
	       EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
	       double x = posX - player.posX;
	       double y = posY - (player.posY + (double)player.getEyeHeight());
	       double z = posZ - player.posZ;
	       double dist = MathHelper.sqrt_double(x * x + z * z);
	       float yaw = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
	       float pitch = (float)(-(Math.atan2(y, dist) * 180.0 / 3.141592653589793));
	       return new Rotations(yaw,pitch);
	   }
	
}
