
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
					System.out.println(ch);
					return KeyCode.A;
//					return KeyCode.getKeyCode(Character.toString((char) ch).toUpperCase());
				}

				break;
			}

			prevCh = ch;
		}
	}
}
