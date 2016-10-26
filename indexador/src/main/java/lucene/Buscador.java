package lucene;

import java.io.File;

import javax.swing.JOptionPane;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class Buscador {
	private String diretorioDoIndice = System.getProperty("user.home") + "/index-teste/indices";

	public void buscaComParser(String parametro) {
		try {
			Directory diretorio = new SimpleFSDirectory(new File(diretorioDoIndice));
			// {1}
			IndexReader leitor = DirectoryReader.open(diretorio);
			// {2}
			IndexSearcher buscador = new IndexSearcher(leitor);
			Analyzer analisador = new StandardAnalyzer(Version.LUCENE_47);
			// {3}
			QueryParser parser = new QueryParser(Version.LUCENE_47, "texto", analisador);
			
			Query consulta = parser.parse(parametro);
			long inicio = System.currentTimeMillis();
			// {4}
			TopDocs resultado = buscador.search(consulta, 10000);
			long fim = System.currentTimeMillis();
			int totalDeOcorrencias = resultado.totalHits;
			System.out.println("Total de documentos encontrados:" + totalDeOcorrencias);
			System.out.println("Tempo total para busca: " + (fim - inicio) + "ms");
			// {5}
			for (ScoreDoc sd : resultado.scoreDocs) {
				Document documento = buscador.doc(sd.doc);
//				System.out.println("Caminho:" + documento.get("caminho"));
				System.out.println(documento.get("caminho"));
//				System.out.println("Ultima modificacao:" + documento.get("UltimaModificacao"));
//				System.out.println("Score:" + sd.score);
//				System.out.println("--------");
			}
			leitor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Buscador b = new Buscador();
//		String parametro = "$conn_DP || "
//						+ "$conn_base_parlamentar ||"
//						+ "$conn_cmrj_web ||"
//						+ "$conn_comissao ||"
//						+ "$conn_contratacao ||"
//						+ "$conn_escala_eventos ||"
//						+ "$conn_noticias ||"
//						+ "$conn_programacao_tvcamara ||"
//						+ "$conn_setores_cmrj ||"
//						+ "$conn_tv_camara";
		String parametro = "from";
						
		b.buscaComParser(parametro);
	}
}