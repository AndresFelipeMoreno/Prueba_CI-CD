package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JViewport;

import controller.Events;



public class FrameView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private ButtonFrameView buttonAddProcess;
	private ButtonFrameView buttonEditProcess;
	private ButtonFrameView buttonDeleteProcess;
	private ButtonFrameView buttonUpdateProcess;
	private ButtonFrameView simulateProcess;
	private ButtonFrameView showProcess;
	private ButtonFrameView resetSimulation;
	private ButtonFrameView comunciateProcess;
	private ButtonFrameView changuePriorityProcess;
	private JPanel mainPanel; 
	private JPanel managerPanel; 
	private JDialogAlert alert;
	private JButton btnExit;
	
	public FrameView(ActionListener listener, String action) {
		setBackground(Color.WHITE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		initComponenets(listener, action);
		setVisible(true);
	}

	private void initComponenets(ActionListener listener,String action) {
		managerPanel = new JPanel(new BorderLayout());
		mainPanel =  new JPanel();
		mainPanel.setBackground(Color.white);
		GridBagLayout gbl =  new GridBagLayout();
		GridBagConstraints gbc = new  GridBagConstraints();
		mainPanel.setLayout(gbl);
		managerPanel.add(mainPanel,BorderLayout.CENTER);
		this.add(managerPanel);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		btnExit = new JButton("Salir");
		PanelAddProcess.mixFont(btnExit);
		btnExit.setActionCommand(action);
		btnExit.addActionListener(listener);
		btnExit.setFocusable(false);
		gbc.insets = new Insets(-300, 0, 50, 0);
		mainPanel.add(btnExit,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		title = new JLabel("Simulacion de procesos");
		title.setFont(new Font("Arial", Font.BOLD, 50));
		gbc.insets = new Insets(-50, 0, 50, -50);
		mainPanel.add(title,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		
		buttonAddProcess = new ButtonFrameView(listener, Events.ADD_PROCESS.name(), Message.MESSAGE_ADD_PROCESS);
		gbc.insets = new Insets(50, 0, 0, -50);
		mainPanel.add(buttonAddProcess,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.ipadx = 60;
		showProcess = new ButtonFrameView(listener, Events.SHOW_PROCESS_ADD.name(), Message.MESSAGE_SHOW_PROCESS_ADD);
		
		gbc.insets = new Insets(50, 80, 0, -40);
		mainPanel.add(showProcess,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.ipadx = 0;
		simulateProcess = new ButtonFrameView(listener, Events.SIMULATE_PROCESS.name(), Message.MESSAGE_SIMULATE_PROCESS);
		gbc.insets = new Insets(50, 80, 0, -40);
		mainPanel.add(simulateProcess,gbc);
		
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.ipadx = 50;
		comunciateProcess = new ButtonFrameView(listener, Events.COMUNICATE_PROCESS.name(), Message.MESSAGE_COMUNICATE_PROCESS);
		gbc.insets = new Insets(50, 80, 0, 0);
		mainPanel.add(comunciateProcess,gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.ipadx = 0;
		resetSimulation = new ButtonFrameView(listener, Events.RESET_SIMULATION.name(), Message.MESSAGE_RESET_SIMULATION);
		gbc.insets = new Insets(50, 80, 0, 0);
		mainPanel.add(resetSimulation,gbc);
		
		
//		
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.ipadx = 100;
		changuePriorityProcess = new ButtonFrameView(listener, Events.CHANGUE_PRIORITY_PROCESS.name(), Message.MESSAGE_CHANGUE_PRIORITY_PROCESS);
		gbc.insets = new Insets(50,-100, 0, 0);
		mainPanel.add(changuePriorityProcess,gbc);
//		
	}
	
	public void createAlert(String message, ActionListener l) {
		alert = new JDialogAlert(message, l);
		alert.setVisible(true);
	}
	
	public void disposeDialog() {
		alert.dispose();
	}
	
	public void cleanForm(Component[] componentes) {
		 
	    for (int i = 0; i < componentes.length; i++) {
	        if (componentes[i] instanceof JPanel) {
	            cleanForm(((JPanel) componentes[i]).getComponents());
	        } else if (componentes[i] instanceof JViewport) {
	            cleanForm(((JViewport) componentes[i]).getComponents());
	        } else if (componentes[i] instanceof JScrollPane) {
	            cleanForm(((JScrollPane) componentes[i]).getComponents());
	        } else if (componentes[i] instanceof JTextField) {
	            ((JTextField) componentes[i]).setText("");
	        } else if (componentes[i] instanceof JTextArea) {
	            ((JTextArea) componentes[i]).setText("");
	        } else if (componentes[i] instanceof JPasswordField) {
	            ((JPasswordField) componentes[i]).setText("");
	        } else if (componentes[i] instanceof JCheckBox) {
	            ((JCheckBox) componentes[i]).setSelected(false);
	        } else if (componentes[i] instanceof JRadioButton) {
	            ((JRadioButton) componentes[i]).setSelected(false);
	        } else if (componentes[i] instanceof JToggleButton) {
	            ((JToggleButton) componentes[i]).setSelected(false);
	        } else if (componentes[i] instanceof JComboBox) {
	            ((JComboBox<?>) componentes[i]).setSelectedIndex(0);
	        } else if (componentes[i] instanceof JSlider) {
	            ((JSlider) componentes[i]).setValue(((JSlider) componentes[i]).getMinimum());
	        } else if (componentes[i] instanceof JFormattedTextField) {
	            ((JFormattedTextField) componentes[i]).setText("");
	        }
	    }
	 
	}
	
	
	
	

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setManagerPanel(JPanel managerPanel) {
		this.managerPanel = managerPanel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public ButtonFrameView getButtonAddProcess() {
		return buttonAddProcess;
	}

	public ButtonFrameView getButtonUpdateProcess() {
		return buttonUpdateProcess;
	}

	public ButtonFrameView getButtonEditProcess() {
		return buttonEditProcess;
	}

	public void setButtonEditProcess(ButtonFrameView buttonEditProcess) {
		this.buttonEditProcess = buttonEditProcess;
	}

	public ButtonFrameView getButtonDeleteProcess() {
		return buttonDeleteProcess;
	}

	public void setButtonDeleteProcess(ButtonFrameView buttonDeleteProcess) {
		this.buttonDeleteProcess = buttonDeleteProcess;
	}

	public ButtonFrameView getSimulateProcess() {
		return simulateProcess;
	}

	public void setSimulateProcess(ButtonFrameView simulateProcess) {
		this.simulateProcess = simulateProcess;
	}
	
	
	
	
	public JPanel getManagerPanel() {
		return managerPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public ButtonFrameView getShowProcess() {
		return showProcess;
	}

	public void setShowProcess(ButtonFrameView showProcess) {
		this.showProcess = showProcess;
	}

//	public static void main(String[] args) {
//		FrameView f = new FrameView(null);
//	}

	

}
