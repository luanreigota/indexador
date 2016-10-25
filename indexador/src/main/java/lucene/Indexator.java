package lucene;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.tika.Tika;

public class Indexator {

	private String diretorioDosIndices;

	private IndexWriter writer;

	private Tika tika;

	public Indexator(String diretorioDosIndices) {
		try {
			this.diretorioDosIndices = diretorioDosIndices;
			File diretorio = new File(diretorioDosIndices);
			apagarIndices(diretorio);
			Directory doc = new SimpleFSDirectory(diretorio);
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
			writer = new IndexWriter(doc, config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void indexarArquivo(File arquivo) {
		StringBuffer msg = new StringBuffer();
		msg.append("Indexando o arquivo ");
		msg.append(arquivo.getAbsoluteFile());
		msg.append(", ");
		msg.append(arquivo.length() / 1000);
		msg.append("kb");
		System.out.println(msg);
		try {
			SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
			
			System.out.println(arquivo.getAbsolutePath());
			
			Document document = new Document();
			document.add(new TextField("UltimaModificacao", formatador.format(arquivo.lastModified()), Store.YES));
			document.add(new TextField("caminho", arquivo.getAbsolutePath(), Store.YES));
			document.add(new TextField("texto", getTika().parseToString(arquivo), Store.YES));
			try {
				writer.addDocument(document);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void finish() throws IOException {
		writer.commit();
		writer.close();
	}

	public void apagarIndices(File diretorio) {
		if (diretorio.exists()) {
			File arquivos[] = diretorio.listFiles();
			for (File arquivo : arquivos) {
				arquivo.delete();
			}
		}

	}

	public Tika getTika() {
		if (tika == null) {
			tika = new Tika();
		}
		return tika;
	}

}
