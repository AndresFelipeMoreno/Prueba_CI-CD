package views;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Events;

public class PanelAddProcess extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel nameProcessLabel, timeUnitsProcess, messageState,message,message2,message3,message4,message5,message6;
	JTextField nameProcessText;
	JTextField timeUnitsProcessText;
	JButton submit, cancel, go_back;
	private JTextField priorityText;
	private JLabel priority;
	private JLabel islayoffLabel;
	private JLabel	isDestroyedLabel;
	private JLabel comunicateLabel;
	
	JComboBox<String> boxIsBlock = new JComboBox<>();
	JComboBox<String> boxIsLayOff = new JComboBox<>();
	JComboBox<String> boxIsDestroyed = new JComboBox<>();
	JComboBox<String> boxComunicate= new JComboBox<>();

	public PanelAddProcess(ActionListener actionListener) {

		nameProcessLabel = new JLabel();
		boxIsBlock  = new JComboBox<String>();
		mixFont(boxIsBlock);
		boxIsBlock.addItem("No");
		boxIsBlock.addItem("Si");
		boxIsBlock.setSelectedIndex(0);
		nameProcessLabel.setText("Nombre de Proceso: ");
		mixFont(nameProcessLabel);
		nameProcessText = new JTextField();
		timeUnitsProcess = new JLabel();
		timeUnitsProcess.setText("Unidades de Tiempo Del Proceso: ");
		mixFont(timeUnitsProcess);
		timeUnitsProcessText = new JTextField();
		submit = new JButton("Agregar Proceso");
		mixFont(submit);
		cancel = new JButton("Volver al Menu Principal");
		mixFont(cancel);
		cancel.addActionListener(actionListener);
		cancel.setActionCommand(Events.RETURN_MAIN_MENU.name());
		panel = new JPanel(new GridLayout(11, 1));
		panel.add(nameProcessLabel);
		mixFont(nameProcessText);
		panel.add(nameProcessText);
		panel.add(timeUnitsProcess);
		mixFont(timeUnitsProcessText);
		panel.add(timeUnitsProcessText);
		messageState = new JLabel();
		messageState.setText("Proceso Bloqueado: ");
		mixFont(messageState);
		panel.add(messageState);
		panel.add(boxIsBlock);
		message = new JLabel();
		message2 = new JLabel();
		message3 = new JLabel();
		
		submit.addActionListener(actionListener);
		submit.setActionCommand(Events.CONFIRM_ADD_PROCESS.name());
		priority = new JLabel("Ingrese la prioridad (Valor numerico):");
		PanelAddProcess.mixFont(priority);
		priorityText = new JTextField();
		PanelAddProcess.mixFont(priorityText);
		
		

		panel.add(priority);
		
		panel.add(priorityText);
		
//		panel.add(message3);
		
		islayoffLabel = new JLabel("Proceso Suspendido?");
		PanelAddProcess.mixFont(islayoffLabel);
		boxIsLayOff  = new JComboBox<String>();
		mixFont(boxIsLayOff);
		boxIsLayOff.addItem("No");
		boxIsLayOff.addItem("Si");
		boxIsLayOff.setSelectedIndex(0);
		
		panel.add(islayoffLabel);
		panel.add(boxIsLayOff);
		
		
		isDestroyedLabel = new JLabel("Proceso Destruido?");
		PanelAddProcess.mixFont(isDestroyedLabel);
		
		boxIsDestroyed  = new JComboBox<String>();
		mixFont(boxIsDestroyed);
		boxIsDestroyed.addItem("No");
		boxIsDestroyed.addItem("Si");
		boxIsDestroyed.setSelectedIndex(0);
		
		panel.add(isDestroyedLabel);
		panel.add(boxIsDestroyed);
		
		comunicateLabel = new JLabel("El proceso se puede comunicar?");
		PanelAddProcess.mixFont(comunicateLabel);
		
		boxComunicate  = new JComboBox<String>();
		mixFont(boxComunicate);
		boxComunicate.addItem("No");
		boxComunicate.addItem("Si");
		boxComunicate.setSelectedIndex(0);
		
		panel.add(comunicateLabel);
		panel.add(boxComunicate);
		
		panel.add(message);
		
		panel.add(submit);
		panel.add(message2);
		panel.add(cancel);
		add(panel);
	}
	
	static void mixFont(Component jLabel) {
		jLabel.setFont(new Font("Serif", Font.PLAIN, 35));
	}
	
	
	
	
	public JComboBox<String> getBoxIsBlock() {
		return boxIsBlock;
	}

	public JComboBox<String> getBoxIsLayOff() {
		return boxIsLayOff;
	}

	public JComboBox<String> getBoxIsDestroyed() {
		return boxIsDestroyed;
	}

	public JComboBox<String> getBoxComunicate() {
		return boxComunicate;
	}

	public JTextField getPriorityText() {
		return priorityText;
	}

	public void setPriorityText(JTextField priorityText) {
		this.priorityText = priorityText;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getNameProcessLabel() {
		return nameProcessLabel;
	}

	public void setNameProcessLabel(JLabel nameProcessLabel) {
		this.nameProcessLabel = nameProcessLabel;
	}

	public JLabel getTimeUnitsProcess() {
		return timeUnitsProcess;
	}

	public void setTimeUnitsProcess(JLabel timeUnitsProcess) {
		this.timeUnitsProcess = timeUnitsProcess;
	}

	public JLabel getMessageState() {
		return messageState;
	}

	public void setMessageState(JLabel messageState) {
		this.messageState = messageState;
	}

	public JLabel getMessage() {
		return message;
	}

	public void setMessage(JLabel message) {
		this.message = message;
	}

	public JLabel getMessage2() {
		return message2;
	}
	public void setMessage2(JLabel message2) {
		this.message2 = message2;
	}

	public JTextField getNameProcessText() {
		return nameProcessText;
	}


	public void setNameProcessText(JTextField nameProcessText) {
		this.nameProcessText = nameProcessText;
	}

	public JTextField getTimeUnitsProcessText() {
		return timeUnitsProcessText;
	}

	public void setTimeUnitsProcessText(JTextField timeUnitsProcessText) {
		this.timeUnitsProcessText = timeUnitsProcessText;
	}

	public JButton getSubmit() {
		return submit;
	}

	public void setSubmit(JButton submit) {
		this.submit = submit;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

	public JButton getGo_back() {
		return go_back;
	}





	public void setGo_back(JButton go_back) {
		this.go_back = go_back;
	}





	public JComboBox<String> getBox() {
		return boxIsBlock;
	}





	public void setBox(JComboBox<String> box) {
		this.boxIsBlock = box;
	}





	/*public static void main(String[] args) {
		PanelAddProcess addProcess = new PanelAddProcess(null);
		
		JFrame frame = new JFrame();
		frame.add(addProcess, BorderLayout.CENTER);
		frame.setSize(1000, 600);
		frame.setVisible(true);
	}*/
	
	

}
