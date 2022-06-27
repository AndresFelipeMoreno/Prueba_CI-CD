package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Events;

public class JDialogAlert extends JDialog{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JDialogAlert(String message, ActionListener l) {
		
		setSize(550, 300);
		setUndecorated(true);
		setModal(false);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 1));
		getContentPane().setBackground(getBackground());
		add(new JPanel());
		JLabel text = new JLabel(message, SwingConstants.CENTER);
		text.setFont(new Font("Serif", Font.BOLD, 20));
		add(text);
		add(createButtonActions("Volver", l,Events.ALERTA_VOLVER.name()));
	}
	
	public JButton createButtonActions(String name,ActionListener listener,String Commmand) {
		JButton button = new JButton(name);
		button.setBackground(Color.BLACK);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setFont(new Font("HelveticaNeue-Bold", Font.BOLD, 18));
		button.setForeground(new Color(204,204,0));
		button.addActionListener(listener);
		button.setActionCommand(Commmand);
		button.setFocusPainted(false);
		button.setFocusable(false);
		button.setActionCommand(Commmand);
		return button;
	}
}
