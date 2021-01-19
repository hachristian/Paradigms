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
        if (this.turtleX < this.destX)
            this.turtleX += 5;
        else if (this.turtleX > this.destX)
            this.turtleX -= 5;
        if (this.turtleY < this.destY)
            this.turtleY += 5;
        else if (this.turtleY > this.destY)
            this.turtleY -= 5;
    }

    public void setDestination(int x, int y)
    {
        this.destX = x;
        this.destY = y;
    }
}
