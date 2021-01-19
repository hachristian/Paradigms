import java.awt.Graphics;


class Goomba extends Sprite
{
    int framesOnFire;
    int count;
    int direction;
    boolean onFire;
    boolean alreadyLoaded;
    Model model;

    Goomba(int x, int y, Model m)
    {
        this.x = x;
        this.y = y;
        pX = 0;
        pY = 0;
        w = 35;
        h = 42;
        framesOnFire = 0;
        count = 0;
        direction = 1;
        onFire = false;
        alreadyLoaded = false;
        isAlive = true;
        model = m;
        
        if (image == null)
            image = View.loadImage("goomba.png");

        System.out.println("You put a Goomba at (" + x + ", " + y + ")");
    }

    Goomba(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        framesOnFire = 0;
        pX = 0;
        pY = 0;
        w = 35;
        h = 42;
        count = 0;
        direction = 1;
        onFire = false;
        alreadyLoaded = false;
        isAlive = true;
        model = m;

        if (image == null)
            image = View.loadImage("goomba.png");

        System.out.println("You put a Goomba at (" + x + ", " + y + ")");
    }

    void update()
    {
        vertVel += 1.2;
        y += vertVel;

        if (y > 700 - h)
        {
            vertVel = 0.0;
            y = 700 - h;
            count = 0;
        }
        x += 4 * direction;
        
        if (onFire == true)
        {
            framesOnFire++;
        }

        if (framesOnFire > 10)
            isAlive = false;
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);

        return ob;
    }

    public boolean isGoomba(int mouseX, int mouseY)
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
    boolean isGoomba()
    {
        return true;
    }

    void drawYourself(Graphics g)
    {
        if (onFire && !alreadyLoaded)
        {
            image = View.loadImage("goombaFire.png");
            alreadyLoaded = true;
        }
        g.drawImage(image, x - model.mario.x + model.mario.marioView, y, null);
    }
}
