import java.io.Serializable;
import java.util.Random;

/**
 * Created by Gleb on 12/25/2016.
 */
public class Mob implements Serializable{
    Game game;
    String name;
    Room location;
    Random random = new Random();

    double health = 15;



    Mob(Game game, String name, Room location)
    {
        this.game = game;
        this.name = name;
        this.location = location;

        game.add(this);
        location.add(this);
    }

    void play(){
        moveRandom();
    }

    void moveRandom(){
        int rand = random.nextInt(10);
        switch (rand){
            case 0:
                move(location.north);
                break;
            case 1:
                move(location.east);
                break;
            case  2:
                move(location.south);
                break;
            case 3:
                move(location.west);
                break;
        }
    }

    void move(Room toRoom)
    {
        if (toRoom == null)
            return;

        location.remove(this);
        toRoom.add(this);
        location = toRoom;

        System.out.println(name+" moved to " + toRoom.name);
    }

    void hit(Player player){
        if (!player.isAlive())
            return;

        player.health -= 10;
        System.out.println(name + " hit you your health = " + player.health);
        if (player.health <= 0) {
            System.out.println("you died");
        }
    }

}
