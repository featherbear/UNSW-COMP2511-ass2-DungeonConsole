import java.io.FileNotFoundException;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.InvincibilityPotion;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Sword;
import unsw.dungeon.entity.Treasure;
import unsw.dungeon.entity.Wall;
import unsw.dungeon.entity.meta.Entity;

public class GameController {
	private DungeonLoader loader;
	private Dungeon dungeon;
	private Player player;

	public GameController(String mapFile) throws FileNotFoundException {
		loader = new DungeonLoader(mapFile);
	}

	private char entityToChar(Entity e) {
		if (e instanceof Wall) {
			return '\u2588';
		} else if (e instanceof Portal) {
			return '\u25EF';
		} else if (e instanceof Player) {
			return '\u00B7';
		} else if (e instanceof Sword) {
			return 'S';
		} else if (e instanceof Enemy) {
			return 'E';
		} else if (e instanceof Treasure) {
			return '$';
		} else if (e instanceof InvincibilityPotion) {
			return 'P';
		}
		return '?';
	}

	public void initialise() {
		dungeon = loader.load();
		player = dungeon.getPlayer();
	}

	public void moveUp() {
		player.moveUp();
	}

	public void moveRight() {
		player.moveRight();
	}

	public void moveDown() {
		player.moveDown();
	}

	public void moveLeft() {
		player.moveLeft();
	}

	char[][] getView() {
		int width = dungeon.getWidth();
		int height = dungeon.getHeight();

		char[][] view = new char[height][width]; // y,x
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				view[i][j] = ' ';
			}
		}

		for (Entity e : dungeon.getEntities()) {
			if (!e.getVisibility()) {
				continue;
			}

			view[e.getY()][e.getX()] = entityToChar(e);
		}

		view[player.getY()][player.getX()] = entityToChar(player);

		return view;
	}

}
