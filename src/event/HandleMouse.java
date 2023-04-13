package event;

import common.common_view;
import entities.Bomber;
import sound.SoundEffect;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HandleMouse implements MouseListener {
    private SoundEffect sound_click = new SoundEffect("res/audio/click.wav");
    public void clear() {
        common_view.bomber = null;
        common_view.enemies.clear();
        common_view.bombs.clear();
        common_view.items.clear();
    }

    public void create_data_game() {
        common_view.map.create_Map(common_view.map.getLevel());
        common_view.bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);
    }

    public boolean check_position(int x, int y, int A, int B, int C, int D) {
        return x > A && x < B
                && y > C && y < D;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        sound_click.is_play_music = false;
        sound_click.play_sound();
        if (common_view.menu) {
            if (check_position(e.getX(), e.getY(), 413, 680, 360, 400)) {
                common_view.menu = false;
                common_view.how_to_play = true;
            } else if (check_position(e.getX(), e.getY(), 485, 615, 245, 285)) {
                common_view.menu = false;
                common_view.choose_map = true;
            }
        } else if (common_view.how_to_play) {
            if (check_position(e.getX(), e.getY(), 0, 102, 0, 58)) {
                common_view.how_to_play = false;
                common_view.menu = true;
            }
        } else if (common_view.choose_map) {
            if (check_position(e.getX(), e.getY(), 40, 110, 32, 110)) {
                common_view.map.setLevel(1);
                clear();
                create_data_game();
                common_view.choose_map = false;
                common_view.is_playing = true;
            } else if (check_position(e.getX(), e.getY(), 170, 240, 32, 110)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("1") >= 0) {
                    common_view.map.setLevel(2);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            } else if (check_position(e.getX(), e.getY(), 280, 350, 32, 110)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("2") >= 0) {
                    common_view.map.setLevel(3);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            } else if (check_position(e.getX(), e.getY(), 410, 480, 32, 110)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("3") >= 0) {
                    common_view.map.setLevel(4);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            } else if (check_position(e.getX(), e.getY(), 520, 590, 32, 110)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("4") >= 0) {
                    common_view.map.setLevel(5);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            } else if (check_position(e.getX(), e.getY(), 170, 240, 170, 260)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("5") >= 0) {
                    common_view.map.setLevel(6);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            } else if (check_position(e.getX(), e.getY(), 280, 350, 170, 260)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("6") >= 0) {
                    common_view.map.setLevel(7);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            } else if (check_position(e.getX(), e.getY(), 410, 480, 170, 260)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("7") >= 0) {
                    common_view.map.setLevel(8);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            } else if (check_position(e.getX(), e.getY(), 540, 630, 170, 280)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("8") >= 0) {
                    common_view.map.setLevel(9);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            }
        } else if (common_view.pause) {
            if (check_position(e.getX(), e.getY(), 156, 242, 85, 108)) {
                clear();
                create_data_game();
                common_view.pause = false;
                common_view.is_playing = true;
            } else if (check_position(e.getX(), e.getY(), 90, 152, 404, 426)) {
                common_view.pause = false;
                common_view.menu = true;
                clear();
            } else if (check_position(e.getX(), e.getY(), 403, 457, 305, 332)) {
                common_view.pause = false;
                common_view.is_playing = true;
            }
        } else if (common_view.game_over) {
            if (check_position(e.getX(), e.getY(), 103, 234, 379, 423)) {
                common_view.game_over = false;
                common_view.menu = true;
                common_view.sound_game_over.stop_sound();
                common_view.sound_game.is_play_music = false;
                common_view.sound_game.play_sound();
                clear();
            } else if (check_position(e.getX(), e.getY(), 381, 525, 352, 396)) {
                common_view.game_over = false;
                common_view.is_playing = true;
                clear();
                create_data_game();
                common_view.sound_game_over.stop_sound();
                common_view.sound_game.is_play_music = false;
                common_view.sound_game.play_sound();
            }
        } else if (common_view.win_game) {
            if (check_position(e.getX(), e.getY(), 81, 260, 397, 450)) {
                common_view.win_game = false;
                common_view.menu = true;
                clear();
            } else if (check_position(e.getX(), e.getY(), 511, 628, 397, 450)) {
                clear();
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
