import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FunGamePanel extends JPanel implements KeyListener, ActionListener {
	private static final int numRows = 10, numCols = 10;
	public static final int gameObjectWidth = FunGame.gamePanelWidth / numCols;
	public static final int gameObjectHeight = FunGame.gamePanelHeight / numRows;

	int fps = 60;
	FunObjectManager objectMgr;
	MazeObject[][] mazeGrid = new MazeObject[numRows][numCols];
	Timer timer;
	int[][] mazeControl = { { 1,0,1,0,1,0,1,0,1,0 }, { 0,1,0,1,0,1,0,1,0,1 }, { 1,0,1,0,1,0,1,0,1,0 },
			                { 0,1,0,1,0,1,0,1,0,1 }, { 1,0,1,0,1,0,1,0,1,0 }, { 0,1,0,1,0,1,0,1,0,1 }, 
			                { 1,0,1,0,1,0,1,0,1,0 }, { 0,1,0,1,0,1,0,1,0,1 }, { 1,0,1,0,1,0,1,0,1,0 }, 
			                { 0,1,0,1,0,1,0,1,0,1 } };

	public FunGamePanel() {
		objectMgr = new FunObjectManager();

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				mazeGrid[row][col] = new MazeObject(row, col, mazeControl[row][col]);
				objectMgr.addMazeObject(mazeGrid[row][col]);
			}
		}
		
		timer = new Timer(1000/fps, this);
//		timer.start();
	}

	public void paintComponent(Graphics g) {
		objectMgr.draw(g);
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key pressed " + arg0.getKeyCode());
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				MazeObject o = mazeGrid[i][j];
				if (arg0.getKeyCode() == 37) {  // Left
					o.row--;
					if (o.row < 0)
						o.row = numRows - 1;
				}
				else if (arg0.getKeyCode() == 38)  { // Up
					o.col--;
					if (o.col < 0)
						o.col = numCols - 1;
				}
				else if (arg0.getKeyCode() == 39) {  // Right
					o.row++;
					if (o.row >= numRows)
						o.row = 0;
				}
				else if (arg0.getKeyCode() == 40) {  // Down
					o.col++;
					if (o.col >= numCols)
						o.col = 0;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		objectMgr.update();
		repaint();
	}
}
