package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Events;
import models.MyProcess;

public class PanelComunicateProcess extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel message;
	private JLabel message2;
	private JButton accept;
	JComboBox<String> boxTransmition;
	JComboBox<String> boxReception;
	public PanelComunicateProcess(ArrayList<MyProcess> processes,ActionListener listener) {
		
		
		ArrayList<MyProcess> aux = new ArrayList<MyProcess>(processes);
		boxTransmition = new JComboBox<String>();
			
		for (int i = 0; i < processes.size(); i++) {
			boxTransmition.addItem("Proceso: " + processes.get(i).getName());
		}
		
//		System.out.println("trst");
		boxTransmition.setSelectedIndex(0);
		PanelAddProcess.mixFont(boxTransmition);
		 boxReception = new JComboBox<String>();
		 if (boxTransmition.getSelectedIndex()==0) {
			 for (int i = 1; i < aux.size(); i++) {
					boxReception.addItem("Proceso: " + aux.get(i).getName());
				}
		}
		
		 boxTransmition.addActionListener(event -> {
	            @SuppressWarnings("rawtypes")
				JComboBox comboBox1 = (JComboBox) event.getSource();
	            Object selected = comboBox1.getSelectedItem();
	            String command = event.getActionCommand();
	            if ("comboBoxEdited".equals(command)) {
	            } else if ("comboBoxChanged".equals(command)) {
	            	for (int i = 0; i < processes.size(); i++) {
	            		String processName = String.valueOf(selected);
	            		String evaluate = processName.substring(processName.lastIndexOf(": ") +1).replaceAll("\\s+","");
							if (processes.get(i).getName().equalsIgnoreCase(evaluate) ) {
								MyProcess pro = null;
								for (int j = 0; j < aux.size(); j++) {
									if (processes.get(i).getName().equalsIgnoreCase(aux.get(j).getName())) {
										pro = aux.get(j);
										aux.remove(j);
									}
								}
								boxReception.removeAllItems();
								ArrayList<MyProcess> aux2 = new ArrayList<MyProcess>(aux);
								
								for (int i2 = 0; i2 < aux2.size(); i2++) {
									boxReception.addItem("Proceso: " + aux2.get(i2).getName());
								}
								if (pro!=null) {
									aux.add(pro);
								}
							}
					}
	            }
	        });
		PanelAddProcess.mixFont(boxReception);
		message = new JLabel("Seleccione un proceso para realizar la comunicacion?");
		PanelAddProcess.mixFont(message);
		message2  = new JLabel("Con que  proceso desea hacer la comunicacion?");
		PanelAddProcess.mixFont(message2);
		accept = new JButton("Agregar Comunicacion");
		accept.addActionListener(listener);
		accept.setActionCommand(Events.ACCEPT_COMUNICATION.name());
		PanelAddProcess.mixFont(accept);
		panel = new JPanel(new GridLayout(6, 1));
		panel.add(message);
		panel.add(boxTransmition);
		panel.add(message2);
		panel.add(boxReception);
		panel.add(accept);
		panel.add(new PanelAddProcess(listener).getCancel());
		
		this.add(panel);
	}
	public JPanel getPanel() {
		return panel;
	}
	public JLabel getMessage() {
		return message;
	}
	public JLabel getMessage2() {
		return message2;
	}
	public JButton getAccept() {
		return accept;
	}
	public JComboBox<String> getBoxTransmition() {
		return boxTransmition;
	}
	public JComboBox<String> getBoxReception() {
		return boxReception;
	}
	
	
	
} 
