package lucene;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

public class TesteMain {

	
	public static void main(String[] args) throws Throwable {
		JFileChooser chooserDiretorio = new JFileChooser();
		chooserDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		chooserDiretorio.showOpenDialog(null);
		String pasta = chooserDiretorio.getSelectedFile().getAbsolutePath();
		
		Path path = Paths.get(pasta);
		String diretorioDosIndices = System.getProperty("user.home")+"/index-teste/indices";
		
		Analisador analisador=new Analisador(diretorioDosIndices, path.toString());
		
		long inicio = System.currentTimeMillis();
		try {
			Files.walkFileTree(path, analisador);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			analisador.finalize();
		}
		long fim = System.currentTimeMillis();
		System.out.println("tempo para indexar: " + ((fim - inicio) / 1000) + "s");
	}
	
}
