import java.awt.Color;
import java.awt.Graphics;

import org.w3c.dom.Node;

public class Snake {
	public static final int WIDTH = SnakeFrame.WIDTH;
	public static final int LENGTH = SnakeFrame.LENGTH;
	
	public Node head = null;
	public Node tail = null;
	public SnakeFrame snakeFrame;
	public int size = 0;
	public Node node = new Node(3,4,"LEFT");
	
	// Constructor
	public Snake(SnakeFrame snakeFrame) {
		head = node;
		tail = node;
		size ++;
		this.snakeFrame = snakeFrame;
	}
	
	public void draw(Graphics g) {
		if(head == null) {
			return;
		}
		
		
		for(Node node = head;node!= null;node = node.next) {
			node.draw(g);
		}
	}
	
	public class Node{
		public int row;
		public int col;
		public String dir;
		
		public Node pre;
		public Node next;
		
		public Node(int row,int col,String dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.GREEN);
			g.fillRect(col*WIDTH, row*LENGTH, WIDTH, LENGTH);
			g.setColor(c);
		}
		
	}
	
}
