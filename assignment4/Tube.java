/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/

class Tube extends Sprite
{
    Tube(int x, int y)
    {
        this.x = x;
        this.y = y;
        w = 55;
        h = 400;

        if (image == null)
            image = View.loadImage("tube.png");
    }
    // this is another constructor
    Tube(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 55;
        h = 400;

        if (image == null)
            image = View.loadImage("tube.png");

        System.out.println("You put a tube at (" + x + ", " + y + ")");
    }

    Json marshalTube()
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
}