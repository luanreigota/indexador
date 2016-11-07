package view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import lucene.ExecutaIndexacao;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class Indexar extends JDialog {

	private JList list = new JList();
	private JTextField textPastaParaIndexar;
	private JTextField textPastaDosIndices;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Indexar dialog = new Indexar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Indexar() {
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 800, 500);
		// {
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel selecExtencao = new JPanel();
		tabbedPane.addTab("Selecionar Extenção", null, selecExtencao, null);

		DefaultListModel model = new DefaultListModel();
		model.addElement(".php");
		model.addElement(".txt");
		model.addElement(".docx");
		model.addElement(".doc");
		model.addElement(".asp");
		model.addElement(".java");
		selecExtencao.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("284px"),
				ColumnSpec.decode("255px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("30px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
				JLabel titleSelectExtencao = DefaultComponentFactory.getInstance()
						.createTitle("Selecione as exten\u00E7\u00F5es que deseja indexar");
				GridBagConstraints gbc_titleSelectExtencao = new GridBagConstraints();
				gbc_titleSelectExtencao.insets = new Insets(0, 0, 5, 5);
				gbc_titleSelectExtencao.anchor = GridBagConstraints.WEST;
				gbc_titleSelectExtencao.gridx = 2;
				gbc_titleSelectExtencao.gridy = 0;
				selecExtencao.add(titleSelectExtencao, "2, 4, left, top");
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.insets = new Insets(0, 0, 0, 5);
				gbc_list.gridx = 3;
				gbc_list.gridy = 1;
				selecExtencao.add(list, "2, 8, left, center");
				
						list.setModel(model);
						list.setFixedCellWidth(100);
		tabbedPane.setEnabledAt(0, false);

		JPanel selecPastaParaIndexar = new JPanel();
		tabbedPane.addTab("Selecionar pasta para Indexar", null, selecPastaParaIndexar, null);
		selecPastaParaIndexar.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(139dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(205dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(19dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblSelecioneAPasta = new JLabel("Selecione a pasta que deseja indexar");
		selecPastaParaIndexar.add(lblSelecioneAPasta, "4, 6");
		
		textPastaParaIndexar = new JTextField();
		textPastaParaIndexar.setText(System.getProperty("user.home"));
		selecPastaParaIndexar.add(textPastaParaIndexar, "4, 14, left, default");
		textPastaParaIndexar.setColumns(40);
		
		JButton btnSelecPasta = new JButton("");
		btnSelecPasta.setIcon(new ImageIcon(Indexar.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		btnSelecPasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooserDiretorio = new JFileChooser();
				chooserDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooserDiretorio.showOpenDialog(tabbedPane);
				String pasta = chooserDiretorio.getSelectedFile().getAbsolutePath();
				
				textPastaParaIndexar.setText(pasta);
			}
		});
		selecPastaParaIndexar.add(btnSelecPasta, "6, 14");
		tabbedPane.setEnabledAt(1, false);

		JPanel selecPastaDosIndices = new JPanel();
		tabbedPane.addTab("Selecionar pasta dos indices", null, selecPastaDosIndices, null);
		selecPastaDosIndices.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("210px"),
				ColumnSpec.decode("320px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("49px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("29px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblSelecioneAPasta_1 = new JLabel("Selecione a pasta onde ficar\u00E3o os indices");
		selecPastaDosIndices.add(lblSelecioneAPasta_1, "2, 6");
		
		textPastaDosIndices = new JTextField();
		textPastaDosIndices.setText(System.getProperty("user.home")+"\\indice");
		textPastaDosIndices.setColumns(40);
		selecPastaDosIndices.add(textPastaDosIndices, "2, 14, left, center");
		
		JButton buttonSelecPastaIndice = new JButton("");
		buttonSelecPastaIndice.setIcon(new ImageIcon(Indexar.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		buttonSelecPastaIndice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooserDiretorio = new JFileChooser();
				chooserDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooserDiretorio.showOpenDialog(tabbedPane);
				String pasta = chooserDiretorio.getSelectedFile().getAbsolutePath();
				
				textPastaDosIndices.setText(pasta);
			}
		});
		selecPastaDosIndices.add(buttonSelecPastaIndice, "4, 14, left, top");
		tabbedPane.setEnabledAt(2, false);

		// }

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		final JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setEnabled(false);
		panel.add(btnAnterior);

		final JButton btnPrximo = new JButton("Pr\u00F3ximo");
		panel.add(btnPrximo);

		final JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setEnabled(false);
		btnConcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new ExecutaIndexacao(textPastaParaIndexar.getText(), textPastaDosIndices.getText());
					Indexar.this.dispose();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnConcluir);

		btnAnterior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				andaTab(-1, tabbedPane, btnPrximo, btnAnterior, btnConcluir);
			}
		});

		btnPrximo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				andaTab(1, tabbedPane, btnPrximo, btnAnterior, btnConcluir);
			}
		});

	}

	public void andaTab(int prox, JTabbedPane tabbedPane, JButton btnProx, JButton btnAnt, JButton btnConcluir) {

		int atual = tabbedPane.getSelectedIndex();

		tabbedPane.setSelectedIndex(prox + atual);

		
		
		if ((tabbedPane.getSelectedIndex() + 1) == tabbedPane.getTabCount()) {
			btnProx.setEnabled(false);
			btnConcluir.setEnabled(true);
			
		} else {
			btnProx.setEnabled(true);
			btnConcluir.setEnabled(false);
		}

		if (tabbedPane.getSelectedIndex() == 0) {
			btnAnt.setEnabled(false);
		} else {
			btnAnt.setEnabled(true);
		}

	}

}
