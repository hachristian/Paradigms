/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;
	
	
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);

		this.setTitle("Mario attack!");
		this.setSize(800, 800);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);
		view.addMouseListener(controller);

		//loads the map when you start it.
		try {
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("you have loaded map.json");
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("There was an error loading the map");
			System.exit(0);
		}
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen
	
			// Go to sleep for 50 miliseconds
			try
			{
				Thread.sleep(25);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
