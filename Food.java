import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Food {
	public static final int WIDTH = SnakeFrame.WIDTH;
	public static final int LENGTH = SnakeFrame.LENGTH;
	
	public SnakeFrame snakeFrame;
	public int col;
	public int row;
	
	public Food(int col,int row) {
		this.col = col;
		this.row = row;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(col*WIDTH, row*LENGTH, WIDTH, LENGTH);
		g.setColor(c);
	}
	
	public void randomAppear() {
		Random randomRow = new Random();
		Random randomCol = new Random();
		
		this.col = randomCol.nextInt(SnakeFrame.ROW);
		this.row = randomRow.nextInt(SnakeFrame.COLUMNS);
	}
	
	
	public Rectangle getRect() {
		return new Rectangle(col*WIDTH,row*LENGTH,WIDTH,LENGTH);
	}
}
