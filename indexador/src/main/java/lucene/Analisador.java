package lucene;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class Analisador extends SimpleFileVisitor<Path> {

	Indexator indexator;
	
	public Analisador(String diretorioDosIndices) {
		System.out.println("instanciando o indexador");
		indexator = new Indexator(diretorioDosIndices);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		indexator.indexarArquivo(file.toFile());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Pasta: "+dir);
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("fim");
		indexator.finish();
		return FileVisitResult.TERMINATE;
	}

}
