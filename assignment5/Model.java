/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/

import java.util.ArrayList;

class Model
{
    ArrayList<Sprite> sprites;
    Mario mario;

    Model()
    {
        sprites = new ArrayList<Sprite>();
        mario = new Mario(700, 50);
        sprites.add(mario);
    }

    public void update()
    {
        for (int i = 0; i < sprites.size(); i++)
        {
            Sprite s = sprites.get(i);
            s.update();

            if (s.isTube())
            {
                if (mario.collision((Tube)sprites.get(i)))
                {
                    mario.getOffSprite((Tube)sprites.get(i));
                }
                
                //checking for goombas
                for (int j = 0; j < sprites.size(); j++)
                {
                    if (sprites.get(j).isGoomba())
                    {
                        Goomba g = (Goomba)sprites.get(j);

                        if (sprites.get(j).isAlive == false)
                            sprites.remove(sprites.get(j));

                        if (g.collision((Tube)sprites.get(i)))
                        {
                            g.direction *= -1;
                        }
                    }
                }
            }
            if (s.isFireball())
            {
                for(int j = 0; j < sprites.size(); j++)
                {
                    if (sprites.get(j).isGoomba())
                    {
                        Goomba g = (Goomba)sprites.get(j);
        
                        if (g.collision(s))
                        {
                            g.onFire = true;
                            sprites.remove(s);
                        }
                        if (g.isAlive == false)
                            sprites.remove(g);
                    }
                }
                if (s.isAlive == false)
                    sprites.remove(s);
            }
        }

    }

    public void addTube(int x, int y)
    {
        boolean found = false;

        for (int i = 0; i < sprites.size(); i++)
        {
            if (sprites.get(i).isTube())
            {
                Tube temp = (Tube)sprites.get(i);
                if (temp.isTube(x, y))
                {
                    sprites.remove(temp);
                    found = true;
                    break;
                }
            }
        }
        // adds tube if a tube wasn't clicked on
        if (found == false)
        {
            System.out.println("adding tube");
            Tube t = new Tube(x, y, this);
            System.out.println("added tube at " + x + " " + y);
            sprites.add(t);
        }
    }

    public void addGoomba(int x, int y)
    {
        boolean found = false;

        for (int i = 0; i < sprites.size(); i++)
        {
            if (sprites.get(i).isGoomba())
            {
                Goomba temp = (Goomba)sprites.get(i);
                if (temp.isGoomba(x, y))
                {
                    //removes goomba if there's one
                    sprites.remove(temp);
                    found = true;
                    break;
                }
            }
        }
        // adds Goomba if a goomba wasn't clicked on
        if (found == false)
        {
            System.out.println("adding Goomba");
            Goomba g = new Goomba(x, y, this);
            System.out.println("added Goomba at " + x + " " + y);
            sprites.add(g);
        }
    }

    void addFireBall()
    {
        FireBall f = new FireBall(mario.x, mario.y, this);
        sprites.add(f);
    }

    void unmarshal(Json ob)
    {
        // this clears the array list
        sprites = new ArrayList<Sprite>();
        sprites.add(mario);
        Json jsonList = ob.get("sprites");
        Json tubesList = jsonList.get("tubes");
        Json goombasList = jsonList.get("goombas");

        for (int i = 0; i < tubesList.size(); i++)
        {
            sprites.add(new Tube(tubesList.get(i), this));
        }

        for (int i = 0; i < goombasList.size(); i++)
        {
            sprites.add(new Goomba(goombasList.get(i), this));
        }
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        Json spritesOb = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("sprites", spritesOb);
        spritesOb.add("tubes", tmpList);

        for (int i = 0; i < sprites.size(); i++)
        {
            if (sprites.get(i).isTube())
            {
                Tube t = (Tube)sprites.get(i);
                tmpList.add(t.marshal());
            }
        }

        tmpList = Json.newList();
        spritesOb.add("goombas", tmpList);
        
        for (int i = 0; i < sprites.size(); i++)
        {
            if (sprites.get(i).isGoomba())
            {
                Goomba g = (Goomba)sprites.get(i);
                tmpList.add(g.marshal());
            }
        }

        return ob;
    }
}