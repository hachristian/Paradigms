/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	BufferedImage tubeImage;
	Model model;
	int scrollPos;

	View(Controller c, Model m)
	{
		model = m;
		scrollPos = 0;

		c.setView(this);

		try
		{
			this.tubeImage = ImageIO.read(new File("tube.png"));
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//drawing tubes
		for (int i = 0; i < model.tubes.size(); i++)
		{
			Tube t = model.tubes.get(i);
			g.drawImage(tubeImage, t.x - scrollPos, t.y, null);
		}
	}
}
