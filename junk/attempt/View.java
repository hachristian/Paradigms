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
	JButton b1;
	BufferedImage turtleImage;
	Model model;

	View(Controller c, Model m)
	{
		model = m;
		b1 = new JButton("Turtle attack!");
		b1.addActionListener(c);
		this.add(b1);
		c.setView(this);

		try
		{
			this.turtleImage =
				ImageIO.read(new File("turtle.png"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	void removeButton()
	{
		this.remove(b1);
		this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(this.turtleImage, model.turtleX, model.turtleY, null);
	}
}
