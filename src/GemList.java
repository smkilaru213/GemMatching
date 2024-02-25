import java.awt.Color;
import java.awt.Font;

public class GemList  {
	private int size;
	private Node head;
	private Node tail;
	
	public GemList() {
		size = 0;
		head = null;
		tail = null;
	}

	public static void main(String [] args) {
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);
	}

	private class Node {
		private Gem gem;
		private Node next;
		
		public Node(Gem gem) {
			this.gem = gem;
		}
	}
	
	public int size() {
		return size;
	}

	public void draw(double y) {
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
		StdDraw.setPenColor(Color.WHITE);
		Node temp = this.head;
		int index = 0;
		while (temp != null) {
			if (temp.gem.getType() == GemType.GREEN) {
				StdDraw.picture(GemGame.indexToX(index), y, "gem_green.png");
				StdDraw.text(GemGame.indexToX(index),  y, temp.gem.getPoints() + "");
			} else if (temp.gem.getType() == GemType.BLUE) {
				StdDraw.picture(GemGame.indexToX(index), y, "gem_blue.png");
				StdDraw.text(GemGame.indexToX(index),  y, temp.gem.getPoints() + "");
			} else if (temp.gem.getType() == GemType.ORANGE) {
				StdDraw.picture(GemGame.indexToX(index), y, "gem_orange.png");
				StdDraw.text(GemGame.indexToX(index),  y, temp.gem.getPoints() + "");
			}
			index++;
			temp = temp.next;
		}
	}
	
	@Override
	public String toString() {
		if (head == null) {
			return "<none>";
		} else {
			Node temp = this.head;
			String print = "";
			while (temp != null) {
				if (temp.next != null) {
					print += temp.gem.getType() + " -> ";
				} else {
					print += temp.gem.getType();
				}
				temp = temp.next;
			}
			return print;
		}
	}
	
	public void insertBefore(Gem gem, int index) {
		Node n = new Node(gem);
		if (index >= size()) {
			Node x = new Node(gem);
			if (head == null) {
				head = x;
				tail = head;
			} else {
				tail.next = x;
				tail = tail.next;
			}
			size++;
		} else if (index == 0) {
			Node temp = this.head;
			head = n;
			head.next = temp;
			size++;
		} else {
			Node temp = this.head;
			for (int i = 0; i < index - 1; i++) {
				temp = temp.next;
			}
			Node temp2 = temp.next;
			temp.next = n;
			n.next = temp2;
			size++;		
		}
	}
	
	public int score() {
		Node temp = this.head;
		if (size == 0) {
			return 0;
		} else if (size == 1) {
			return temp.gem.getPoints();
		}
		int tempCount = temp.gem.getPoints();
		int finCount = 0;
		int s = 1;
		GemType color = temp.gem.getType();
		while (temp.next != null) {
			temp = temp.next;
			if (temp.gem.getType() == color) {
				tempCount += temp.gem.getPoints();
				color = temp.gem.getType();
				s++;
			} else {
				finCount += (tempCount * s);
				tempCount = temp.gem.getPoints();
				color = temp.gem.getType();
				s = 1;
			}
		}
		finCount += (tempCount * s);
		return finCount;
	}
}
