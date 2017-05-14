import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PeppaPig on 12/10/2016.
 */
public class Room implements Serializable{
    String name;
    String description;
    HashMap<String, Item> items = new HashMap<String, Item>();
    HashMap<String, Mob> mobs = new HashMap<String, Mob>();
    Room north;
    Room west;
    Room south;
    Room east;

    void add(Item item) {
        items.put(item.name, item);
    }

    void remove(Item item) {
        items.remove(item.name);
    }

    void add(Mob mob) {
        mobs.put(mob.name, mob);
    }

    void remove(Mob mob)
    {
        mobs.remove(mob.name);
    }
}