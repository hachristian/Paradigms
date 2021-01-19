import java.awt.image.BufferedImage;

class Mario extends Sprite
{
    int pX, pY;
    int marioImageNum;
    int count = 0;
    double vertVel;

    Mario(int x, int y)
    {
        this.x = x;
        this.y = y;
        pX = 0;
        pX = 0;
        w = 60;
        h = 95;
        marioImageNum = 0;
        vertVel = 0.0;
        
        if (images == null)
        {
            images = new BufferedImage[5];
            images[0] = View.loadImage("mario1.png");
            images[1] = View.loadImage("mario2.png");
            images[2] = View.loadImage("mario3.png");
            images[3] = View.loadImage("mario4.png");
            images[4] = View.loadImage("mario5.png");
        }
    }
    
    void update()
    {
        count++;
        //simulating gravity
        vertVel += 1.2;
        y += vertVel;
        
        if (y > 700 - h)
        {
            vertVel = 0.0;
            y = 700 - h;
            count = 0;
        }
        if (y <= 0)
        {
            y = 0;
        }
        
        //itterating through the images to make mario walk
        marioImageNum = (marioImageNum + 1) % 5;
    }
    void prevCord()
    {
        pX = x;
        pY = y;
    }

    void getOffTube(Tube t)
    {
        if (x + w >= t.x && pX + w < t.x )
        {
            // subtracting one beacuse it wont collide if i dont
            x = t.x - w - 1;
            marioImageNum = 0;
        }
        else if (x <= t.x + t.w && pX > t.x + t.w)
        {
            // adding one because it wont collide if i dont.
            x = t.x + t.w + 1;
            marioImageNum = 0;
        }
        else if (y + h > t.y && pY < t.y)
        {
            y = t.y - h;
            // allows us to jump again if on top of tube
            count = 0;
            // treats the top of the tube like the ground
            vertVel = 0.0;
        }
        else if (y < t.y + t.h && pY > t.y + t.h)
        {
            y = t.y + t.h + 1;
            vertVel = 0.0;
        }
    }

    boolean collision(Tube t)
    {
        if (x + w < t.x)
            return false;
        if (x > t.x + t.w)
            return false;
        if (y + h < t.y)
            return false;
        if (y > t.y + t.h)
        {
            return false;
        }

        return true;
    }
    
    void jump()
    {
        if (count < 10)
        {
            vertVel = -15;
        }
    }
}
