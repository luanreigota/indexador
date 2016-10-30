package lucene;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class Analisador extends SimpleFileVisitor<Path> {

	Indexator indexator;
	String pastaInicio;

	public Analisador(String diretorioDosIndices, String pastaInicio) {
		this.pastaInicio = pastaInicio;
		System.out.println("instanciando o indexador");
		indexator = new Indexator(diretorioDosIndices);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		if (file.toFile().getName().toLowerCase().endsWith(".php")) {
			indexator.indexarArquivo(file.toFile());
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("preVisitDirectory(" + dir + ")");
		return FileVisitResult.CONTINUE;
	}

	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		
		indexator.finish();
		System.out.println("fim!");

	}

}
