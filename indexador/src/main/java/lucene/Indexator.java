package lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.tika.Tika;

public class Indexator {

	private IndexWriter writer;

	private Tika tika;

	public Indexator(String diretorioDosIndices) {
		try {
			Directory doc = FSDirectory.open(Paths.get(diretorioDosIndices));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

			// cria um novo index no diretório, removendo qualquer indice
			// criado
			// anteriormente
			iwc.setOpenMode(OpenMode.CREATE);

			writer = new IndexWriter(doc, iwc);

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
		try (InputStream stream = Files.newInputStream(arquivo.toPath())) {
			SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");

			
			System.out.println(arquivo.getAbsolutePath());

			Document document = new Document();
			document.add(new TextField("UltimaModificacao", formatador.format(arquivo.lastModified()), Store.YES));
			document.add(new TextField("caminho", arquivo.getAbsolutePath(), Store.YES));
			document.add(new TextField("nome", arquivo.getName(), Store.YES));
//			document.add(new TextField("texto", getTika().parseToString(arquivo), Store.YES));
			 document.add(new TextField("texto",
			 new BufferedReader(new InputStreamReader(stream,
			 StandardCharsets.ISO_8859_1))));
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

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		writer.close();
		System.out.println("fim!");
	}

	public Tika getTika() {
		if (tika == null) {
			tika = new Tika();
		}
		return tika;
	}

}
