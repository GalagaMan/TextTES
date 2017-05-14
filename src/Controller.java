/**
 * Created by PeppaPig on 12/10/2016.
 */
import com.sun.deploy.util.SyncFileAccess;

import java.io.*;
import java.util.*;

public class Controller {
    Game game;

    Controller(Game g)
    {
        game = g;
    }

    void save(String fname) {
        try (
            FileOutputStream fo = new FileOutputStream(fname);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
        )
        {
            oo.writeObject(game);
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + fname + " not found");
        }
        catch (IOException e) {
            System.out.println("Cannot write to file " + fname);
        }



    }

    void load(String fname){
        try (
                FileInputStream fi = new FileInputStream(fname);
                ObjectInputStream oi = new ObjectInputStream(fi);
        )
        {
            game = (Game)oi.readObject();
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + fname + " not found");
        }
        catch (IOException e) {
            System.out.println("Cannot read from file " + fname);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    void play(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welkomen TO +3 31|)3!) 2(!)0!2");
        game.showLocation();

        while (true) {
            if (!game.player.isAlive()) {
            return;
            }
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            switch(words[0]){
                case "exit":
                    return;

                case "west":
                case "w":
                    game.goWest();
                    break;


                case "east":
                case "e":
                    game.goEast();
                    break;

                case "south":
                case "s":
                    game.goSouth();

                    break;

                case "north":
                case "n":
                    game.goNorth();
                    break;
                case "look":
                case "l":
                    game.look();
                    break;
                case "pikup":
                case "p":
                   if (words.length<2)
                       System.out.println("you can`t pikup nothing ");
                   else
                    game.pikup(words [1]);
                    break;
                case "inventory":
                case "i":
                    game.showInventory();
                    break;
                case "drop":
                case "d":
                    if (words.length<2)
                        System.out.println("you can`t drop nothing ");
                        else  game.drop(words [1]);
                    break;

                case "take":
                case "t":
                    if (words.length<2)
                        System.out.println("you can`t put in your hand (take) nothing ");
                    else
                        game.player.take(words [1]);
                    break;


                case "hit":
                case "h":
                    if (words.length<2)
                        System.out.println("you can`t hit nothing ");
                    else
                        game.hit(words [1]);
                    break;
                case "save":
                    if(words.length<2)
                        System.out.println("game can not be saved file name missing");
                    else
                        save(words[1]);
                    break;

                case "load":
                    if(words.length<2)
                        System.out.println("game can not be loaded file name missing");
                    else
                        load(words[1]);
                    break;

                default:
                    System.out.println("unknow command");


            }
        }
    }
}
