
import java.io.Console;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.input.KeyCode;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Wall;
import unsw.dungeon.entity.meta.Entity;

/**
 * @author z5206677
 *
 */

// m e m e s

public class DungeonConsole {
	public static void main(String[] args) throws IOException {

//		String mapName = getMapNameLoop(args.length == 0 ? "" : args[0].strip());
//		System.out.println(String.format("Selected \"%s\"", mapName));
		DungeonLoader loader = new DungeonLoader("advanced.json");
		Dungeon dungeon = loader.load();

		int width = dungeon.getWidth();
		int height = dungeon.getHeight();

		Console c = System.console();

		char[][] z = new char[height][width]; // y,x
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				z[i][j] = ' ';
			}
		}

		for (Entity e : dungeon.getEntities()) {
			char symbol = '?';
			{
				if (e instanceof Wall) {
					symbol = '\u25A0';
				} else if (e instanceof Portal) {
					symbol = '\u25EF';
				} else if (e instanceof Player) {
					symbol = '\u00B7';
				}
			}

			z[e.getY()][e.getX()] = symbol;
		}

		for (int i = 0; i < height; i++) {
			System.out.println(z[i]);
		}
		KeyReader keyInput = new KeyReader();
		while (true) {
			KeyCode code = keyInput.read();
			if (code == KeyCode.CANCEL) {
				System.exit(0);
			} else {
				System.out.println(code);
			}
		}
	}

	public static String getMapNameLoop(String input) {
		List<String> levels = getLevels();

		Scanner sc = new Scanner(System.in);

		while (!levels.contains(input)) {
			if (!input.isEmpty()) {
				System.out.println(String.format("\"%s\" is an invalid map!", input));
			}

			System.out.println("======== MAPS ========");
			levels.forEach(f -> System.out.println("- " + f));
			System.out.println("======================");
			System.out.print("Enter a map name\n> ");
			input = sc.nextLine().strip();
		}

		sc.close();
		return input;
	}

	public static Dungeon createDungeon(File level) {
		System.out.println(level);
//		new DungeonLoader()
		return null;
	}

	public static List<String> getLevels() {
		// your directory
		File f = new File("dungeons");
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".json");
			}
		});

		ArrayList<String> levelFiles = new ArrayList<String>();
		for (File file : matchingFiles) {
			levelFiles.add(file.getName());
		}

		return levelFiles;

	}

//	public static String promptLevel() {
//
//	}
}
