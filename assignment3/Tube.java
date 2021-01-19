/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/

class Tube
{
    int x;
    int y;

    final int w = 55;
    final int h = 400;

    Tube(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    // this is another constructor
    Tube(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");

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