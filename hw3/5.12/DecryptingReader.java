import java.io.IOException;
import java.io.Reader;

public class DecryptingReader extends Reader {
	private Reader reader;
	
	/**
	 * Creates a new DecryptingReader
	 * @param r
	 */
	public DecryptingReader(Reader r) {
		reader = r;
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
	
	@Override
	public int read(char[] cbuf) throws IOException {
		int ret = reader.read(cbuf);
		decrypt(cbuf);
		return ret;
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int ret = reader.read(cbuf, off, len);
		decrypt(cbuf);
		return ret;
	}
	
	/**
	 * Decrypts
	 * @param data The data to decrypt
	 */
	private void decrypt(char[] data) {
		final int OFFSET = 3;
		final int ALPHABET_LENGTH = 26;
		int index = 0;
		for (char c : data) {
			if (Character.isLetter(c)) {
				c = (char)((int)c - OFFSET);
				if (!Character.isLetter(c)) c = (char)((int)c + ALPHABET_LENGTH);
			}
			data[index] = c;
			index++;
		}
	}
}
