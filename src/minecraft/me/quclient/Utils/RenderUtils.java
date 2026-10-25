package me.quclient.Utils;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.AxisAlignedBB;

public class RenderUtils {
	
	
	
	public static void drawRoundedRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, float radius, int color) {
		drawRoundedRect(paramXStart,paramYStart,paramXEnd,paramYEnd,radius,color,true);
	}
	
	public static void drawRoundedRect(double d, double paramYStart, double paramXEnd, double paramYEnd, float radius, int color, boolean popPush) {
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;

        double z = 0;
        if (d > paramXEnd) {
            z = d;
            d = paramXEnd;
            paramXEnd = z;
        }

        if (paramYStart > paramYEnd) {
            z = paramYStart;
            paramYStart = paramYEnd;
            paramYEnd = z;
        }

    	double x1 = (double)(d + radius);
    	double y1 = (double)(paramYStart + radius);
    	double x2 = (double)(paramXEnd - radius);
    	double y2 = (double)(paramYEnd - radius);

        if (popPush) GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(1);

        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(GL11.GL_POLYGON);
    
        double degree = Math.PI / 180;
        for (double i = 0; i <= 90; i += 1)
        	GL11.glVertex2d(x2 + Math.sin(i * degree) * radius, y2 + Math.cos(i * degree) * radius);
        for (double i = 90; i <= 180; i += 1)
        	GL11.glVertex2d(x2 + Math.sin(i * degree) * radius, y1 + Math.cos(i * degree) * radius);
        for (double i = 180; i <= 270; i += 1)
        	GL11.glVertex2d(x1 + Math.sin(i * degree) * radius, y1 + Math.cos(i * degree) * radius);
        for (double i = 270; i <= 360; i += 1)
        	GL11.glVertex2d(x1 + Math.sin(i * degree) * radius, y2 + Math.cos(i * degree) * radius);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        if (popPush) GL11.glPopMatrix();
    }
	
	public static void drawBoundingBox(AxisAlignedBB aa)  {
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		worldRenderer.startDrawingQuads();
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.maxZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.maxZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawingQuads();
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.maxZ);
		tessellator.draw();
	}
	
	public static void drawSolidBlockESP(double x, double y, double z, float red, float green, float blue, float alpha) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
		// GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(red, green, blue, alpha);
		drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1D, y + 1D, z + 1D));
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		worldRenderer.startDrawing(3);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawing(3);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		tessellator.draw();
		worldRenderer.startDrawing(1);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.func_181662_b(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.func_181662_b(aa.minX, aa.maxY, aa.maxZ);
		tessellator.draw();
	}
	
	public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 771);
		// GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(red, green, blue, alpha);
		drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width , y + height, z + width));
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	
	public static void drawRoundedCornerRect(float x, float y, float x1, float y1, float radius, int color) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
        final boolean hasCull = GL11.glIsEnabled(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_CULL_FACE);

        
        Color c = new Color(color);
        GL11.glColor4f(c.getRed(),c.getBlue(),c.getGreen(),c.getAlpha());
        drawRoundedCornerRect(x, y, x1, y1, radius);

        GL11. glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
	
	public static void drawRoundedCornerRect(float x, float y, float x1, float y1, float radius) {
		GL11.glBegin(GL11.GL_POLYGON);

        float xRadius = (float) Math.min((x1 - x) * 0.5, radius);
        float yRadius = (float) Math.min((y1 - y) * 0.5, radius);
        quickPolygonCircle(x + xRadius,y + yRadius, xRadius, yRadius,180,270);
        quickPolygonCircle(x1 - xRadius,y + yRadius, xRadius, yRadius,90,180);
        quickPolygonCircle(x1 - xRadius,y1 - yRadius, xRadius, yRadius,0,90);
        quickPolygonCircle(x + xRadius,y1 - yRadius, xRadius, yRadius,270,360);

        GL11.glEnd();
    }
	
	private static void quickPolygonCircle(float x, float y, float xRadius, float yRadius, int start, int end) {
        for(int i = end; i >= start; i -= 4) {
            GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * xRadius, y + Math.cos(i * Math.PI / 180.0D) * yRadius);
        }
    }
	
}
