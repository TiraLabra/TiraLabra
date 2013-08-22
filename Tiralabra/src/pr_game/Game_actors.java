
package pr_game;

import pr_ai.Actor;
import pr_data_structures.array_list.ArrayList;

/**
 *
 * @author Henri Korpela
 * 
 * Is responsible of all game actors.
 */
public class Game_actors {
    /**
     * Array list that contains all game actors.
     */
    private ArrayList<Actor> actors;
    /**
     * Creates Game actors handler.
     */
    public Game_actors()
    {
        this.actors = new ArrayList<Actor>();
    }
    /**
     * Adds given actor to actor list.
     * @param actor Actor to be added.
     */
    public void add_actor(Actor actor)
    {
        this.actors.add(actor);
    }
    /**
     * Updates all actors. This method is executed every turn.
     */
    public void update_actors()
    {
        for(int i = 0;i < this.actors.size();i ++)
        {
            this.actors.getIndex(i).update();
        }
    }
}
