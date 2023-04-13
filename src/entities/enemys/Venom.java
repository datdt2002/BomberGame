package entities.enemys;

import common.common_view;

public class Venom extends Enemy {
    public Venom(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    public void update() {
        if (isDie()) {
            interval = 8;
            if (indexAnim < 3) {
                image = common_view.sprite.venomAnimDead[indexAnim];
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
            this.moving = false;
            if (steps <= 0) {
                random = (int) (Math.random() * 5);
                steps = 64;
            }
            if (random == 0) {
                right = true;
                image = common_view.sprite.venomAnimRight[this.indexAnim];
                if (isFreeR(x, y)) {
                    this.x += 1;
                    this.moving = true;
                    steps -= speed;
                }
                if (!isFreeR(x, y)) {
                    this.moving = false;
                    steps = 0;
                }
            } else if (random == 1) {
                left = true;
                image = common_view.sprite.venomAnimLeft[this.indexAnim];
                if (isFreeL(x, y)) {
                    this.x -= 1;
                    this.moving = true;
                    steps -= speed;
                }
                if (!isFreeL(x, y)) {
                    this.moving = false;
                    steps = 0;
                }
            } else if (random == 2) {
                up = true;
                image = common_view.sprite.venomAnimUp[this.indexAnim];
                if (isFreeU(x, y)) {
                    this.y -= 1;
                    this.moving = true;
                    steps -= speed;
                }
                if (!isFreeU(x, y)) {
                    this.moving = false;
                    steps = 0;
                }
            } else if (random == 3) {
                down = true;
                image = common_view.sprite.venomAnimDown[this.indexAnim];
                if (isFreeD(x, y)) {
                    this.y += 1;
                    this.moving = true;
                    steps -= speed;
                }
                if (!isFreeD(x, y)) {
                    this.moving = false;
                    steps = 0;
                }
            } else if (random == 4) {
                this.moving = false;
                steps = 0;
                image = common_view.sprite.venomAnimDown[0];
                random = (int) (Math.random() * 5);
            }

            if (this.moving) {
                anim();
            }
        }
    }
}
