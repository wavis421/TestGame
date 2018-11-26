package fonts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * MyFonts: This program displays all available Java Graphics fonts on a window.
 * 
 * @author wavis
 *
 */
public class GraphicsFonts {
	private static int frameWidth = 1800;
	private static int xSeparation = 380;
	private static int ySeparation = 30;
	private static int entriesPerColumn = 30;
	private static int fontSize = 20;

	private String[] fontNames;
	private Font[] fonts;
	private ArrayList<String> fontNameList = new ArrayList<String>();
	private int numColumns, innerPanelWidth, innerPanelHeight;

	public static void main(String[] args) {
		new GraphicsFonts();
	}

	public GraphicsFonts() {
		// Get all fonts for this platform
		fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		if (fontNames == null) {
			JOptionPane.showMessageDialog(null, "Get java graphics fonts failed!");
			System.exit(0);
		}

		// Add all fonts to the font list
		for (int i = 0; i < fontNames.length; i++) {
			fontNameList.add(fontNames[i]);
		}

		fonts = new Font[fontNameList.size()];

		// Simple GUI with scroll panel in JFrame
		JFrame frame = new JFrame();
		JPanel outerPanel = new JPanel();
		JPanel innerPanel = new FontPanel();
		frame.setResizable(false);

		// Calculate # columns, width & height based on how many fonts to display
		numColumns = (fontNameList.size() / entriesPerColumn) + 1;
		innerPanelWidth = 20 + (numColumns * xSeparation);
		innerPanelHeight = 30 + (entriesPerColumn * ySeparation);

		// Set inner/outer panel and frame sizes
		innerPanel.setPreferredSize(new Dimension(innerPanelWidth, innerPanelHeight));
		JScrollPane scrollPane = new JScrollPane(innerPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(frameWidth, innerPanelHeight));
		outerPanel.add(scrollPane);

		frame.add(outerPanel);
		frame.setTitle("Available Java Graphics fonts for this platform (font size 20, bold) ");
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class FontPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, innerPanelWidth, innerPanelHeight);
			g.setColor(Color.BLACK);

			// Use Graphics to display the fonts
			for (int i = 0; i < fontNameList.size(); i++) {
				if (fonts[i] == null) {
					fonts[i] = new Font(fontNameList.get(i), Font.BOLD, fontSize);
				}

				g.setFont(fonts[i]);
				int x = 20 + ((i / entriesPerColumn) * xSeparation);
				int y = 30 + (ySeparation * (i % entriesPerColumn));
				g.drawString(fontNameList.get(i), x, y);
			}
			repaint();
		}
	}
}
