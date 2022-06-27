package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Events;

public class PanelSimulation extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> listStates;
	private String listStatesBox[] = { "Lista de Procesos Listos",
			"Lista de Procesos Despachados","Lista de Procesos en Ejecucion","Lista de Procesos Bloqueados",
			"Lista de Procesos Despertados","Lista de Procesos Con tiempo expirado", "Lista De Procesos Terminados",
			"Lista de Procesos a suspension","Lista de Procesos suspendidos","Lista de Procesos reanudados",
			"Lista de Procesos Destruidos","Lista de Procesos Destruidos a ejecucion","Lista de Procesos Suspendidos a destruccion",
			"Lista de Procesos Suspendidos a listos","Lista de Procesos despertados","Lista de Procesos bloqueados a destruidos",
			"Lista de Procesos bloqueados a suspendidos","Historial De Comunicaciones entre procesos"};
	
	private JLabel message1;
	private JButton accept;
	public PanelSimulation(ActionListener listener) {
		
		
		setLayout(new GridLayout(4,1));
		listStates = new JComboBox<>(listStatesBox);
		PanelAddProcess.mixFont(listStates);
		message1 = new JLabel("Que lista De Procesos Desea Ver?");;
		PanelAddProcess.mixFont(message1);
		this.add(message1);
		
		listStates.setSelectedIndex(0);

		this.add(listStates);
		accept = new JButton("Aceptar");
		PanelAddProcess.mixFont(accept);
		this.add(accept);
		accept.addActionListener(listener);
		accept.setActionCommand(Events.SEARCH_LIST_PROCESS.name());
		this.add(new PanelAddProcess(listener).getCancel(),BorderLayout.SOUTH);

	}
	public JComboBox<String> getListStates() {
		return listStates;
	}
	public String[] getListStatesBox() {
		return listStatesBox;
	}
	public JButton getAccept() {
		return accept;
	} 
	
	
	
}
