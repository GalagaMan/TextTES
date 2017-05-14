
/**
 * Created by Gleb on 2/1/2017.
 */

public class Troll extends Mob {

    Troll(Game game, String name, Room room){
        super (game,name,room);

    }

    void play (){
        if (game.player.location == location){
            System.out.println("I am kill you");
            hit(game.player);
        }
    }

}
