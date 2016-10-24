package lucene;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TesteMain {

	
	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home") + "/bkp-portal-cmrj");
		try {
			Files.walkFileTree(path, new Teste());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
