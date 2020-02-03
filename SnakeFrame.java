import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnakeFrame extends Frame{
	// Define the total width and length of the frame
	public static final int WIDTH = 20;
	public static final int LENGTH = 20;
	
	
	// Define the number of rows and columns in the frame
	public static final int ROW = 40;
	public static final int COLUMNS = 40;

	private static SnakeFrame snakeFrame;
	private Image image;
	private MyPaintThread paintThread = new MyPaintThread();
	
	public static void main(String[] args) {
		snakeFrame = new SnakeFrame();
		snakeFrame.launch();
	}
	
	public void launch() {
		this.setTitle("Hungry Snake");
		this.setSize(ROW*LENGTH, COLUMNS*WIDTH);
		this.setLocation(300,400);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setVisible(true);
		
		new Thread(paintThread).start();
		
		if(image == null) {
			image = this.createImage(ROW*LENGTH,COLUMNS*WIDTH);
		}
		Graphics offg = image.getGraphics();
		paint(offg);
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		
		// draw the grids
		for(int i = 0; i < ROW; i++) {
			g.drawLine(0, i*LENGTH,COLUMNS*WIDTH, i*LENGTH);
		}
		for(int i = 0; i < COLUMNS;i++) {
			g.drawLine(i*WIDTH, 0, i*WIDTH, ROW*LENGTH);
		}
		g.setColor(c);
		
	}
	
	
	// Use multi-thread to redraw 
	public class MyPaintThread implements Runnable{
		private static final boolean running = true;
		private boolean pause = false;
		
		@Override
		public void run() {
			while(running) {
				if(pause) {
					continue;
				}
				repaint();
				System.out.println("redraw");
				try {
					Thread.sleep(10000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
	}
}
