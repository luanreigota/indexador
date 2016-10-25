package lucene;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.tika.Tika;

public class Indexador {

	// private String diretorioDosIndices = System.getProperty("user.home") +
	// "/indices-lucene-portal";
	private String diretorioDosIndices = System.getProperty("user.home") + "/index-teste/indices";

	// private String diretorioParaIndexar = System.getProperty("user.home") +
	// "/bkp-portal-cmrj";
	private String diretorioParaIndexar = System.getProperty("user.home") + "/index-teste/pastaParaIndexar";

	private IndexWriter writer;

	private Tika tika;

	public static void main(String[] args) {
		Indexador indexador = new Indexador();
		indexador.indexarArquivosDoDiretorio();
	}

	public void indexarArquivosDoDiretorio() {
		try {
			File diretorio = new File(diretorioDosIndices);
			apagarIndices(diretorio);
			Directory d = new SimpleFSDirectory(diretorio);

			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);

			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);

			writer = new IndexWriter(d, config);

			long inicio = System.currentTimeMillis();
			indexarArquivosDoDiretorio(new File(diretorioParaIndexar));
			writer.commit();
			writer.close();
			long fim = System.currentTimeMillis();
			System.out.println("tempo para indexar: " + ((fim - inicio) / 1000) + "s");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void apagarIndices(File diretori) {
		if (diretori.exists()) {
			File arquivos[] = diretori.listFiles();
			for (File arquivo : arquivos) {
				arquivo.delete();
			}
		}

	}

	public void indexarArquivosDoDiretorio(File raiz) {
		FilenameFilter filtro = new FilenameFilter() {
			public boolean accept(File arquivo, String nome) {
				 if (nome.toLowerCase().endsWith(".pdf") ||
				 nome.toLowerCase().endsWith(".odt")
				 || nome.toLowerCase().endsWith(".doc") ||
				 nome.toLowerCase().endsWith(".docx")
				 || nome.toLowerCase().endsWith(".ppt") ||
				 nome.toLowerCase().endsWith(".pptx")
				 || nome.toLowerCase().endsWith(".xls") ||
				 nome.toLowerCase().endsWith(".txt")
				 || nome.toLowerCase().endsWith(".rtf")){
//				if (nome.toLowerCase().endsWith(".php")) {
					return true;
				}
				return false;
			}
		};
		 for (File arquivo : raiz.listFiles(filtro)) {
		
			if (arquivo.isFile()) {
				StringBuffer msg = new StringBuffer();
				msg.append("Indexando o arquivo ");
				msg.append(arquivo.getAbsoluteFile());
				msg.append(", ");
				msg.append(arquivo.length() / 1000);
				msg.append("kb");
				System.out.println(msg);
				try {
					// {9}
					String textoExtraido = getTika().parseToString(arquivo);
					 System.out.println(textoExtraido);
					indexaArquivo(arquivo, textoExtraido);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				indexarArquivosDoDiretorio(arquivo);
			}
		}
	}

	private void indexaArquivo(File arquivo, String textoExtraido) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
		String ultimaModificacao = formatador.format(arquivo.lastModified());
		Document documento = new Document();
		documento.add(new TextField("UltimaModificacao", ultimaModificacao, Store.YES));
		documento.add(new TextField("Caminho", arquivo.getAbsolutePath(), Store.YES));
		documento.add(new TextField("Texto", textoExtraido, Store.YES));
		try {
			getWriter().addDocument(documento);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Tika getTika() {
		if (tika == null) {
			tika = new Tika();
		}
		return tika;
	}

	public IndexWriter getWriter() {
		return writer;
	}

}
