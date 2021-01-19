/*
Name: Julio Sibrian
09/10/2020
Assignment 2
*/

class Model
{
    int turtleX;
    int turtleY;
    int destX;
    int destY;

    Model()
    {
    }

    public void update()
    {
        // Moves the turtle
        if (this.turtleX < this.destX)
        {
            this.turtleX += Math.min(4, destX - turtleX);
        }
        else if (this.turtleX > this.destX)
        {
            this.turtleX -= Math.min(4, turtleX - destX);
        }

        if (this.turtleY < this.destY)
        {
            this.turtleY += Math.min(4, destY - turtleY);
        }
        else if (this.turtleY > this.destY)
        {
            this.turtleY -= Math.min(4, turtleY - destY);
        }
    }

    public void setDestination(int x, int y)
    {
        this.destX = x;
        this.destY = y;
    }
}