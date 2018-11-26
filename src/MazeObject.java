import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MazeObject {
	int row, col, x, y, width, height, maze;
	Rectangle collisionBox;
	Random random = new Random();

	public MazeObject(int row, int col, int maze) {
		this.row = row;
		this.col = col;
		this.width = FunGamePanel.gameObjectWidth;
		this.height = FunGamePanel.gameObjectHeight;
		this.x = row * width;
		this.y = col * height;
		this.maze = maze;
		
		collisionBox = new Rectangle(x,y,width,height);
	}
	
	public void update() {
		x = row * width;
		y = col * height;
		//collisionBox.setBounds(x, y, width, height);
	}

	public void draw(Graphics g) {
//		if (maze == 1)
//			g.setColor(Color.BLACK);
//		else
//			g.setColor(Color.WHITE);
		g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		g.fillRect(x, y, width, height);
	}
}
