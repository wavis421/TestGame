import java.awt.Graphics;
import java.util.ArrayList;

public class FunObjectManager {
	ArrayList<MazeObject> funList;
	boolean isMaze;

	public FunObjectManager() {
		funList = new ArrayList<MazeObject>();
	}

	public void addMazeObject(MazeObject o) {
		isMaze = true;
		funList.add(o);
	}

	public void update() {
		for (MazeObject o : funList) {
			o.update();
		}
		// purge
	}
	
	public void draw(Graphics g) {
		for (MazeObject o : funList)
			o.draw(g);
	}
}
