package lucene;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
import org.apache.lucene.store.FSDirectory;

public class Buscador {
//	private String diretorioDoIndice = System.getProperty("user.home") + "/indice";

	public List<String> buscaComParser(String parametro, String diretorioDoIndice) {
		try {
			List<String> paths = new ArrayList<>();
			// {1}
			IndexReader leitor = DirectoryReader.open(FSDirectory.open(Paths.get(diretorioDoIndice)));
			// {2}
			IndexSearcher buscador = new IndexSearcher(leitor);
			Analyzer analisador = new StandardAnalyzer();
			// {3}
			QueryParser parser = new QueryParser("texto", analisador);
			
			
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
				paths.add(documento.get("caminho"));
//				System.out.println("Ultima modificacao:" + documento.get("UltimaModificacao"));
//				System.out.println("Score:" + sd.score);
//				System.out.println("--------");
			}
			leitor.close();
			return paths;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
//		String parametro = "\"cadastro no portal da CMRJ\"";
		String parametro = "inativo";
						
//		b.buscaComParser(parametro);
	}
}