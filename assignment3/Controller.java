/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;

	Controller()
	{
	}

	Controller(Model m)
	{
		model = m;
	}

	void setView(View v)
	{
		view = v;
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	//mouse methods
	public void mousePressed(MouseEvent e)
	{
		model.addTube(e.getX() + view.scrollPos, e.getY());
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }	

	//keyboard methods
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: 
				keyRight = true; 
				break;

			case KeyEvent.VK_LEFT: 
				keyLeft = true; 
				break;

			case KeyEvent.VK_UP: 
				keyUp = true; 
				break;

			case KeyEvent.VK_DOWN: 
				keyDown = true; 
				break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: 
				keyRight = false; 
				break;

			case KeyEvent.VK_LEFT: 
				keyLeft = false; 
				break;

			case KeyEvent.VK_UP: 
				keyUp = false; 
				break;

			case KeyEvent.VK_DOWN: 
				keyDown = false; 
				break;
			
			case KeyEvent.VK_S:
				model.marshal().save("map.json");
				System.out.println("You have saved map.json");
				break;

			case KeyEvent.VK_L:
				Json j = Json.load("map.json");
				model.unmarshal(j);
				System.out.println("you pressed loaded map.json");
				break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if (keyRight)
			view.scrollPos += 5;
		if (keyLeft)
			view.scrollPos -= 5;
	}
}

