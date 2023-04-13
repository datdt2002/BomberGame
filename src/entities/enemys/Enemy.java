package entities.enemys;

import common.common_view;
import entities.Bomber;
import sound.SoundEffect;

public abstract class Enemy extends Bomber {
    protected int steps = 0;
    protected int random = 0;

    protected boolean freezer = false;
    protected long time_start_freezer;
    protected int expiry_item_freezer = 5000;

    public Enemy(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    public void handle_eat_item(int x, int y) {
        char[][] scene = common_view.scene;
        for (int i = 0; i < common_view.items.size(); i++) {
            if (x * common_view.size == common_view.items.get(i).getX()) {
                if (y * common_view.size == common_view.items.get(i).getY()) {
                    common_view.items.remove(i);
                    break;
                }
            }
        }

        scene[y][x] = ' ';
        common_view.has_item[y][x] = 0;
    }

    public void setTime_start_freezer(long time_start_freezer) {
        this.time_start_freezer = time_start_freezer;
    }

    public void setFreezer(boolean freezer) {
        this.freezer = freezer;
    }
}
