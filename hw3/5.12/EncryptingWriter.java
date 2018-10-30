import java.io.IOException;
import java.io.Writer;

public class EncryptingWriter extends Writer {
	private Writer writer;
	
	public EncryptingWriter(Writer w) {
		writer = w;
	}
	
	@Override
	public void close() throws IOException {
		writer.close();
	}

	@Override
	public void flush() throws IOException {
		writer.flush();
	}
	
	@Override
	public void write(char[] arg0) throws IOException {
		encrypt(arg0);
		writer.write(arg0);
	}

	@Override
	public void write(char[] arg0, int arg1, int arg2) throws IOException {
		encrypt(arg0);
		writer.write(arg0);
	}
	
	/**
	 * Encrypts
	 * @param data The data to encrypt
	 */
	private void encrypt(char[] data) {
		final int OFFSET = 3;
		final int ALPHABET_LENGTH = 26;
		int index = 0;
		for (char c : data) {
			if (Character.isLetter(c)) {
				c = (char)((int)c + OFFSET);
				if (!Character.isLetter(c)) c = (char)((int)c - ALPHABET_LENGTH);
			}
			data[index] = c;
			index++;
		}
	}
}
