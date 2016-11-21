package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lucene.AbreDocumento;
import lucene.Buscador;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;

import org.apache.lucene.queryparser.classic.ParseException;

import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;

public class TelaPrincipal {

	private JFrame frame;
	private JTextField pathIndice;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		final JPanel topo = new JPanel();
		frame.getContentPane().add(topo, BorderLayout.NORTH);
		topo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("153px"),
				ColumnSpec.decode("max(84dlu;default)"),
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("21px"),
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JMenuBar menuBar = new JMenuBar();
		topo.add(menuBar, "1, 1, fill, top");
		
		JMenuItem indexar = new JMenuItem("Indexar");
		indexar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Indexar viewIndexar = new Indexar();
				viewIndexar.setVisible(true);
			}
		});
		menuBar.add(indexar);
		
		JLabel lblIndice = new JLabel("Indice:");
		topo.add(lblIndice, "1, 2, right, default");
		
		pathIndice = new JTextField();
		String separator = System.getProperty("file.separator");
		pathIndice.setText(System.getProperty("user.home")+separator+"indice");
		topo.add(pathIndice, "2, 2, fill, default");
		pathIndice.setColumns(10);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserDiretorio = new JFileChooser();
				chooserDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooserDiretorio.showOpenDialog(topo);
				String pasta = chooserDiretorio.getSelectedFile().getAbsolutePath();
				
				pathIndice.setText(pasta);
			}
		});
		button.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		topo.add(button, "3, 2");
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("216px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("451px:grow"),}));
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, "3, 3, fill, fill");
		
		JLabel lblBuscar = new JLabel("Buscar:");
		splitPane.setLeftComponent(lblBuscar);
		
		textField = new JTextField();
		splitPane.setRightComponent(textField);
		textField.setColumns(10);

		final JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(20);
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setViewportView(list);
		list.addMouseListener(new AbreDocumento());
		panel.add(jScrollPane, "3, 7, fill, top");
		
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(textField.getText()!=""){
					List<String> paths = null;
					try {
						paths = new Buscador().buscaComParser(textField.getText(), pathIndice.getText());
						Collections.sort(paths);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Informe um valor para busca", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}catch (IOException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Esta pasta não contém Indices!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
					}
					DefaultListModel<String> model = new DefaultListModel<>();
					for (String string : paths) {
						model.addElement(string);
					}
					list.setModel(model);
				}
				
			}
		});
		
		panel.add(btnOk, "5, 3, center, top");


		
	}
}
