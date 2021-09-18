import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Maze {
	private int rows;
	private int cols;
	private int borderWidth;
	private int borderHeight;
	private int wallWidth;
	private int wallHeight;
	private Shlomi shlomi;
	
	private Square grid[][];
	
	public Maze(int cols, int rows, int borderWidth, int borderHeight) {
		this.rows = rows;
		this.cols = cols;
		
		this.grid = new Square[rows][cols];
		this.borderWidth = borderWidth;
		this.borderHeight = borderHeight;
		this.wallWidth = borderWidth / cols;
		this.wallHeight = borderHeight / rows;
		
		this.shlomi = new Shlomi(0, 0, this);
		
		initGrid();
	}
	
	public int getWallWidth() {
		return this.wallWidth;
	}
	
	public int getWallHeight() {
		return this.wallHeight;
	}
	
	public Square[][] getGrid() {
		return this.grid;
	} 
	
	public ArrayList<Square> getNeighbors(int i, int j) {
		ArrayList<Square> neighbors = new ArrayList<Square>();
		Square temp;
		if (i - 1 >= 0) {
			temp = this.grid[i-1][j];
			if (temp.getColor() != Shlomi.SHLOMI_WAS_HERE_COLOR)
				neighbors.add(temp);
		}
		if (i + 1 < this.grid.length) {
			temp = this.grid[i+1][j];
			if (temp.getColor() != Shlomi.SHLOMI_WAS_HERE_COLOR)
				neighbors.add(temp);
		}
		if (j - 1 >= 0) {
			temp = this.grid[i][j-1];
			if (temp.getColor() != Shlomi.SHLOMI_WAS_HERE_COLOR)
				neighbors.add(temp);
		}
		if (j + 1 < this.grid[0].length) {
			temp = this.grid[i][j+1];
			if (temp.getColor() != Shlomi.SHLOMI_WAS_HERE_COLOR)
				neighbors.add(temp);
		}
			
		return neighbors;
	}
	
	private void initGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				this.grid[i][j] = new Square(Color.green, 
						j * wallWidth, i * wallHeight, 
						wallWidth, wallHeight, i, j);
			}
		}
	}
	
	public void update() {
		this.shlomi.update();
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Square s = grid[i][j];
				s.paint(g);
			}
		}
		this.shlomi.paint(g);
	}
}
