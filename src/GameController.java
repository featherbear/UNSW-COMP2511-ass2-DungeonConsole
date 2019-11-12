import java.io.FileNotFoundException;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Door;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Exit;
import unsw.dungeon.entity.InvincibilityPotion;
import unsw.dungeon.entity.Key;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Switch;
import unsw.dungeon.entity.Sword;
import unsw.dungeon.entity.Treasure;
import unsw.dungeon.entity.Wall;
import unsw.dungeon.entity.meta.Entity;
import unsw.dungeon.entity.meta.EntityLevel;

public class GameController {
	private static final char backgroundTile = ' ';

	private DungeonLoader loader;
	private Dungeon dungeon;
	private Player player;

	private boolean hasWon;
	private boolean hasLost;

	public GameController(String mapFile) throws FileNotFoundException {
		loader = new DungeonLoader(mapFile);
		this.hasWon = false;
		this.hasLost = false;
	}

	private char entityToChar(Entity e) {
		if (e instanceof Wall) {
			return '\u2588';
		} else if (e instanceof Portal) {
			return '\u25EF';
		} else if (e instanceof Player) {
			return '\u00B7';
		} else if (e instanceof Sword) {
			return '\u2694';
		} else if (e instanceof Enemy) {
			return 'X';
		} else if (e instanceof Treasure) {
			return '$';
		} else if (e instanceof InvincibilityPotion) {
			return 'P';
		} else if (e instanceof Switch) {
			return 'S';
		} else if (e instanceof Boulder) {
			return 'B';
		} else if (e instanceof Key) {
			return 'K';
		} else if (e instanceof Door) {
			if (!((Door) e).getOpen()) {
				return '\u29BC';
			}
		} else if (e instanceof Exit) {
			return '\u00d8';
		}
		return backgroundTile;
	}

	public void initialise() {
		dungeon = loader.load();
		player = dungeon.getPlayer();

		dungeon.finishEvent.register(() -> {
			this.hasWon = true;
		});
		dungeon.playerDeadEvent.register(() -> {
			this.hasLost = true;
		});

		this.hasWon = false;
		this.hasLost = false;
	}

	public boolean hasWon() {
		return this.hasWon;
	}

	public boolean hasLost() {
		return this.hasLost;
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
				view[i][j] = backgroundTile;
			}
		}

		for (EntityLevel entityLevel : EntityLevel.values()) {
			for (Entity e : Entity.filter(dungeon.getEntities(), entityLevel)) {
				if (view[e.getY()][e.getX()] != backgroundTile) {
					continue;
				}

				if (!e.getVisibility()) {
					continue;
				}

				view[e.getY()][e.getX()] = entityToChar(e);
			}
		}

		return view;
	}

}
