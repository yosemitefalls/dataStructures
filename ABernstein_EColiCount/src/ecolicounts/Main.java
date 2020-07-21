package ecolicounts;

import static java.lang.System.*;
import static org.apache.commons.io.FileUtils.*;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner ecoliReader = new Scanner(in);
		File ecoliFile = new File(ecoliReader.nextLine());
		String text = readFileToString(ecoliFile);
		ecoliReader.close();
		int a = 0;
		int c = 0;
		int g = 0;
		int t = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == 'A') {
				a++;
			} else if (text.charAt(i) == 'C') {
				c++;
			} else if (text.charAt(i) == 'G') {
				g++;
			} else if (text.charAt(i) == 'T') {
				t++;

			}
		}
		out.printf("#A = %d\n#C = %d\n#G = %d\n#T = %d\n", a, c, g, t);
	}

}
