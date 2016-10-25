package lucene;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TesteMain {

	
	public static void main(String[] args) {
//		Path path = Paths.get(System.getProperty("user.home") + "/bkp-portal-cmrj");
		Path path = Paths.get(System.getProperty("user.home") + "/index-teste/pastaParaIndexar");
		String diretorioDosIndices = System.getProperty("user.home")+"/index-teste/indices";
		long inicio = System.currentTimeMillis();
		try {
			Files.walkFileTree(path, new Analisador(diretorioDosIndices));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long fim = System.currentTimeMillis();
		System.out.println("tempo para indexar: " + ((fim - inicio) / 1000) + "s");
	}
	
}
