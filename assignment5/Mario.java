import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Mario extends Sprite
{
    int marioImageNum;
    int count;
	int marioView;
    boolean flip;
    boolean jump;

    Mario(int x, int y)
    {
        this.x = x;
        this.y = y;
        pX = 0;
        pY = 0;
        w = 60;
        h = 95;
        marioImageNum = 0;
        count = 0;
        marioView = 90;
        vertVel = 0.0;
        flip = false;
        onSurface = false;
        isAlive = true;
        
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
        //simulating gravity
        vertVel += 1.2;
        y += vertVel;
        count++;
        
        if (y > 700 - h)
        {
            vertVel = 0.0;
            y = 700 - h;
            count = 0;
        }

        if (onSurface == true)
        {
            onSurface = false;
            count = 0;
        }
     
        //itterating through the images to make mario walk
        marioImageNum = (marioImageNum + 1) % 5;
    }
    void prevCord()
    {
        pX = x;
        pY = y;
    }
    
    void jump()
    {
        if (count < 7 || onSurface)
        {
            vertVel = -15;
        }
    }

    void drawYourself(Graphics g)
    {
        if(flip)
		    g.drawImage(images[marioImageNum], marioView + w, y, -w, h, null);
        else
		    g.drawImage(images[marioImageNum], marioView, y, w, h,null);
    }
}
