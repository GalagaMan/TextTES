/**
 * Created by PeppaPig on 12/10/2016.
 */
import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    Game game;
    Room location;
    Item hands[] = new Item[2];
    double health = 250;

    HashMap<String, Item> inventory;

    Player(Game game, Room location) {
        inventory = new HashMap<String, Item>();
        this.game = game;
        this.location = location;
    }

    void add(Item item) {
        inventory.put(item.name, item);
    }

    void hit(Mob mob) {
        mob.health -= 10;
        if (mob.health <= 0) {
            mob.location.remove(mob);
            game.remove(mob);
            System.out.println(mob.name+" killed");
        }
    }

    void take(String iName) {
        Item i = inventory.get(iName);
        if (i == null)
            takeFromRoom(iName);
        else
            takeFromInventory(iName);
    }

    void takeFromInventory(String itemName) {
        int freeHand = getFreeHand();
        if (freeHand < 0) {
            System.out.println("you are haven't got ONE BILLION HANDS");
        } else {
            Item i = inventory.get(itemName);
            hands[freeHand] = i;
            inventory.remove(itemName);
            System.out.println("U TOOK " + itemName);
        }
    }

    void takeFromRoom(String itemName) {
        Item i = location.items.get(itemName);
        if (i == null) {
            System.out.println("this object can not find.object name is " + itemName);
        } else {
            int freeHand = getFreeHand();
            if (freeHand < 0) {
                System.out.println("you are haven't got ONE BILLION HANDS");
            } else {
                hands[freeHand] = i;
                location.remove(i);
                System.out.println("U TOOK " + itemName);
            }
        }
    }

    int getFreeHand(){
        for (int i = 0; i< hands.length; i++)
            if (hands[i]==null)
                return i;
        return -1;
    }

    int findItemInHand(String itemName){
        for (int i = 0; i< hands.length; i++)
            if (hands[i]!=null && hands[i].name.equals(itemName))
                return i;
        return -1;
    }

    boolean isAlive(){
        return health >0;
   }

    void drop(String name) {
        Item x = inventory.get(name);
        if (x != null) {
            inventory.remove(name);
            location.add(x);
            System.out.println("you drop " + name);
        } else {
            int handIndex =findItemInHand(name);
            if (handIndex < 0)
                System.out.println("YOU haven't got " + name);
            else {
                location.add(hands[handIndex]);
                hands[handIndex] = null;
                System.out.println("you dropped " + name);

            }
        }
    }
}
