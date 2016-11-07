package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import lucene.ExecutaIndexacao;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.Sizes;
import javax.swing.JButton;

public class TelaPrincipal {

	private JFrame frame;
	private JTextField diretorioDosIndices;

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
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem indexar = new JMenuItem("Indexar");
		indexar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		indexar.setSelectedIcon(new ImageIcon(TelaPrincipal.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		
		indexar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					Indexar indexar = new Indexar();
					indexar.setVisible(true);
					
					
					
//					JFileChooser chooserDiretorio = new JFileChooser();
//					chooserDiretorio.setDialogTitle("Selecione a pasta que deseja indexar.");
//					chooserDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
//					chooserDiretorio.showOpenDialog(null);
//					String pasta = chooserDiretorio.getSelectedFile().getAbsolutePath();
//					
//					new ExecutaIndexacao(pasta, pasta);
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menuBar.add(indexar);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.LEFT, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("163dlu", true), Sizes.constant("200dlu", true)), 0),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblIndices = new JLabel("Indices:");
		frame.getContentPane().add(lblIndices, "2, 2, right, default");
		
		diretorioDosIndices = new JTextField();
		diretorioDosIndices.setText(System.getProperty("user.home")+"\\indice");
		frame.getContentPane().add(diretorioDosIndices, "4, 2, left, default");
		diretorioDosIndices.setColumns(40);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		frame.getContentPane().add(button, "6, 2");
	}
}
