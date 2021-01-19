import java.awt.Graphics;

class FireBall extends Sprite
{
    int count = 0;
    Model model;

    FireBall(int x, int y, Model m)
    {
        this.x = x;
        this.y = y;
        pX = 0;
        pY = 0;
        w = 47;
        h = 47;
        vertVel = 0.0;
        onSurface = false;
        model = m;

        if (image == null)
            image = View.loadImage("fireball.png");
    }

    void update()
    {
        isAlive = true;
        vertVel += 2;
        y += vertVel;
        x += 10;

        if (y > 700 - h)
        {
            y = 700 - h;
            count++;
        }

        if (count > 1)
        {
            vertVel = -20;
            count = 0;
        }

        if (x > model.mario.x + model.mario.marioView + 1000)
        {
            isAlive = false;
        }

    }

    @Override
    boolean isFireball()
    {
        return true;
    }

    void drawYourself(Graphics g)
    {
        g.drawImage(image, x - model.mario.x + model.mario.marioView, y, null);
    }
}