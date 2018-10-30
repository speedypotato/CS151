import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DecoratorTester {
	public static final String text = "The Quick Brown Fox Jumped Over The Lazy Dog.\n";
	
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("temp.txt");
		EncryptingWriter ew = new EncryptingWriter(fw);
		ew.write(text);
		ew.close();
		
		FileReader fr = new FileReader("temp.txt");
		BufferedReader br = new BufferedReader(fr);
		String read = br.readLine();
		System.out.println(read);
		
		char[] buffer = read.toCharArray();
		DecryptingReader dr = new DecryptingReader(fr);
		dr.read(buffer);
		System.out.println(buffer);
		dr.close();
	}
}
