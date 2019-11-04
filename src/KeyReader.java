
import java.io.IOException;

import biz.source_code.utils.RawConsoleInput;
import javafx.scene.input.KeyCode;

public class KeyReader {

	public KeyCode read() throws IOException {
		int prevCh = -1;
		int ch;

		while (true) {
			ch = RawConsoleInput.read(true);

			switch (prevCh) {

			case 27:
				break;
			case 91:
				switch (ch) {
				case 65:
					return KeyCode.UP;
				case 66:
					return KeyCode.DOWN;
				case 67:
					return KeyCode.RIGHT;
				case 68:
					return KeyCode.LEFT;
				}
				break;

			default:
				switch (ch) {
				case 3:
				case 28:
					return KeyCode.CANCEL;
				case 27:
				case 91:
					break;
				default:
					switch (ch) {
					case 119:
					case 87:
						return KeyCode.W;

					case 115:
					case 83:
						return KeyCode.S;

					case 97:
					case 65:
						return KeyCode.A;

					case 100:
					case 68:
						return KeyCode.D;

					default:
						System.out.println(ch);
						return KeyCode.UNDEFINED;
//					return KeyCode.getKeyCode(Character.toString((char) ch).toUpperCase());
					}
				}

				break;
			}

			prevCh = ch;
		}
	}
}
