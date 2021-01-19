/*
Name: Julio Sibrian
09/22/2020
Assignment 3
*/

import java.util.ArrayList;
import java.util.Iterator;

class Model
{
    ArrayList<Tube> tubes;
    Mario mario;

    Model()
    {
        tubes = new ArrayList<Tube>();
        mario = new Mario(700, 50);
    }

    public void update()
    {
        mario.update();

        for (int i = 0; i < tubes.size(); i++)
        {
            if (mario.collision(tubes.get(i)))
            {
                mario.getOffTube(tubes.get(i));
            }
        }
    }

    public void addTube(int x, int y)
    {
        boolean found = false;
        Iterator<Tube> tubeIterator = tubes.iterator();

        while (tubeIterator.hasNext())
        {
            Tube temp = tubeIterator.next();
            if (temp.isTube(x, y) == true)
            {
                System.out.println("removing tube");
                tubeIterator.remove();
                found = true;
                break;
            }
        }
        // adds tube if a tube wasn't clicked on
        if (found == false)
        {
            System.out.println("adding tube");
            Tube t = new Tube(x, y);
            System.out.println("added tube at " + x + " " + y);
            tubes.add(t);
        }
    }

    void unmarshal(Json ob)
    {
        // this clears the array list
        tubes = new ArrayList<Tube>();
        Json tmpList = ob.get("tubes");
        for (int i = 0; i < tmpList.size(); i++)
            tubes.add(new Tube(tmpList.get(i)));
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("tubes", tmpList);
        for (int i = 0;i < tubes.size();i++)
            tmpList.add(tubes.get(i).marshalTube());
        
        return ob;
    }
}