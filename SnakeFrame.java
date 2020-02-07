import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	public Snake snake = new Snake(this);
	public Food food = new Food(12,12);
	
	public static void main(String[] args) {
		snakeFrame = new SnakeFrame();
		snakeFrame.launch();
	}
	
	public void launch() {
		this.setTitle("Hungry Snake");
		this.setSize(ROW*LENGTH, COLUMNS*WIDTH);
		this.setLocation(30,40);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setVisible(true);
		this.addKeyListener(new KeyMoniter());
		new Thread(paintThread).start();
		
//		if(image == null) {
//			image = this.createImage(ROW*LENGTH,COLUMNS*WIDTH);
//		}
//		Graphics offg = image.getGraphics();
//		paint(offg);
		
	}
	
	
	@Override
	public void update(Graphics g) {
		if(image == null) {
			image = this.createImage(ROW*LENGTH,COLUMNS*WIDTH);
		}
		Graphics graphic = image.getGraphics();
		paint(graphic);
		
		g.drawImage(image, 0, 0, null);
		snake.draw(g);
		food.draw(g);
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
				try {
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
		public void pause() {
			pause = true;
		}
		
		public void recover() {
			pause = false;
		}
		
		public void dead() {
			pause = true;
		}
		public void reStart() {
			this.pause = false;
		}
		
	}
	
	public class KeyMoniter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_SPACE) {
				paintThread.pause();
			}
			else if(key == KeyEvent.VK_B) {
				paintThread.recover();
			}
			else if(key == KeyEvent.VK_R) {
				paintThread.reStart();
			}else {
				snake.keyPressed(e);
			}
		}
	}
}
