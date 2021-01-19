/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/
import java.awt.Graphics;

class Tube extends Sprite
{
    Model model;

    Tube(int x, int y, Model m)
    {
        this.x = x;
        this.y = y;
        pX = 0;
        pY = 0;
        w = 55;
        h = 400;
        model = m;
        isAlive = true;

        if (image == null)
            image = View.loadImage("tube.png");
    }
    // this is another constructor
    Tube(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 55;
        h = 400;
        isAlive = true;
        model = m;

        if (image == null)
            image = View.loadImage("tube.png");

        System.out.println("You put a tube at (" + x + ", " + y + ")");
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);

        return ob;
    }

    public boolean isTube(int mouseX, int mouseY)
    {
        if (mouseX < x)
            return false;
        if (mouseX > x + w)
            return false;
        if (mouseY < y)
            return false;
        if (mouseY > y + h)
            return false;

        return true;
    }

    @Override
    boolean isTube()
    {
        return true;
    }

    void update()
    {
    }

    void drawYourself(Graphics g)
    {
        g.drawImage(image, x - model.mario.x + model.mario.marioView, y, null);
    }
}