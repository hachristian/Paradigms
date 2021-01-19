import java.awt.image.BufferedImage;
import java.awt.Graphics;

abstract class Sprite 
{
    int x, y;
    int pX, pY;
    int w, h;
    double vertVel;
    boolean onSurface;
    boolean isAlive;
    BufferedImage image;
    BufferedImage images[];

    abstract void update();
    abstract void drawYourself(Graphics g);

    boolean collision(Sprite s)
    {
        if (x + w < s.x)
            return false;
        if (x > s.x + s.w)
            return false;
        if (y + h < s.y)
            return false;
        if (y > s.y + s.h)
        {
            return false;
        }

        return true;
    }

    void getOffSprite(Tube t)
    {
        if (x + w >= t.x && pX + w < t.x )
        {
            // subtracting one beacuse it wont collide if i dont
            x = t.x - w - 1;
        }
        else if (x <= t.x + t.w && pX > t.x + t.w)
        {
            // adding one because it wont collide if i dont.
            x = t.x + t.w + 1;
        }
        else if (y + h > t.y && pY < t.y)
        {
            y = t.y - h;
            // treats the top of the tube like the ground
            vertVel = 0.0;
            onSurface = true;
        }
        else if (y < t.y + t.h && pY > t.y + t.h)
        {
            y = t.y + t.h + 1;
            vertVel = 0.0;
        }
    }

    boolean isTube()
    {
        return false;
    }

    boolean isMario()
    {
        return false;
    }

    boolean isGoomba()
    {
        return false;
    }

    boolean isFireball()
    {
        return false;
    }
}
