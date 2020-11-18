package ir.ac.kntu;

import javafx.scene.paint.Color;

public enum Wall {
    LONGWALL (20, 200, 100, Color.GREEN),
    SHORTWALL (20, 120, 50, Color.YELLOW);

    Wall(int width, int height, int speed,Color color){
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color=color;
    }
    ;

    private int width;
    private int height;
    private int speed;
    private Color color;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public Color getColor() {
        return color;
    }

}
