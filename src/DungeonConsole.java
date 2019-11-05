
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.input.KeyCode;

/**
 * @author z5206677
 *
 */

// m e m e s

public class DungeonConsole {

	public static void main(String[] args) throws IOException {
		System.out.println("| Dungeon Console |");
		System.out.println("// The COMP2511 assignment but in a text-based version that no one asked for...\n");

		// Get maps
		String mapName = getMapNameLoop(args.length == 0 ? "" : args[0].strip());
		System.out.println(String.format("Selected \"%s\"", mapName));

		// Load game controller
		GameController controller = new GameController(mapName);

		controller.initialise();
		redraw(controller);

		KeyReader keyInput = new KeyReader();
		// Listen to keyboard events

		while (true) {
			KeyCode key = keyInput.read();
			switch (key) {

			case CANCEL:
			case DELETE:
				System.exit(0);
				break;

			case W:
			case UP:
				controller.moveUp();
				break;

			case S:
			case DOWN:
				controller.moveDown();
				break;

			case A:
			case LEFT:
				controller.moveLeft();
				break;

			case D:
			case RIGHT:
				controller.moveRight();
				break;

			case R:
				controller.initialise();

			default:
				System.out.println(key);

			}

			redraw(controller);
		}
	}

	private static void redraw(GameController controller) {
		// Clear the screen first
		clearScreen();

		// Print out the entities - We can print an array of characters as a String
		char[][] view = controller.getView();
		for (int i = 0; i < view.length; i++) {
			System.out.println(view[i]);
		}

		for (String line : new String[] { "", "=== CONTROLS ===", "W / UP - Move Up", "S / DOWN - Move Down",
				"A / LEFT - Move Left", "D / RIGHT - Move Right", "R - Restart", "DELETE - Exit" }) {
			System.out.println(line);
		}

	}

	private static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static String getMapNameLoop(String input) {
		List<String> levels = getLevels();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (!levels.contains(input)) {
			if (!input.isEmpty()) {
				System.out.println(String.format("\"%s\" is an invalid map!", input));
			}

			System.out.println("======== MAPS ========");
			levels.forEach(f -> System.out.println("- " + f));
			System.out.println("======================");
			System.out.println("\nEnter a map name");
			System.out.print("> ");
			input = sc.nextLine().strip();
		}

		/*
		 * Don't close the Scanner, else we get a java.io.IOException: Stream closed //
		 * // sc.close();
		 */

		return input;
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
}
