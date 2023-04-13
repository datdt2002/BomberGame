package entities.enemys;

import common.common_view;

public class Bat extends Enemy {
    public Bat(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {
        if (isDie()) {
            if (indexAnim < 3) {
                image = common_view.sprite.batAnimDead[indexAnim];
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
            int x_bat = this.getX();
            int y_bat = this.getY();

            if (x_bat % 32 == 0 && y_bat % 32 == 0) {
                this.moving = false;
                if (y_bat == y_bomber) {
                    if (x_bat < x_bomber) {
                        right = true;
                        up = false;
                        down = false;
                        left = false;
                        this.moving = true;
                    } else {
                        right = false;
                        up = false;
                        down = false;
                        left = true;
                        this.moving = true;
                    }
                } else if (x_bat == x_bomber) {
                    if (y_bat < x_bomber) {
                        right = false;
                        up = false;
                        down = true;
                        left = false;
                        this.moving = true;
                    } else {
                        right = false;
                        up = true;
                        down = false;
                        left = false;
                        this.moving = true;
                    }
                }
                if (x_bat < x_bomber && y_bat > y_bomber) {
                    random = (int) (Math.random() * 5);
                    if (random % 2 == 1) {
                        right = true;
                        up = false;
                        down = false;
                        left = false;
                        this.moving = true;
                    } else {
                        right = false;
                        up = true;
                        down = false;
                        left = false;
                        this.moving = true;
                    }
                }
                if (x_bat > x_bomber && y_bat > y_bomber) {
                    random = (int) (Math.random() * 5);
                    if (random % 2 == 1) {
                        left = true;
                        up = false;
                        down = false;
                        right = false;
                        this.moving = true;
                    } else {
                        right = false;
                        up = true;
                        down = false;
                        left = false;
                        this.moving = true;
                    }
                }

                if (x_bat < x_bomber && y_bat < y_bomber) {
                    random = (int) (Math.random() * 5);
                    if (random % 2 == 1) {
                        right = true;
                        up = false;
                        down = false;
                        left = false;
                        this.moving = true;
                    } else {
                        right = false;
                        up = false;
                        down = true;
                        left = false;
                        this.moving = true;
                    }
                }

                if (x_bat > x_bomber && y_bat < y_bomber) {
                    random = (int) (Math.random() * 5);
                    if (random % 2 == 1) {
                        right = false;
                        up = false;
                        down = false;
                        left = true;
                        this.moving = true;
                    } else {
                        right = false;
                        up = false;
                        down = true;
                        left = false;
                        this.moving = true;
                    }
                }
            }

            if (up && moving) {
                this.y -= 1;
                image = common_view.sprite.batAnimUp[this.indexAnim];
            }
            if (right && moving) {
                this.x += 1;
                image = common_view.sprite.batAnimRight[this.indexAnim];
            }
            if (down && moving) {
                this.y += 1;
                image = common_view.sprite.batAnimDown[this.indexAnim];
            }
            if (left && moving) {
                this.x -= 1;
                image = common_view.sprite.batAnimLeft[this.indexAnim];
            }

            if (moving) {
                anim();
            }
        }
    }
}
