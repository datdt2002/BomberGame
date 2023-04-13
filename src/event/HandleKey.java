package event;

import common.common_view;
import entities.Bomb;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HandleKey implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_M) {
            if (common_view.off_volume == false) {
                common_view.off_volume = true;
                if (common_view.sound_game.is_play_music) {
                    common_view.sound_game.stop_sound();
                }
            } else {
                common_view.off_volume = false;
                common_view.sound_game.play_sound();
            }
        }

        if (common_view.is_playing) {
            if (!common_view.bomber.isDie()) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (common_view.bombs.size() < common_view.bomber.getNumber_of_bombs()) {
                        int size = common_view.TILESIZE * common_view.SCALE;
                        Bomb bomb = new Bomb(0, 0);
                        int _x = (common_view.bomber.getX() / size);
                        int _y = (common_view.bomber.getY() / size);

                        if (common_view.bomber.getX() % size > (size / 2)
                                && common_view.bomber.getY() % size > (size / 2)) {
                            common_view.scene[_y + 1][_x + 1] = '9';
                            bomb.setX((_x + 1) * size);
                            bomb.setY((_y + 1) * size);
                        } else if (common_view.bomber.getX() % size > (size / 2)) {
                            common_view.scene[_y][_x + 1] = '9';
                            bomb.setX((_x + 1) * size);
                            bomb.setY(_y * size);
                        } else if (common_view.bomber.getY() % size > (size / 2)) {
                            common_view.scene[_y + 1][_x] = '9';
                            bomb.setX(_x * size);
                            bomb.setY((_y + 1) * size);
                        } else {
                            common_view.scene[_y][_x] = '9';
                            bomb.setX(_x * size);
                            bomb.setY(_y * size);
                        }
                        common_view.bombs.add(bomb);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    common_view.bomber.setRight(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    common_view.bomber.setLeft(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    common_view.bomber.setUp(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    common_view.bomber.setDown(true);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                common_view.is_playing = false;
                common_view.pause = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (common_view.is_playing && common_view.bomber != null) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                common_view.bomber.setRight(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                common_view.bomber.setLeft(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                common_view.bomber.setUp(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                common_view.bomber.setDown(false);
            }
        }
    }
}
