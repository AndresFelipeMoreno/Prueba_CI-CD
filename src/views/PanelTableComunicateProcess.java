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

public class PanelTableComunicateProcess extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table = new JTable();
	
	private JScrollPane scroll;
	private String headers[] = { "Nombre del Proceso Origen", "Estado de la comunicacion","Nombre del proceso Destino" };
	private DefaultTableModel model = new DefaultTableModel();
	
	private JLabel aux; 
	
	
	public PanelTableComunicateProcess(ArrayList<MyProcess> arrayListOne,ActionListener listener,
			ArrayList<MyProcess> listProcessDestroyed ) {
//		  table.setAutoResizeMode( JTable.WIDTH );    
		setLayout(new BorderLayout());
		setAux(new JLabel());
//		this.add(aux);
		table.setEnabled(false);
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scroll = new JScrollPane(table);
		scroll.setVisible(true);
		insert(arrayListOne,listProcessDestroyed);
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
	public void insert(ArrayList<MyProcess> arrayList,ArrayList<MyProcess> listProcessDestroyed) {
		Object[] data2 = new Object[arrayList.size()*3];
		for (int i = 0; i < arrayList.size(); i++) {
			//if(arrayList.get(i).getProcessWithCommunicated().size()>0) {
			//	if((arrayList.get(i).isComunicates() == true && arrayList.get(i).getProcessWithCommunicated().get(0).isComunicates() == true&& verifyListDestroyed(arrayList.get(i).getProcessWithCommunicated().get(0),listProcessDestroyed) == false)) {
					data2[0] = arrayList.get(i).getName();
					if (arrayList.get(i).getPriority() < arrayList.get(i).getProcessWithCommunicated().get(0).getPriority() /*&& arrayList.get(i).isComunicates() == true*/) {
						data2[1] = "Comunicacion realizada";
					}else {
						data2[1] = "Destino no encontrado";
						
					}
					
					data2[2] = arrayList.get(i).getProcessWithCommunicated().get(0).getName();
					
			//	}
		
			//}
			model.addRow(data2);
		}
	}

	public boolean verifyListDestroyed(MyProcess process,ArrayList<MyProcess> listProcessDestroyed ) {
		for (int i = 0; i < listProcessDestroyed.size(); i++) {
			if (listProcessDestroyed.get(i).getName().equalsIgnoreCase(process.getName())) {
				return true;
			}
		}
		return false;
	}

	public JLabel getAux() {
		return aux;
	}


	public void setAux(JLabel aux) {
		this.aux = aux;
	}

}
