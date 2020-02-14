import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import org.w3c.dom.Node;

public class Snake {
	public static final int WIDTH = SnakeFrame.WIDTH;
	public static final int LENGTH = SnakeFrame.LENGTH;
	
	public Node head = null;
	public Node tail = null;
	public SnakeFrame snakeFrame;
	public int size = 0;
	public Node node = new Node(13,8,"Invalid");
	
	// Constructor
	public Snake(SnakeFrame snakeFrame) {
		head = node;
		tail = node;
		size = 1;
		this.snakeFrame = snakeFrame;
	}
	
	public void draw(Graphics g) {
		if(head == null) {
			return;
		}
		
		move();
		for(Node node = head;node!= null;node = node.next) {
			node.draw(g);
		}
	}
	
	
	public void addNodeInHead() {
		Node newnode = null;
		switch(head.dir) {
		case "Left":
			newnode = new Node(head.row,head.col-1,head.dir);
			break;
		case "Right":
			newnode = new Node(head.row,head.col+1,head.dir);
			break;
		case "Up":
			newnode = new Node(head.row-1,head.col,head.dir);
			break;
		case "Down":
			newnode = new Node(head.row+1,head.col,head.dir);
			break;
		default:
			return;
		}
		
		head.pre = newnode;
		newnode.next = head;
		head = newnode;
	}
	
	
	public void move() {
		addNodeInHead();
		checkDead();
		deleteNodeInTail();
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
	
	public void checkDead() {
		if( head.row <= 2 || head.row >= SnakeFrame.ROW || head.col <= 0 
				|| head.col >= SnakeFrame.COLUMNS) {
			this.snakeFrame.gameOver();
		}
		
		for(Node node = head.next; node != null; node = node.next) {
			if(head.row == node.row && head.col == node.col) {
				this.snakeFrame.gameOver();
			}
		}
	}
	
	
	public void deleteNodeInTail() {
		if(tail == head) {
			return;
		}
		
		Node node = tail.pre;
		tail = null;
		node.next = null;
		tail = node;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT:
			if(head.dir != "Left") {
				head.dir = "Left";
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(head.dir != "Right") {
				head.dir = "Right";
			}
			break;
		case KeyEvent.VK_UP:
			if(head.dir != "Up") {
				head.dir = "Up";
			}
			break;
		case KeyEvent.VK_DOWN:
			if(head.dir != "Down") {
				head.dir = "Down";
			}
			break;
		}
		
	}
	
	
	public Rectangle getRect() {
		return new Rectangle(head.col*WIDTH,head.row*LENGTH,WIDTH,LENGTH);
	}
	
	public boolean eatFood(Food food) {
		if(this.getRect().intersects(food.getRect())) {
			addNodeInHead();
			food.randomAppear();
			return true;
		}
		return false;
	}
	
}
