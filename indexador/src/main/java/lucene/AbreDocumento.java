package lucene;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class AbreDocumento implements MouseListener {

	@SuppressWarnings("rawtypes")
	@Override
	public void mouseClicked(MouseEvent e){
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			JList list = (JList) e.getSource();
			int index = list.getSelectedIndex();
			System.out.println(list.getModel().getElementAt(index));
			try {
				Desktop.getDesktop().open(new File(list.getModel().getElementAt(index).toString()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch (IllegalArgumentException e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
