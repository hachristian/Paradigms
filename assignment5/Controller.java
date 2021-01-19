/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/

import java.awt.event.MouseListener;

import javax.lang.model.util.ElementScanner14;

import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	int coolDown;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean control;
	boolean spaceBar;
	boolean mapEditor;
	boolean goombaEditor;

	Controller()
	{
		coolDown = 0;
		mapEditor = false;
		goombaEditor = false;
		model = null;
		view = null;
	}

	Controller(Model m)
	{
		mapEditor = false;
		model = m;
		view = null;
	}
	
	void setModel(Model m)
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
		if (mapEditor == true)
			model.addTube(e.getX() + model.mario.x - model.mario.marioView, e.getY());
		if (goombaEditor == true)
			model.addGoomba(e.getX() + model.mario.x - model.mario.marioView, e.getY());

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

			case KeyEvent.VK_SPACE:
				spaceBar = true;
				break;

			case KeyEvent.VK_CONTROL:
				control = true;
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

			case KeyEvent.VK_SPACE:
				spaceBar = false;
				break;
			
			case KeyEvent.VK_CONTROL:
				control = false;
				break;
			
			case KeyEvent.VK_S:
				model.marshal().save("map.json");
				System.out.println("You have saved map.json");
				break;

			case KeyEvent.VK_L:
				Json j = Json.load("map.json");
				model.unmarshal(j);
				System.out.println("You have loaded map.json");
				break;

			case KeyEvent.VK_M:
				if (mapEditor == false)
				{
					System.out.println("You have enabled map editor");
					mapEditor = true;
				}
				else
				{
					System.out.println("You have disabled map editor");
					mapEditor = false;
				}
				break;
			
			case KeyEvent.VK_G:
				if (goombaEditor == false)
				{
					System.out.println("You have enabled goomba editor");
					goombaEditor = true;
				}
				else
				{
					System.out.println("you have disabled goomba editor");
					goombaEditor = false;
				}
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		//cooldown so it looks better when shooting a fireball
		coolDown--;
		model.mario.prevCord();

		if (keyRight)
		{
			model.mario.x += 5;
			model.mario.flip = false;
		}
		if (keyLeft)
		{
			model.mario.x -= 5;
			model.mario.flip = true;
		}
		if (keyUp || spaceBar)
			model.mario.jump();
		if (control && coolDown < 0)
		{
			model.addFireBall();
			coolDown = 5;
		}
		
	}
}

