import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeFrame extends JPanel implements Runnable {
	private JFrame frame;
	private int width, height;
	private Thread mainLoopThread;
	private Maze maze;
	public static Color BG_COLOR = Color.BLACK;
	public static int FRAME_RATE = 1000;
	
	public static int MAZE_COLS = 10;
	public static int MAZE_ROWS = 6;
	
	public MazeFrame(int width, int height) {
		this.width = width;
		this.height = height;
		this.frame = new JFrame("Maze Generator");
		this.frame.setSize(width, height);
		this.frame.setBackground(MazeFrame.BG_COLOR);
		
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(MazeFrame.BG_COLOR);
		this.setDoubleBuffered(true);
		
		this.frame.getContentPane().add(this);
		this.mainLoopThread = new Thread(this);
		this.maze = new Maze(MAZE_COLS, MAZE_ROWS, width, height);
	}
	
	public void start() {
		this.frame.pack();
		this.frame.setVisible(true);
		this.mainLoopThread.start();
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);
		this.maze.update();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.maze.paint(g);
	}

	@Override
	public void run() {
		while (true)
		{
			this.update(this.getGraphics());
			this.paint(this.getGraphics());
			try {
				Thread.sleep(MazeFrame.FRAME_RATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
