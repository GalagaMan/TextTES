/**
 * Created by PeppaPig on 5/14/2017.
 */
public class Weapon extends Item {
    int damage;

    Weapon(String name,int damage) {
        super(name);
        this.damage=damage;
    }
}
