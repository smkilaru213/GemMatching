import java.awt.Color;
import java.awt.Font;
import java.util.Random;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}

public class Gem {	
	/** Tester main method */
	public static void main(String [] args) {
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
	
	private GemType type;
	private int points;
	
	public Gem() {
		int[] randomNumbers = new int[] {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
	    Random r = new Random();
	    int nextRandomNumberIndex = r.nextInt(randomNumbers.length);
		this.points = randomNumbers[nextRandomNumberIndex];
		Random rn = new Random();
		int answer = rn.nextInt(3);
		if (answer == 0) {
			this.type = GemType.GREEN;
		} else if (answer == 1) {
			this.type = GemType.BLUE;
		} else if (answer == 2) {
			this.type = GemType.ORANGE;
		}
	}
	
	public Gem(GemType type, int points) {
		this.type = type;
		this.points = points;
	}

	public String toString() { 
		return this.getType() + " " + this.getPoints();
	}
	
	public GemType getType() {
		return this.type;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void draw(double x, double y) {
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
		StdDraw.setPenColor(Color.WHITE);

		if (this.type == GemType.GREEN) {
			StdDraw.picture(x, y, "gem_green.png");
			StdDraw.text(x,  y, this.getPoints() + "");
		} else if (this.type == GemType.BLUE) {
			StdDraw.picture(x, y, "gem_blue.png");
			StdDraw.text(x,  y, this.getPoints() + "");
		} else if (this.type == GemType.ORANGE) {
			StdDraw.picture(x, y, "gem_orange.png");
			StdDraw.text(x,  y, this.getPoints() + "");
		}
	}
}
