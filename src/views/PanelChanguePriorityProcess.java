package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Events;
import models.MyProcess;

public class PanelChanguePriorityProcess extends JPanel {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JLabel messageRequest;
	
	
	private JPanel panel;
	private JLabel message;
	private JButton accept;
	private JTextField updatePriority;
	private JLabel message2;
	JComboBox<String> boxListProccess;
	
	public PanelChanguePriorityProcess(ArrayList<MyProcess> processes,ActionListener listener) {
		boxListProccess = new JComboBox<String>();
		PanelAddProcess.mixFont(boxListProccess);
		
		for (int i = 0; i < processes.size(); i++) {
			boxListProccess.addItem("Proceso: " + processes.get(i).getName());
		}
		boxListProccess.setSelectedIndex(0);
		
		message = new JLabel("Seleccione el proceso al que desea cambiarle la prioridad:");
		PanelAddProcess.mixFont(message);
		accept = new JButton("Cambiar Prioridad del Proceso");
		accept.addActionListener(listener);
		accept.setActionCommand(Events.CONFIRM_CHANGUE_PRIORITY.name());
		PanelAddProcess.mixFont(accept);
		panel = new JPanel(new GridLayout(6, 1));
		
		message2 = new JLabel("Ingrese la nueva prioridad del proceso");
		PanelAddProcess.mixFont(message2);
		updatePriority = new JTextField();
		PanelAddProcess.mixFont(updatePriority);
		
		
		panel.add(message);
		panel.add(boxListProccess);
		panel.add(message2);
		panel.add(updatePriority);
		panel.add(accept);
		panel.add(new PanelAddProcess(listener).getCancel());
		
		this.add(panel);
	}

	public JLabel getMessageRequest() {
		return messageRequest;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getMessage() {
		return message;
	}

	public JButton getAccept() {
		return accept;
	}

	public JTextField getUpdatePriority() {
		return updatePriority;
	}

	public JLabel getMessage2() {
		return message2;
	}

	public JComboBox<String> getBoxListProccess() {
		return boxListProccess;
	}
	
	
	
}
