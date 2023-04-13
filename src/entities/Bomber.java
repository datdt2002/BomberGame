package entities;

import common.common_view;
import sound.SoundEffect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomber extends Entity {
    protected int speed = 2;

    protected boolean moving = false;
    protected boolean right = false;
    protected boolean left = false;
    protected boolean up = false;
    protected boolean down = false;
    protected boolean die = false;

    protected boolean no_dead = false;
    protected long time_start_boost;
    protected long time_start_no_dead;

    protected SoundEffect sound_foot;
    protected SoundEffect sound_item_get;

    private int number_of_bombs = 1;
    private final int expiry_item_speed = 5000;
    private final int expiry_item_no_dead = 5000;

    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        sound_foot = new SoundEffect("res/audio/sound_foot.wav");
        sound_item_get = new SoundEffect("res/audio/item_get.wav");
        interval = 5;
    }

    public void update() {
        if (collisionEnemy()) {
            common_view.bomber.die = true;
            common_view.menu = false;
            common_view.bomber.setDie(true);
            common_view.bomber.moving = false;
            if (!common_view.sound_game_over.is_play_music) {
                if (sound_foot.is_play_music) {
                    sound_foot.stop_sound();
                }
                common_view.sound_game.stop_sound();
                common_view.sound_game_over.is_play_music = false;
                common_view.sound_game_over.play_sound();
            }
        } else if (!isDie()) {
            moving = false;
            if (right) {
                if (isFreeR(x, y)) {
                    this.x += speed;
                    moving = true;
                } else {
                    if (y % 32 != 0 && y % 32 <= 16 && common_view.scene[y / 32][x / 32 + 1] == ' ') {
                        this.y -= speed;
                    }
                    if (y % 32 != 0 && y % 32 > 16 && common_view.scene[y / 32 + 1][x / 32 + 1] == ' ') {
                        this.y += speed;
                    }
                }
            }
            if (left) {
                if (isFreeL(x, y)) {
                    this.x -= speed;
                    moving = true;
                } else {
                    if (y % 32 != 0 && y % 32 <= 16 && common_view.scene[y / 32][x / 32 - 1] == ' ') {
                        this.y -= speed;
                    }
                    if (y % 32 != 0 && y % 32 > 16 && common_view.scene[y / 32 + 1][x / 32 - 1] == ' ') {
                        this.y += speed;
                    }
                }
            }
            if (up) {
                if (isFreeU(x, y)) {
                    this.y -= speed;
                    moving = true;
                } else {
                    if (x % 32 != 0 && x % 32 <= 16 && common_view.scene[y / 32 - 1][x / 32] == ' ') {
                        this.x -= speed;
                    }
                    if (x % 32 != 0 && x % 32 > 16 && common_view.scene[y / 32 - 1][x / 32 + 1] == ' ') {
                        this.x += speed;
                    }
                }
            }
            if (down) {
                if (isFreeD(x, y)) {
                    this.y += speed;
                    moving = true;
                } else {
                    if (x % 32 != 0 && x % 32 <= 16 && common_view.scene[y / 32 + 1][x / 32] == ' ') {
                        this.x -= speed;
                    }
                    if (x % 32 != 0 && x % 32 > 16 && common_view.scene[y / 32 + 1][x / 32 + 1] == ' ') {
                        this.x += speed;
                    }
                }
            }

            if (moving) {
                sound_foot.play_sound();
                sound_foot.loop();
                anim();

                if (right) {
                    image = common_view.sprite.playerAnimRight[indexAnim];
                } else if (left) {
                    image = common_view.sprite.playerAnimLeft[indexAnim];
                } else if (up) {
                    image = common_view.sprite.playerAnimUp[indexAnim];
                } else if (down) {
                    image = common_view.sprite.playerAnimDown[indexAnim];
                }
            } else {
                image = common_view.sprite.playerAnimDown[0];
                sound_foot.stop_sound();
            }

            if (System.currentTimeMillis() - common_view.bomber.time_start_boost > expiry_item_speed) {
                this.speed = 2;
            }
            if (System.currentTimeMillis() - common_view.bomber.time_start_no_dead > expiry_item_no_dead) {
                this.no_dead = false;
            }
        } else if (isDie()) {
            interval = 12;
            if (indexAnim < 3) {
                image = common_view.sprite.playerAnimDead[indexAnim];
                frame++;
                if (frame > interval) {
                    frame = 0;
                    indexAnim++;
                }
            } else {
                common_view.is_playing = false;
                common_view.game_over = true;
            }
        }
        if (common_view.enemies.isEmpty()) {
            if (common_view.bomber.getX() == 20 * common_view.size) {
                if (common_view.bomber.getY() == 14 * common_view.size) {
                    common_view.win_level = true;
                    if (common_view.map.getLevel() == 9) {
                        common_view.win_game = true;
                        common_view.map.load_level_passed();
                        common_view.handleMouse.clear();
                        common_view.win_level = false;
                        common_view.is_playing = false;
                    }
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, BufferedImage image, int x, int y) {
        g2.drawImage(this.getImage(), this.getX(), this.getY(), common_view.size, common_view.size, null);
    }

    public void handle_eat_item(int x, int y) {
        char[][] scene = common_view.scene;
        for (int i = 0; i < common_view.items.size(); i++) {
            if (common_view.items.get(i).getX() == x * common_view.size) {
                if (common_view.items.get(i).getY() == y * common_view.size) {
                    if (common_view.items.get(i).getType_item() == "item_speed") {
                        speed = 4;
                        time_start_boost = System.currentTimeMillis();
                    } else if (common_view.items.get(i).getType_item() == "item_deadth") {
                        no_dead = true;
                        time_start_no_dead = System.currentTimeMillis();
                    } else if (common_view.items.get(i).getType_item() == "item_bomb") {
                        if (getNumber_of_bombs() < 2) {
                            setNumber_of_bombs(getNumber_of_bombs() + 1);
                        }
                    } else if (common_view.items.get(i).getType_item() == "item_freezer") {
                        for (int j = 0; j < common_view.enemies.size(); j++) {
                            common_view.enemies.get(j).setFreezer(true);
                            common_view.enemies.get(j).setTime_start_freezer(System.currentTimeMillis());
                        }
                    }
                    common_view.items.remove(i);
                    sound_item_get.is_play_music = false;
                    sound_item_get.play_sound();
                    break;
                }
            }
        }

        scene[y][x] = ' ';
        common_view.has_item[y][x] = 0;
    }

    public boolean collisionEnemy() {
        int size = common_view.TILESIZE * common_view.SCALE;
        int x1 = common_view.bomber.getX();
        int y1 = common_view.bomber.getY();
        for (int i = 0; i < common_view.enemies.size(); i++) {
            int x2 = common_view.enemies.get(i).getX();
            int y2 = common_view.enemies.get(i).getY();
            if ((x1 + size > x2) && (x2 + size > x1) && (y1 + size > y2) && (y2 + size > y1)) {
                if (common_view.bomber.isNo_dead()) {
                    common_view.enemies.get(i).setDie(true);
                    return false;
                } else {
                    common_view.bomber.moving = false;
                    common_view.enemies.get(i).setDie(true);
                }
                return true;
            }
        }
        return false;
    }

    public boolean isFreeR(int x, int y) {
        char[][] scene = common_view.scene;
        boolean canMove = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (x + speed > scene[0].length * size) {
            return false;
        } else {
            if (x % size != 0) {
                if ((x + 2) % size == 0) {
                    this.x += 2;
                } else {
                    canMove =true;
                }
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;

                if (scene[y1][x1 + 1] == ' ' || scene[y1][x1 + 1] == '1') {
                    handle_eat_item(x1 + 1, y1);
                    canMove =true;
                } else {
                    canMove =false;
                }
            } else if (y % size != 0 && x % size == 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1][x1 + 1] == '1') && scene[y1 + 1][x1 + 1] == ' ') {
                    handle_eat_item(x1 + 1, y1);
                    canMove =true;
                } else if (scene[y1][x1 + 1] == ' ' && scene[y1 + 1][x1 + 1] == '1') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    canMove =true;
                } else if (scene[y1][x1 + 1] == '1') {
                    handle_eat_item(x1 + 1, y1);
                    canMove =true;
                } else if (scene[y1 + 1][x1 + 1] == '1') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    canMove =true;
                } else if (scene[y1][x1 + 1] == '1' && scene[y1 + 1][x1 + 1] == '1') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    handle_eat_item(x1 + 1, y1);
                    canMove =true;
                } else if (scene[y1][x1 + 1] == ' ' && scene[y1 + 1][x1 + 1] == ' ') {
                    canMove =true;
                }
            }
        }
        return canMove;
    }

    public boolean isFreeL(int x, int y) {
        char[][] scene = common_view.scene;
        boolean canMove =false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (x - speed < size) {
            return false;
        } else {
            if (x % size != 0) {
                if ((x - 2) % size == 0) {
                    this.x -= 2;
                } else {
                    canMove =true;
                }
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1][x1 - 1] == ' ' || scene[y1][x1 - 1] == '1') {
                    handle_eat_item(x1 - 1, y1);
                    canMove =true;
                } else {
                    canMove =false;
                }
            } else if (y % size != 0 && x % size == 0) {
                int y1 = y / size;
                int x1 = x / size;
                if (scene[y1][x1 - 1] == '1' && scene[y1 + 1][x1 - 1] == ' ') {
                    handle_eat_item(x1 - 1, y1);
                    canMove =true;
                }
                if (scene[y1][x1 - 1] == ' ' && scene[y1 + 1][x1 - 1] == '1') {
                    handle_eat_item(x1 - 1, y1 + 1);
                    canMove =true;
                }
                if (scene[y1][x1 - 1] == '1') {
                    handle_eat_item(x1 - 1, y1);
                    canMove =true;
                }
                if (scene[y1 + 1][x1 - 1] == '1') {
                    handle_eat_item(x1 - 1, y1 + 1);
                    canMove =true;
                }
                if (scene[y1][x1 - 1] == '1' && scene[y1 + 1][x1 - 1] == '1') {
                    handle_eat_item(x1 - 1, y1);
                    handle_eat_item(x1 - 1, y1 + 1);
                    canMove =true;
                }
                if (scene[y1][x1 - 1] == ' ' && scene[y1 + 1][x1 - 1] == ' ') {
                    canMove =true;
                }
            }
        }
        return canMove;
    }

    public boolean isFreeU(int x, int y) {
        char[][] scene = common_view.scene;
        boolean canMove =false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (y - speed < size) {
            return false;
        } else {
            if (y % size != 0) {
                if ((y - 2) % size == 0) {
                    this.y -= 2;
                } else {
                    canMove =true;
                }
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1 - 1][x1] == ' ' || scene[y1 - 1][x1] == '1') {
                    handle_eat_item(x1, y1 - 1);
                    canMove =true;
                } else {
                    canMove =false;
                }
            } else if (y % size == 0 && x % size != 0) {
                int y1 = y / size;
                int x1 = x / size;
                if (scene[y1 - 1][x1 + 1] == '1' && scene[y1 - 1][x1] == ' ') {
                    handle_eat_item(x1 + 1, y1 - 1);
                    canMove =true;
                }
                if (scene[y1 - 1][x1 + 1] == ' ' && scene[y1 - 1][x1] == '1') {
                    handle_eat_item(x1, y1 - 1);
                    canMove =true;
                }
                if (scene[y1 - 1][x1 + 1] == '1') {
                    handle_eat_item(x1, y1 - 1);
                    canMove =true;
                }
                if (scene[y1 - 1][x1] == '1') {
                    handle_eat_item(x1, y1 - 1);
                    canMove =true;
                }
                if (scene[y1 - 1][x1 + 1] == '1' && scene[y1 - 1][x1] == '1') {
                    handle_eat_item(x1 + 1, y1 - 1);
                    handle_eat_item(x1, y1 - 1);
                    canMove =true;
                }
                if (scene[y1 - 1][x1 + 1] == ' ' && scene[y1 - 1][x1] == ' ') {
                    canMove =true;
                }
            }
        }
        return canMove;
    }

    public boolean isFreeD(int x, int y) {
        char[][] scene = common_view.scene;
        boolean canMove =false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (y + speed > scene.length * size) {
            return false;
        } else {
            if (y % size != 0) {
                if ((y + 2) % size == 0) {
                    this.y += 2;
                } else {
                    canMove =true;
                }
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1 + 1][x1] == ' ' || scene[y1 + 1][x1] == '1') {
                    handle_eat_item(x1, y1 + 1);
                    canMove =true;
                } else {
                    canMove =false;
                }
            } else if (y % size == 0 && x % size != 0) {
                int y1 = y / size;
                int x1 = x / size;
                if (scene[y1 + 1][x1 + 1] == '1' && scene[y1 + 1][x1] == ' ') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    canMove =true;
                }
                if (scene[y1 + 1][x1 + 1] == ' ' && scene[y1 + 1][x1] == '1') {
                    handle_eat_item(x1, y1 + 1);
                    canMove =true;
                }
                if (scene[y1 + 1][x1 + 1] == '1') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    canMove =true;
                }
                if (scene[y1 + 1][x1] == '1') {
                    handle_eat_item(x1, y1 + 1);
                    canMove =true;
                }
                if (scene[y1 + 1][x1 + 1] == '1' && scene[y1 + 1][x1] == '1') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    handle_eat_item(x1, y1 + 1);
                    canMove =true;
                }
                if (scene[y1 + 1][x1 + 1] == ' ' && scene[y1 + 1][x1] == ' ') {
                    canMove =true;
                }
            }
        }
        return canMove;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isNo_dead() {
        return no_dead;
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public int getNumber_of_bombs() {
        return number_of_bombs;
    }

    public void setNumber_of_bombs(int number_of_bombs) {
        this.number_of_bombs = number_of_bombs;
    }
}

