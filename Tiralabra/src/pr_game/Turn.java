
package pr_game;

import pr_map.Map;
import pr_renderer.Render_manager;

/**
 *
 * @author Henri Korpela
 * Turn class is responsible for running single turn.
 * Running a turn takes one second or more time.
 * If Turn has executed all actions before one second
 * has passed it will wait remaining time.
 */
public class Turn {
    /**
     * Variable holds all game actors.
     * Turn uses this variable to update
     * all actors.
     */
    private static Game_actors actors;
    /**
     * Running time of the turn in milliseconds.
     */
    private static long running_time;
    /**
     * Map that is currently played.
     */
    private static Map map;
    /**
     * Render manager that does all rendering.
     */
    private static Render_manager render_manager;
    /**
     * One second in milliseconds.
     */
    private static final long SECOND = 1000;
    /**
     * Sets given actor list as turns actor list.
     * Actors in this list will be updated one per turn.
     * @param actor_list Actor list to be updated each turn.
     */
    public static void set_actor_list(Game_actors actor_list)
    {
        actors = actor_list;
    }
    /**
     * Sets map that is played.
     * @param current_map Map that is played.
     */
    public static void set_map(Map current_map)
    {
        map = current_map;
    }
    /**
     * Return last turns running time in milliseconds.
     * @return last turn running time in milliseconds.
     */
    public static long get_last_running_time()
    {
        return running_time;
    }
    /**
     * Runs one turn. Updates all actors in
     * given actor list, renders scene and checks
     * whether game should stop. Game stops if prisoner
     * reaches escape car or is caught by police.
     * User can also stop game by closing display window.
     */
    public static void run()
    {
        long start_time = System.currentTimeMillis();
        
        actors.update_actors();
        render_manager.update();
        map.check_prisoner_escape();
        check_windows();
        long end_time = System.currentTimeMillis();
        running_time = end_time - start_time;
        if(running_time < SECOND)
        {
            try
            {
                Thread.sleep(SECOND - running_time);
            }
            catch(Exception e){}
        }
    }
    /**
     * Creates render manager for map.
     */
    public static void initialize_render_manager()
    {
        render_manager = new Render_manager(null,map,map.prisoner_position());
    }
    /**
     * Checks whether windows have been disposed.
     * If windows have been disposed sets game state to menu.
     */
    private static void check_windows()
    {
        if(render_manager.windows_disposed())
        {
            Game.state = Game_state.MENU;
        }
    }
}
