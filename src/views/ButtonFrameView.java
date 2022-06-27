package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonFrameView extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonFrameView(ActionListener listener, String action,String text) {
		setText(text);
		setActionCommand(action);
		addActionListener(listener);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setFocusable(false);
		setPreferredSize(new Dimension(150, 50));
	}
}
