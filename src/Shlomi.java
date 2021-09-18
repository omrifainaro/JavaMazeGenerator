import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

class Pos {
	public int i, j;
	public Pos(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

public class Shlomi {
	private int i, j;
	private Maze maze;
	private static final Color SHLOMI_COLOR = Color.RED;
	public static final Color SHLOMI_WAS_HERE_COLOR = Color.YELLOW;
	private static final int PADDING = 20;
	private Random rand;
	
	Stack<Pos> prevPositions;
	
	public Shlomi(int i, int j, Maze maze) {
		this.i = i;
		this.j = j;
		this.maze = maze;
		
		this.rand = new Random();
		this.prevPositions = new Stack<Pos>();
	}
	
	public void breakWall(Square start, Square end) {
		int deltaI = end.getI() - start.getI();
		int deltaJ = end.getJ() - start.getJ();
		int wallStart, wallEnd;
		
		if (deltaI != 0) {
			wallStart = 1 + deltaI;
		} else {
			wallStart = 2 - deltaJ;
		}
		
		wallEnd = (wallStart + 2) % 4;
		
		start.breakWall(wallStart);
		end.breakWall(wallEnd);
	}
	
	public void update() {
		Square currentSquare = this.maze.getGrid()[this.i][this.j];
		Square nextSquare;
		currentSquare.setColor(this.SHLOMI_WAS_HERE_COLOR);
		
		ArrayList<Square> neighs = this.maze.getNeighbors(this.i, this.j);
		if (neighs.size() > 0) {
			nextSquare = neighs.get(this.rand.nextInt(neighs.size()));
			breakWall(currentSquare, nextSquare);
			prevPositions.push(new Pos(this.i, this.j));
			this.i = nextSquare.getI();
			this.j = nextSquare.getJ();
		}
		else {
			if (prevPositions.size() > 0) {
				Pos prev = this.prevPositions.pop();
				this.i = prev.i;
				this.j = prev.j;
			}
		}
		
	}
	
	public void paint(Graphics g) {
		g.setColor(this.SHLOMI_COLOR);
		g.fillOval(this.j * this.maze.getWallWidth() + this.PADDING / 2, 
				this.i * this.maze.getWallHeight() + this.PADDING / 2, 
				this.maze.getWallWidth() - this.PADDING,
				this.maze.getWallHeight() - this.PADDING
		);
	}
}
