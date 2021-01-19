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
	BufferedImage[] marioImages;
	Model model;
	int marioView;

	View(Controller c, Model m)
	{
		model = m;
		marioImages = new BufferedImage[5];
		marioView = 90;

		c.setView(this);

		//loading images
		marioImages[0] = loadImage("mario1.png");
		marioImages[1] = loadImage("mario2.png");
		marioImages[2] = loadImage("mario3.png");
		marioImages[3] = loadImage("mario4.png");
		marioImages[4] = loadImage("mario5.png");
	}
	
	static BufferedImage loadImage(String fileName)
	{
		BufferedImage im = null;
		
		try
		{
			im = ImageIO.read(new File(fileName));
			System.out.println(fileName + " has been loaded.");
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}

		return im;
	}

	public void paintComponent(Graphics g)
	{
		//background
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		//floor
		g.setColor(new Color(12, 125, 14));
		g.fillRect(0, 700, this.getWidth(), this.getHeight());
		//drawing tubes
		for (int i = 0; i < model.tubes.size(); i++)
		{
			Tube t = model.tubes.get(i);
			g.drawImage(t.image, t.x - model.mario.x + marioView, t.y, null);
		}
		//drawing mario
		g.drawImage(model.mario.images[model.mario.marioImageNum], marioView, model.mario.y, null);
	}

}
