import java.io.Serializable;

/**
 * Created by Gleb on 12/17/2016.
 */
public class Item implements Serializable {
   String name;
   Item(String name)
   {
       this.name = name;
   }
}
