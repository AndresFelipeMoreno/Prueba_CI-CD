package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.MyProcess;

public class PanelTableProcess extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table = new JTable();
	
	private JScrollPane scroll;
	private String headers[] = { "Nombre Del Proceso", "Unidades de tiempo","Prioridad","Bloqueado","Suspendido","Destruido","Se Comunica" };
	private DefaultTableModel model = new DefaultTableModel();
	
	private JLabel aux; 
	
	
	public PanelTableProcess(ArrayList<MyProcess> arrayList,ActionListener listener) {

		
		
		
	

		
//		  table.setAutoResizeMode( JTable.WIDTH );    

		setLayout(new BorderLayout());
		setAux(new JLabel());
//		this.add(aux);
		table.setEnabled(false);
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setVisible(true);
		insert(arrayList);
		add(scroll, BorderLayout.CENTER);
		JButton aux = new PanelAddProcess(listener).getCancel();
		aux.setText("                          Volver al Menu Principal                               ");
		table.setRowHeight(table.getRowHeight()+30);
		table.setFont(new Font("Serif", Font.PLAIN, 25));
		this.add(aux,BorderLayout.SOUTH);
		setVisible(true);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setPreferredSize(new Dimension(100,90));
		tableHeader.setFont(new Font("Serif", Font.PLAIN, 20));
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(7);
		table.getColumnModel().getColumn(3).setPreferredWidth(7);
		table.getColumnModel().getColumn(4).setPreferredWidth(7);
		table.getColumnModel().getColumn(5).setPreferredWidth(7);
		table.getColumnModel().getColumn(6).setPreferredWidth(7);
//		table.setPreferredSize(new Dimension(500,500));
//		table.setRowHeight(table.getRowHeight()+10);
//	     table.getColumnModel().getColumn(0).setPreferredWidth(200);
//	     table.getColumnModel().getColumn(1).setPreferredWidth(400);
//	     table.getColumnModel().getColumn(2).setPreferredWidth(700);
//	      DefaultTableCellRenderer cellRenderer;
//
//	     cellRenderer = new DefaultTableCellRenderer();
//	      cellRenderer.setHorizontalAlignment(JLabel.CENTER);
//	      table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
	}
	

	public void insert(ArrayList<MyProcess> arrayList) {
		Object[] data2 = new Object[arrayList.size()*7];
		for (int i = 0; i < arrayList.size(); i++) {
			data2[0] = arrayList.get(i).getName();
			data2[1] = String.valueOf(arrayList.get(i).getTimeProcess());
			data2[2] = String.valueOf(arrayList.get(i).getPriority());
			data2[3] = arrayList.get(i).isBlock() ? "Si" : "No";
			data2[4] = arrayList.get(i).isLayOff() ? "Si" : "No";
			data2[5] = arrayList.get(i).isDestroy() ? "Si" : "No";
			data2[6] = arrayList.get(i).isComunicates() ? "Si" : "No";
			model.addRow(data2);
			
		}
	}


	public JLabel getAux() {
		return aux;
	}


	public void setAux(JLabel aux) {
		this.aux = aux;
	}

}
