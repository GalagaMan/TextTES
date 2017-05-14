import java.util.*;

public class Main {

    public static void main(String[] args) {
        Game g = new Game();
        Controller c = new Controller(g);
        c.play();

    }
}
