package wordcount;

import static java.lang.System.*;
import static org.apache.commons.io.FileUtils.*;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		String filename = (args.length > 0) ? args[0] : new Scanner(in).nextLine().trim();

		String text = readFileToString(new File(filename));

		int count = text.split("\\b[\\w|']+\\b").length;
		out.println("There are " + count + " words in " + filename);

	}

}
