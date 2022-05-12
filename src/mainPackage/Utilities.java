package mainPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utilities {
	public static String getFileString(String filePath) {
        String str;
		try {
			
			Path fileName = Path.of(filePath);
			str = Files.readString(fileName);
			return str;
			
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
