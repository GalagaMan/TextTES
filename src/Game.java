import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by PeppaPig on 12/10/2016.
 */

public class Game implements Serializable {
    Player player;
    HashMap<String, Mob> mobs = new HashMap<String, Mob>();

    Game(){
        Room meadow = new Room();
        meadow.name = "polyana";
        meadow.description = "big meadow where bees and butterflies are flying around";

        Room cave  = new Room();
        cave.name = "peschera trolya";
        cave.description = "you in the darkness, the lights turn on and u see _____INTERNET+TRO_))_LL!";
        Item ring = new Item("ring");
        Item sword = new Weapon("sword",30);
        Item sword2 = new Weapon("sword_of_pain",100);
        cave.add(ring);
        cave.add(sword);
        cave.add(sword2);

        Room wood  = new Room();
        wood.name = "lyes";
        wood.description = "you in the forest. In front sign._Attention computer NERDS!";
        Item healthPotion = new Item("1UP");
        wood.add(healthPotion);
        new Troll(this, "Leps", wood);


        Room city = new Room();
        city.name = "muhosransk";
        city.description = "So stinky";


        Room gori = new Room();
        gori.name = "gorrri";
        gori.description = "Look nice!";
        new Mob(this, "kusya", gori);

        meadow.south=city;
        meadow.west=cave;
        meadow.east=wood;

        cave.south=gori;

        cave.east=meadow;

        wood.west=meadow;

        city.north=meadow;
        city.west=gori;

        gori.north=cave;
        gori.east=city;

        player = new Player(this, meadow);
    }

    void hit(String name){
        Mob  y = player.location.mobs.get(name);

        if (y == null)
            System.out.format("there is no %s in the location\n", name);
        else
            player.hit(y);
        turn();
    }

    void add(Mob mob)
    {
        mobs.put(mob.name, mob);
    }

    void goNorth()
    {
        if (player.location.north==null)
            System.out.println("suda ne lysa");
        else {
            player.location = player.location.north;
            showLocation();
        }
        turn();
    }

    void goSouth()
    {
        if (player.location.south==null)
            System.out.println("suda ne lysa");
        else {
            player.location = player.location.south;
            showLocation();
        }
        turn();
    }

    void goWest()
    {
        if (player.location.west==null)
            System.out.println("suda ne lysa");
        else {
            player.location = player.location.west;
            showLocation();
        }
        turn();
    }

    void goEast()
    {
        if (player.location.east==null)
            System.out.println("suda ne lysa");
        else {
            player.location = player.location.east;
            showLocation();
        }
        turn();
    }

    void showLocation()
    {
        System.out.println("U are in " + player.location.name);
        System.out.println(player.location.description);
    }

   void  look()
   {
       System.out.println("U are in " + player.location.name);

       System.out.print("In the location you see: ");
       boolean first = true;
       for (Item i : player.location.items.values()) {
           if (first)
                first = false;
           else
               System.out.print(", ");

           System.out.print(i.name);
       }
       System.out.println();

       System.out.print("Creatures in the location: ");
       first = true;
       for (Mob m : player.location.mobs.values()) {
           if (first)
               first = false;
           else
               System.out.print(", ");

           System.out.print(m.name);
       }
       System.out.println();

       if (player.location.north == null)
           System.out.println("There's nothing to North");
       else
           System.out.println("There is " + player.location.north.name + " to the north");

       if (player.location.west == null)
           System.out.println("There's nothing to West");
       else
           System.out.println("There is " + player.location.west.name + " to the west");

       if (player.location.south == null)
           System.out.println("There's nothing to South");
       else
           System.out.println("There is " + player.location.south.name + " to the South");

       if (player.location.east == null)
           System.out.println("There's nothing to East");
       else
           System.out.println("There is " + player.location.east.name + " to the east");
       System.out.println ("your health =" + player.health);
   }

   void pikup(String name) {
       Item x = player.location.items.get(name);
       if (x == null)
           System.out.println("Ti bojara i u tebya net etogo " + name);
       else {
           player.add(x);
           player.location.items.remove(name);
           System.out.println("you pikup " + name);
       }
       turn();
   }

    void drop(String name) {
        player.drop(name);
        turn();
    }

    void showInventory() {
        boolean first = true;
        System.out.print("you have in inventory: ");
        for (Item i : player.inventory.values()) {
            if (first)
                first = false;
            else
                System.out.print(", ");

            System.out.print(i.name);
        }
        System.out.println();

        System.out.println("in your left hand: " + (player.hands[0] != null ? player.hands[0].name : ""));
        System.out.println("in your right hand: " + (player.hands[1] != null ? player.hands[1].name : ""));
    }

    void turn(){
        for (Mob m : mobs.values()) {
            m.play();
        }
    }
    void remove(Mob mob)
    {
        mobs.remove(mob.name);
    }
}
