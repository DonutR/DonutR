package designPatterns;

public class FlyweightDesign {

    public class Colour {
        int colour;

        public Colour(int col) {
            this.colour = col;
        }
    }

    public class Rect {
        Colour colour;
        int len, br, ht;

    }
}
