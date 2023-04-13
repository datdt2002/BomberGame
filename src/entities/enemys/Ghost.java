package entities.enemys;

import common.common_view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ghost extends Enemy {
    private boolean visible;

    public Ghost(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {
        if (isDie()) {
            interval = 8;
            if (indexAnim < 3) {
                image = common_view.sprite.ghostAnimDead[indexAnim];
                frame++;
                if (frame > interval) {
                    frame = 0;
                    indexAnim++;
                }
            } else {
                common_view.enemies.remove(this);
            }
        } else if (freezer) {
            if (System.currentTimeMillis() - time_start_freezer > expiry_item_freezer) {
                freezer = false;
            }
        } else if (!freezer) {
            int x_bomber = common_view.bomber.getX();
            int y_bomber = common_view.bomber.getY();
            int x_ghost = this.getX();
            int y_ghost = this.getY();

            if (moving && up && !isFreeU(x, y)) {
                moving = false;
            }
            if (moving && down && !isFreeD(x, y)) {
                moving = false;
            }
            if (moving && left && !isFreeL(x, y)) {
                moving = false;
            }
            if (moving && right && !isFreeR(x, y)) {
                moving = false;
            }

            if (x_ghost % 32 == 0 && y_ghost % 32 == 0) {
                ArrayList<String> direct = new ArrayList<String>();
                if (isFreeL(x, y)) {
                    if (x_ghost > 3 * 32) {
                        direct.add("left");
                    }
                }
                if (isFreeR(x, y)) {
                    if (x_ghost < 11 * 32) {
                        direct.add("right");
                    }
                }
                if (isFreeU(x, y)) {
                    if (y_ghost > 6 * 32) {
                        direct.add("up");
                    }
                }
                if (isFreeD(x, y)) {
                    if (y_ghost < 14 * 32) {
                        direct.add("down");
                    }
                }

                random = (int) (Math.random() * 100);
                if (direct.get(random % direct.size()) == "left") {
                    right = false;
                    up = false;
                    down = false;
                    left = true;
                    this.moving = true;
                } else if (direct.get(random % direct.size()) == "right") {
                    right = true;
                    up = false;
                    down = false;
                    left = false;
                    this.moving = true;
                } else if (direct.get(random % direct.size()) == "up") {
                    right = false;
                    up = true;
                    down = false;
                    left = false;
                    this.moving = true;
                } else if (direct.get(random % direct.size()) == "down") {
                    right = false;
                    up = false;
                    down = true;
                    left = false;
                    this.moving = true;
                }
            }

            if (up && moving) {
                this.y -= 1;
                image = common_view.sprite.ghostAnimUp[this.indexAnim];
            }
            if (right && moving) {
                this.x += 1;
                image = common_view.sprite.ghostAnimRight[this.indexAnim];
            }
            if (down && moving) {
                this.y += 1;
                image = common_view.sprite.ghostAnimDown[this.indexAnim];
            }
            if (left && moving) {
                this.x -= 1;
                image = common_view.sprite.ghostAnimLeft[this.indexAnim];
            }

            visible = (x_ghost - x_bomber) * (x_ghost - x_bomber) + (y_ghost - y_bomber) * (y_ghost - y_bomber) < 128 * 128;
            if (moving) {
                anim();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, BufferedImage image, int x, int y) {
        if (visible) {
            super.draw(g2, image, x, y);
        }
    }
}
