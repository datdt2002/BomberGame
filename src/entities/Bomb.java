package entities;

import common.common_view;
import entities.enemys.Enemy;
import sound.SoundEffect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends Entity {
    private BufferedImage img_bomb_up;
    private BufferedImage img_bomb_down;
    private BufferedImage img_bomb_left;
    private BufferedImage img_bomb_right;

    private boolean exploded;
    private int countToExplode;
    private final int intervalToExplode = 8;

    private final SoundEffect sound_explosion;

    public Bomb(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        sound_explosion = new SoundEffect("res/audio/bomb_explosion.wav");
    }

    public void animBomb() {
        image = common_view.sprite.bombAnim[this.getIndexAnim()];
        frame++;
        if (frame > interval) {
            frame = 0;
            indexAnim++;
            if (indexAnim > 2) {
                indexAnim = 0;
                countToExplode++;
            }
            if (countToExplode >= intervalToExplode) {
                exploded = true;
            }
        }
    }

    public boolean nguoicobinokhong() {
        if (common_view.bomber.isNo_dead() || common_view.bomber.isDie()) {
            return false;
        }
        int size = common_view.TILESIZE * common_view.SCALE;
        int x_bomber = common_view.bomber.getX();
        int y_bomber = common_view.bomber.getY();
        int x_bomb = this.getX() / (common_view.TILESIZE * common_view.SCALE);
        int y_bomb = this.getY() / (common_view.TILESIZE * common_view.SCALE);
        if (x_bomber > (x_bomb - 2) * size && x_bomber < (x_bomb - 1) * size) {
            if (y_bomber > (y_bomb - 1) * size && y_bomber < (y_bomb + 1) * size) {
                return true;
            }
        }
        if (x_bomber > (x_bomb - 1) * size && x_bomber < (x_bomb) * size) {
            if (y_bomber > (y_bomb - 2) * size && y_bomber < (y_bomb + 2) * size) {
                return true;
            }
        }
        if (x_bomber == (x_bomb - 1) * size || x_bomber == (x_bomb + 1) * size) {
            if (y_bomber > (y_bomb - 1) * size && y_bomber < (y_bomb + 1) * size) {
                return true;
            }
        }
        if (x_bomber >= (x_bomb) * size && x_bomber < (x_bomb + 1) * size) {
            if (y_bomber > (y_bomb - 2) * size && y_bomber < (y_bomb + 2) * size) {
                return true;
            }
        }
        if (x_bomber > (x_bomb + 1) * size && x_bomber < (x_bomb + 2) * size) {
            return y_bomber > (y_bomb - 1) * size && y_bomber < (y_bomb + 1) * size;
        }
        return false;
    }

    public boolean botcobinokhong(Enemy enemy) {
        int x_bomb = this.getX() / (common_view.TILESIZE * common_view.SCALE);
        int y_bomb = this.getY() / (common_view.TILESIZE * common_view.SCALE);
        int x_enemy = enemy.getX();
        int y_enemy = enemy.getY();
        int size = common_view.TILESIZE * common_view.SCALE;
        if (x_enemy > (x_bomb - 2) * size && x_enemy < (x_bomb - 1) * size) {
            if (y_enemy > (y_bomb - 1) * size && y_enemy < (y_bomb + 1) * size) {
                return true;
            }
        }
        if (x_enemy > (x_bomb - 1) * size && x_enemy < (x_bomb) * size) {
            if (y_enemy > (y_bomb - 2) * size && y_enemy < (y_bomb + 2) * size) {
                return true;
            }
        }
        if (x_enemy == (x_bomb - 1) * size || x_enemy == (x_bomb + 1) * size) {
            if (y_enemy > (y_bomb - 1) * size && y_enemy < (y_bomb + 1) * size) {
                return true;
            }
        }
        if (x_enemy >= (x_bomb) * size && x_enemy < (x_bomb + 1) * size) {
            if (y_enemy > (y_bomb - 2) * size && y_enemy < (y_bomb + 2) * size) {
                return true;
            }
        }
        if (x_enemy > (x_bomb + 1) * size && x_enemy < (x_bomb + 2) * size) {
            return y_enemy > (y_bomb - 1) * size && y_enemy < (y_bomb + 1) * size;
        }
        return false;
    }

    public void create_item(int _x, int _y) {
        if (common_view.has_item[_y][_x] == 1) {
            int random = (int) (Math.random() * 100 + 1);
            Item item = new Item(common_view.TILESIZE * _x, common_view.TILESIZE * _y);
            if (random % 4 == 0) {
                common_view.scene[_y][_x] = '1';
                item.setType_item("item_speed");
                common_view.has_item[_y][_x] = 0;
            } else if (random % 4 == 1) {
                common_view.scene[_y][_x] = '1';
                item.setType_item("item_deadth");
                common_view.has_item[_y][_x] = 0;
            } else if (random % 4 == 2) {
                common_view.scene[_y][_x] = '1';
                item.setType_item("item_freezer");
                common_view.has_item[_y][_x] = 0;
            } else if (random % 4 == 3) {
                common_view.scene[_y][_x] = '1';
                item.setType_item("item_bomb");
                common_view.has_item[_y][_x] = 0;
            }
            common_view.items.add(item);
        } else if (common_view.has_item[_y][_x] == 0) {
            common_view.scene[_y][_x] = ' ';
        }
    }

    public void animExplosion() {
        char[][] scene = common_view.scene;
        int size = common_view.size;
        image = common_view.sprite.fontExplosion[this.getIndexAnim()];
        if (scene[this.getY() / size - 1][this.getX() / size] != '#') {
            img_bomb_up = common_view.sprite.upExplosion[this.getIndexAnim()];
        }
        if (scene[this.getY() / size + 1][this.getX() / size] != '#') {
            img_bomb_down = common_view.sprite.downExplosion[this.getIndexAnim()];
        }
        if (scene[this.getY() / size][this.getX() / size - 1] != '#') {
            img_bomb_left = common_view.sprite.leftExplosion[this.getIndexAnim()];

        }
        if (scene[this.getY() / size][this.getX() / size + 1] != '#') {
            img_bomb_right = common_view.sprite.rightExplosion[this.getIndexAnim()];
        }
        int _x = this.getX() / (common_view.TILESIZE * common_view.SCALE);
        int _y = this.getY() / (common_view.TILESIZE * common_view.SCALE);
        frame++;
        if (frame >= interval) {
            frame = 0;
            indexAnim++;
            if (indexAnim == 3) {
                common_view.scene[_y][_x] = ' ';
                create_item(_x, _y + 1);
                create_item(_x, _y - 1);
                create_item(_x + 1, _y);
                create_item(_x - 1, _y);

                if (nguoicobinokhong()) {
                    common_view.bomber.die = true;
                    common_view.menu = false;
                    common_view.bomber.setDie(true);
                    common_view.bomber.moving = false;
                    common_view.bomber.sound_foot.stop_sound();
                    common_view.sound_game.stop_sound();
                    common_view.sound_game_over.is_play_music = false;
                    common_view.sound_game_over.play_sound();
                }
                for (int j = 0; j < common_view.enemies.size(); j++) {
                    if (botcobinokhong(common_view.enemies.get(j))) {
                        if (!common_view.enemies.get(j).isNo_dead()) {
                            common_view.enemies.get(j).setDie(true);
                        }
                    }
                }
                common_view.bombs.remove(this);
            }
        }
    }

    public void update() {
        if (common_view.bombs.size() != 0) {
            for (int i = 0; i < common_view.bombs.size(); i++) {
                common_view.bombs.get(i).animBomb();
                if (common_view.bombs.get(i).exploded) {
                    common_view.bombs.get(i).sound_explosion.play_sound();
                    common_view.bombs.get(i).animExplosion();
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, BufferedImage image, int x, int y) {
        int size = common_view.size;
        if (!this.isExploded()) {
            g2.drawImage(this.getImage(), this.getX(), this.getY(), size, size, null);
        } else {
            g2.drawImage(this.getImage(), this.getX(), this.getY(), size, size, null);
            g2.drawImage(this.getImg_bomb_up(), this.getX(), this.getY() - size, size, size, null);
            g2.drawImage(this.getImg_bomb_down(), this.getX(), this.getY() + size, size, size, null);
            g2.drawImage(this.getImg_bomb_left(), this.getX() - size, this.getY(), size, size, null);
            g2.drawImage(this.getImg_bomb_right(), this.getX() + size, this.getY(), size, size, null);
        }
    }

    public BufferedImage getImg_bomb_up() {
        return img_bomb_up;
    }

    public BufferedImage getImg_bomb_down() {
        return img_bomb_down;
    }

    public BufferedImage getImg_bomb_left() {
        return img_bomb_left;
    }

    public BufferedImage getImg_bomb_right() {
        return img_bomb_right;
    }

    public boolean isExploded() {
        return exploded;
    }
}
