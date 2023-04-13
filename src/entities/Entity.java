package entities;

import common.common_view;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int x;
    protected int y;

    protected BufferedImage image;

    protected int frame = 0;
    protected int interval = 5;
    protected int indexAnim = 0;

    public Entity(int xUnit, int yUnit) {
        this.x = xUnit * common_view.SCALE;
        this.y = yUnit * common_view.SCALE;
    }

    public void anim() {
        frame++;
        if (frame > interval) {
            frame = 0;
            indexAnim++;
            if (indexAnim > 2) {
                indexAnim = 0;
            }
        }
    }

    public abstract void update();

    public abstract void draw(Graphics2D g2, BufferedImage image, int x, int y);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getIndexAnim() {
        return indexAnim;
    }
}
