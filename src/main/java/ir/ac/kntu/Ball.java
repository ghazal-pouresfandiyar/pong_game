package ir.ac.kntu;

import javafx.scene.paint.Color;

public class Ball{
    private int radius;
    private Color color;

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Ball(int radius) {
        this.radius = radius;
    }
}