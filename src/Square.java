import java.awt.Color;
import java.awt.Graphics;

public class Square {
	boolean walls[];
	private Color c;
	private int drawX, drawY, drawWidth, drawHeight, i, j;
	private int numWalls;
	
	public static int WALL_WIDTH = 4;
	
	public Square(Color c, int drawX, int drawY, int drawWidth, int drawHeight, int i, int j) {
		this.numWalls = 4;
		walls = new boolean[this.numWalls];
		this.c = c;
		this.i = i;
		this.j = j;
		this.drawX = drawX; 
		this.drawY = drawY; 
		this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
		for (int k = 0; k < walls.length; k++) {
			walls[k] = true;
		}
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	public Color getColor() {
		return this.c;
	}
	
	public void breakWall(int idx) {
		this.walls[idx] = false;
	}
	
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(this.drawX, this.drawY, this.drawWidth, this.drawHeight);
		g.setColor(Color.BLACK);
		if (walls[0]) // TOP
			g.fillRect(this.drawX, this.drawY, this.drawWidth, this.WALL_WIDTH);
		if (walls[1]) // RIGHT
			g.fillRect(this.drawX + this.drawWidth, this.drawY, this.WALL_WIDTH, this.drawHeight);
		if (walls[2]) // BOTTOM
			g.fillRect(this.drawX, this.drawY + this.drawHeight, this.drawWidth, this.WALL_WIDTH);
		if (walls[3]) // LEFT
			g.fillRect(this.drawX, this.drawY, this.WALL_WIDTH, this.drawHeight);
	}
}
