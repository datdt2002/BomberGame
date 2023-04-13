package graphics;

import common.common_view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    public BufferedImage player;
    public BufferedImage[] playerAnimUp;
    public BufferedImage[] playerAnimDown;
    public BufferedImage[] playerAnimRight;
    public BufferedImage[] playerAnimLeft;
    public BufferedImage[] playerAnimDead;

    public BufferedImage venom;
    public BufferedImage[] venomAnimUp;
    public BufferedImage[] venomAnimDown;
    public BufferedImage[] venomAnimRight;
    public BufferedImage[] venomAnimLeft;
    public BufferedImage[] venomAnimDead;

    public BufferedImage bat;
    public BufferedImage[] batAnimUp;
    public BufferedImage[] batAnimDown;
    public BufferedImage[] batAnimRight;
    public BufferedImage[] batAnimLeft;
    public BufferedImage[] batAnimDead;

    public BufferedImage ghost;
    public BufferedImage[] ghostAnimUp;
    public BufferedImage[] ghostAnimDown;
    public BufferedImage[] ghostAnimRight;
    public BufferedImage[] ghostAnimLeft;
    public BufferedImage[] ghostAnimDead;

    public BufferedImage[] bombAnim;
    public BufferedImage[] fontExplosion;
    public BufferedImage[] rightExplosion;
    public BufferedImage[] leftExplosion;
    public BufferedImage[] upExplosion;
    public BufferedImage[] downExplosion;

    public BufferedImage[] item_speed;
    public BufferedImage[] item_deadth;
    public BufferedImage[] item_freezer;
    public BufferedImage[] item_bomb;

    public BufferedImage blockTile;
    public BufferedImage spriteSheet;
    public BufferedImage grass;
    public BufferedImage brick;
    public BufferedImage wall;
    public BufferedImage coconut;
    public BufferedImage coconut_water;
    public BufferedImage strawberry;
    public BufferedImage banana;
    public BufferedImage soil;
    public BufferedImage mound;
    public BufferedImage portal;

    public void load() {
        try {
            common_view.view_menu = ImageIO.read(new File("res/textures/menu.png"));
            common_view.view_how_to_play = ImageIO.read(new File("res/textures/how_to_play.png"));
            common_view.view_choose_map = ImageIO.read(new File("res/textures/choose_level.png"));
            common_view.view_pause = ImageIO.read(new File("res/textures/pause.png"));
            common_view.view_game_over = ImageIO.read(new File("res/textures/game_over.png"));
            common_view.view_win_game = ImageIO.read(new File("res/textures/win_game.png"));
            spriteSheet = ImageIO.read(new File("res/textures/classic.png"));

            int size = common_view.TILESIZE;

            player = spriteSheet.getSubimage(8 * size, 11 * size, size, size);
            playerAnimUp = new BufferedImage[3];
            playerAnimDown = new BufferedImage[3];
            playerAnimRight = new BufferedImage[3];
            playerAnimLeft = new BufferedImage[3];
            playerAnimDead = new BufferedImage[3];

            venom = spriteSheet.getSubimage(2 * size, 0, size, size);
            venomAnimUp = new BufferedImage[3];
            venomAnimDown = new BufferedImage[3];
            venomAnimRight = new BufferedImage[3];
            venomAnimLeft = new BufferedImage[3];
            venomAnimDead = new BufferedImage[3];

            bat = spriteSheet.getSubimage(2 * size, 0, size, size);
            batAnimUp = new BufferedImage[3];
            batAnimDown = new BufferedImage[3];
            batAnimRight = new BufferedImage[3];
            batAnimLeft = new BufferedImage[3];
            batAnimDead = new BufferedImage[3];

            ghost = spriteSheet.getSubimage(2 * size, 0, size, size);
            ghostAnimUp = new BufferedImage[3];
            ghostAnimDown = new BufferedImage[3];
            ghostAnimRight = new BufferedImage[3];
            ghostAnimLeft = new BufferedImage[3];
            ghostAnimDead = new BufferedImage[3];

            bombAnim = new BufferedImage[3];
            fontExplosion = new BufferedImage[3];
            upExplosion = new BufferedImage[3];
            downExplosion = new BufferedImage[3];
            leftExplosion = new BufferedImage[3];
            rightExplosion = new BufferedImage[3];

            item_speed = new BufferedImage[3];
            item_deadth = new BufferedImage[3];
            item_freezer = new BufferedImage[3];
            item_bomb = new BufferedImage[3];

            for (int i = 0; i < 3; i++) {
                playerAnimLeft[i] = spriteSheet.getSubimage((i + 12) * size, 7 * size, size, size);
                playerAnimRight[i] = spriteSheet.getSubimage(15 * size, (i + 5) * size, size, size);
                playerAnimUp[i] = spriteSheet.getSubimage((i + 12) * size, 6 * size, size, size);
                playerAnimDown[i] = spriteSheet.getSubimage((i + 12) * size, 5 * size, size, size);
                playerAnimDead[i] = spriteSheet.getSubimage((i + 12) * size, 8 * size, size, size);

                venomAnimLeft[i] = spriteSheet.getSubimage(3 * size, i * size, size, size);
                venomAnimRight[i] = spriteSheet.getSubimage(size, i * size, size, size);
                venomAnimUp[i] = spriteSheet.getSubimage(0, i * size, size, size);
                venomAnimDown[i] = spriteSheet.getSubimage(2 * size, i * size, size, size);
                venomAnimDead[i] = spriteSheet.getSubimage((i + 4) * size, 2 * size, size, size);

                batAnimLeft[i] = spriteSheet.getSubimage((i + 3) * size, 12 * size, size, size);
                batAnimRight[i] = spriteSheet.getSubimage(i * size, 13 * size, size, size);
                batAnimUp[i] = spriteSheet.getSubimage((i + 3) * size, 13 * size, size, size);
                batAnimDown[i] = spriteSheet.getSubimage(i * size, 12 * size, size, size);
                batAnimDead[i] = spriteSheet.getSubimage(15 * size, i * size, size, size);

                ghostAnimLeft[i] = spriteSheet.getSubimage((i + 11) * size, 10 * size, size, size);
                ghostAnimRight[i] = spriteSheet.getSubimage((i + 8) * size, 11 * size, size, size);
                ghostAnimUp[i] = spriteSheet.getSubimage((i + 11) * size, 11 * size, size, size);
                ghostAnimDown[i] = spriteSheet.getSubimage((i + 8) * size, 10 * size, size, size);
                ghostAnimDead[i] = spriteSheet.getSubimage(15 * size, i * size, size, size);

                bombAnim[i] = spriteSheet.getSubimage(i * size, 3 * size, size, size);
                fontExplosion[i] = spriteSheet.getSubimage(0, (i + 4) * size, size, size);
                upExplosion[i] = spriteSheet.getSubimage((i + 1) * size, 4 * size, size, size);
                downExplosion[i] = spriteSheet.getSubimage((i + 1) * size, 6 * size, size, size);
                leftExplosion[i] = spriteSheet.getSubimage(0, (i + 7) * size, size, size);
                rightExplosion[i] = spriteSheet.getSubimage(2 * size, (i + 7) * size, size, size);

                item_speed[i] = spriteSheet.getSubimage(i * size, 15 * size, size, size);
                item_deadth[i] = spriteSheet.getSubimage((i + 3) * size, 15 * size, size, size);
                item_freezer[i] = spriteSheet.getSubimage((i + 6) * size, 15 * size, size, size);
                item_bomb[i] = spriteSheet.getSubimage((i + 9) * size, 15 * size, size, size);
            }

            blockTile = spriteSheet.getSubimage(5 * size, 14 * size, size, size);
            grass = spriteSheet.getSubimage(6 * size, size, size, size);
            brick = spriteSheet.getSubimage(7 * size, 0, size, size);
            wall = spriteSheet.getSubimage(5 * size, 0, size, size);
            coconut = spriteSheet.getSubimage(5 * size, 14 * size, size, size);
            coconut_water = spriteSheet.getSubimage(6 * size, 14 * size, size, size);
            strawberry = spriteSheet.getSubimage(3 * size, 14 * size, size, size);
            banana = spriteSheet.getSubimage(4 * size, 14 * size, size, size);
            soil = spriteSheet.getSubimage(4 * size, size, size, size);
            mound = spriteSheet.getSubimage(7 * size, 14 * size, size, size);
            portal = spriteSheet.getSubimage(4 * size, 0, size, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
