import common.common_view;
import entities.Bomb;
import entities.Bomber;
import entities.Item;
import entities.enemys.Enemy;
import event.HandleKey;
import event.HandleMouse;
import map.Map;
import sound.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BombermanGame extends JPanel implements Runnable {
    boolean isRunning;
    Thread thread;

    public BombermanGame() {
        setPreferredSize(new Dimension(common_view.WIDTH, common_view.HEIGHT));
        common_view.handleKey = new HandleKey();
        common_view.handleMouse = new HandleMouse();
        addKeyListener(common_view.handleKey);
        addMouseListener(common_view.handleMouse);
    }

    public static void main(String[] args) {
        common_view.w.setResizable(false);
        common_view.w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        common_view.w.add(new BombermanGame());
        common_view.w.pack();
        common_view.w.setLocationRelativeTo(null);
        common_view.w.setVisible(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }

    public void start() {
        try {
            common_view.view = new BufferedImage(common_view.WIDTH, common_view.HEIGHT, BufferedImage.TYPE_INT_ARGB);
            common_view.view_menu = new BufferedImage(common_view.WIDTH, common_view.HEIGHT, BufferedImage.TYPE_INT_ARGB);
            common_view.map = new Map();
            common_view.passed_levels = new ArrayList<String>();
            common_view.passed_levels = common_view.map.get_level_passed();
            common_view.sprite.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            common_view.sound_game = new SoundEffect("res/audio/theme.wav");
            common_view.sound_game_over = new SoundEffect("res/audio/sound_game_over.wav");
            common_view.sound_game.play_sound();
            common_view.sound_game.loop();
            requestFocus();
            start();
            while (isRunning) {
                update();
                draw();
                Thread.sleep(1000 / 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (common_view.is_playing) {
            if (common_view.bomber != null) {
                common_view.bomber.update();
                if (common_view.win_level && !common_view.win_game) {
                    common_view.map.load_level_passed();
                    common_view.map.setLevel(common_view.map.getLevel() + 1);
                    common_view.handleMouse.clear();
                    common_view.handleMouse.create_data_game();
                    common_view.win_level = false;
                }
            }
            for (int i = 0; i < common_view.enemies.size(); i++) {
                common_view.enemies.get(i).update();
            }
            if (common_view.bombs.size() > 0) {
                for (int i = 0; i < common_view.bombs.size(); i++) {
                    common_view.bombs.get(i).update();
                }
            }
            if (common_view.items.size() > 0) {
                for (int i = 0; i < common_view.items.size(); i++) {
                    common_view.items.get(i).update();
                }
            }
        }
    }

    public void draw() {
        Graphics2D g2 = (Graphics2D) common_view.view.getGraphics();
        int size = common_view.TILESIZE * common_view.SCALE;

        if (common_view.is_playing) {
            for (int i = 0; i < common_view.ROWS; i++) {
                for (int j = 0; j < common_view.COLUMNS; j++) {
                    if (common_view.scene[i][j] == '#') {
                        g2.drawImage(common_view.sprite.coconut, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'z') {
                        g2.drawImage(common_view.sprite.coconut_water, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'x') {
                        g2.drawImage(common_view.sprite.banana, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'c') {
                        g2.drawImage(common_view.sprite.mound, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'v') {
                        g2.drawImage(common_view.sprite.strawberry, j * size, i * size, size, size, null);
                    } else {
                        g2.drawImage(common_view.sprite.soil, j * size, i * size, size, size, null);
                    }
                }
            }
            for (int i = 0; i < common_view.items.size(); i++) {
                Item item = common_view.items.get(i);
                item.draw(g2, item.getImage(), item.getX(), item.getY());
            }
            for (int i = 0; i < common_view.bombs.size(); i++) {
                Bomb bomb = common_view.bombs.get(i);
                common_view.bombs.get(i).draw(g2, bomb.getImage(), bomb.getX(), bomb.getY());
            }
            if (common_view.enemies.size() > 0) {
                for (int i = 0; i < common_view.enemies.size(); i++) {
                    Enemy enemy = common_view.enemies.get(i);
                    enemy.draw(g2, enemy.getImage(), enemy.getX(), enemy.getY());
                }
            } else {
                g2.drawImage(common_view.sprite.portal, 20 * size, 14 * size, size, size, null);
            }
            if (common_view.bomber != null) {
                Bomber bomber = common_view.bomber;
                common_view.bomber.draw(g2, bomber.getImage(), bomber.getX(), bomber.getY());
            }
        } else if (common_view.menu) {
            g2.drawImage(common_view.view_menu, 0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else if (common_view.how_to_play) {
            g2.drawImage(common_view.view_how_to_play, 0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else if (common_view.choose_map) {
            g2.drawImage(common_view.view_choose_map, 0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else if (common_view.pause) {
            g2.drawImage(common_view.view_pause, 0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else if (common_view.game_over) {
            g2.drawImage(common_view.view_game_over, 0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else if (common_view.win_game) {
            g2.drawImage(common_view.view_win_game, 0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        }

        Graphics g = getGraphics();
        g.drawImage(common_view.view, 0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        g.dispose();
    }
}
