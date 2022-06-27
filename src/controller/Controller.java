package controller;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Manager;
import models.MyProcess;
import models.Processor;
import views.FrameView;
import views.PanelAddProcess;
import views.PanelChanguePriorityProcess;
import views.PanelComunicateProcess;
import views.PanelSimulation;
import views.PanelTableComunicateProcess;
import views.PanelTableProcess;

public class Controller implements ActionListener {

	private FrameView frameView;
	private Manager manager;
	private PanelAddProcess addProcess;
	private PanelTableProcess tableProcess;
	private PanelSimulation panelSimulation;
	private PanelComunicateProcess comunicateProcess;
	private PanelChanguePriorityProcess changuePriorityProcess;
	private PanelTableComunicateProcess panelTableComunicateProcess;
	private int count;
	GridBagConstraints bagConstraints;

	public Controller() {
		bagConstraints = new GridBagConstraints();
		setManager(new Manager(new Processor(5)));
		setFrameView(new FrameView(this, Events.EXIT.name()));
		frameView.setResizable(false);
		addProcess = new PanelAddProcess(this);
		count = 0;
	}

	public void replaceJPanel(JPanel jPanel) {
		frameView.getManagerPanel().setVisible(false);
		frameView.getManagerPanel().removeAll();
		frameView.getManagerPanel().revalidate();
		frameView.getManagerPanel().repaint();
		frameView.cleanForm(frameView.getManagerPanel().getComponents());
		frameView.getManagerPanel().setLayout(new BorderLayout());
		if (jPanel!=null) {
			frameView.getManagerPanel().add(jPanel);
		}
		frameView.getManagerPanel().setVisible(true);
		frameView.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (Events.valueOf(event.getActionCommand())) {
		case ADD_PROCESS:
			
			replaceJPanel(null);
			frameView.getManagerPanel().setLayout(new GridBagLayout());
			
			frameView.getManagerPanel().add(addProcess,bagConstraints);
			frameView.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frameView.setLocationRelativeTo(null);
			

			break;
		case UPDATE_PROCESS:
			break;
		case DELETE_PROCESS:
			break;
		case SHOW_PROCESS_ADD:
			tableProcess = new PanelTableProcess(manager.getListMyProcessAdd(), this);
			replaceJPanel(null);
			frameView.getManagerPanel().setLayout(new GridBagLayout());
			frameView.getManagerPanel().add(tableProcess,bagConstraints);
			frameView.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frameView.setLocationRelativeTo(null);
			break;
		case SIMULATE_PROCESS:

			if (count == 0) {
				manager.Simulate();
			}
			count = 1;
			panelSimulation = new PanelSimulation(this);
			replaceJPanel(null);
			frameView.getManagerPanel().setLayout(new GridBagLayout());
			frameView.getManagerPanel().add(panelSimulation,bagConstraints);
			frameView.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frameView.setLocationRelativeTo(null);
			break;
		case CONFIRM_ADD_PROCESS:
			String text = addProcess.getNameProcessText().getText();
			String priority = addProcess.getPriorityText().getText();
			String numberUnit = addProcess.getTimeUnitsProcessText().getText();
			
			boolean isNumeric = numberUnit.matches("[+-]?\\d*(\\.\\d+)?");
			boolean isNumericPriority = numberUnit.matches("[+-]?\\d*(\\.\\d+)?");
			boolean aux = false;
			for (int i = 0; i < manager.getListProcessReady().size(); i++) {
				if (manager.getListProcessReady().get(i).getName().equalsIgnoreCase(text)) {
					frameView.createAlert("Ya existe un proceso con ese nombre", this);
					aux = true;
					break;
				}
				if (isNumericPriority) {
					if (manager.getListProcessReady().get(i).getPriority() == Integer.parseInt(priority)) {
						frameView.createAlert("Ya existe un proceso con esa Prioridad", this);
						aux = true;
						break;
					}
				}
			}
			if (aux) {
				break;
			}
			if (text.length() <= 0) {
				frameView.createAlert("El nombre del proceso no puede estar vacio", this);
				count = 1;
			} else if (numberUnit.length() <= 0) {
				frameView.createAlert("Las unidades de tiempo del proceso no pueden estar vacias", this);
			} else if (!isNumeric) {
				frameView.createAlert("Las unidades de tiempo deben ser numeros", this);
				
			}else if (priority.length()<=0) {
				frameView.createAlert("Se le debe asignar una prioridad Al Proceso", this);
				count = 1;
			}else if (!isNumericPriority) {
				frameView.createAlert("La prioridad debe ser un valor numerico", this);
				count = 1;
			
			}  else if (numberUnit.length() >= 0 && isNumeric) {
				int number = Integer.parseInt(addProcess.getTimeUnitsProcessText().getText());
//				if (String.valueOf(addProcess.getBox().getSelectedItem()).equalsIgnoreCase("No")) {
					manager.addProcess(new MyProcess(text, number, convertBoxContent(addProcess.getBox()),
							convertBoxContent(addProcess.getBoxIsLayOff()), convertBoxContent(addProcess.getBoxIsDestroyed())
							,convertBoxContent(addProcess.getBoxComunicate())),Integer.parseInt(priority));
					frameView.createAlert("El proceso se agrego correctamente", this);
//				} else {
////					manager.addProcess(new MyProcess(text, number, true));
//					frameView.createAlert("El proceso se agrego correctamente", this);
//				}
			}
			frameView.cleanForm(frameView.getMainPanel().getComponents());
			frameView.cleanForm(frameView.getManagerPanel().getComponents());
			frameView.cleanForm(addProcess.getComponents());
			break;
		case ALERTA_VOLVER:
			frameView.disposeDialog();
			break;
		case RETURN_MAIN_MENU:
			replaceJPanel(frameView.getMainPanel());
			frameView.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frameView.setLocationRelativeTo(null);
			break;
		case SEARCH_LIST_PROCESS:
			showListProcess(panelSimulation.getListStates().getSelectedIndex());
			break;
			
		case CHANGUE_PRIORITY_PROCESS:
			changuePriorityProcess = new PanelChanguePriorityProcess(manager.getListMyProcessAdd(), this);
			
			replaceJPanel(null);
			frameView.getManagerPanel().setLayout(new GridBagLayout());
			frameView.getManagerPanel().add(changuePriorityProcess,bagConstraints);
			frameView.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frameView.setLocationRelativeTo(null);
			break;
			
		case CONFIRM_CHANGUE_PRIORITY:
			String priorityNumeric = changuePriorityProcess.getUpdatePriority().getText();
			boolean isNumericPriorityTwo = priorityNumeric.matches("[+-]?\\d*(\\.\\d+)?");
			if (changuePriorityProcess.getUpdatePriority().getText().length()>0 && isNumericPriorityTwo) {
				String process =String.valueOf(changuePriorityProcess.getBoxListProccess().getSelectedItem()).substring(
						String.valueOf(changuePriorityProcess.getBoxListProccess().getSelectedItem()).lastIndexOf(": ") +1).replaceAll("\\s+","");
				if (isNumericPriorityTwo && priorityNumeric.length()>0) {
					for (int i = 0; i < manager.getListMyProcessAdd().size(); i++) {
						if (manager.getListMyProcessAdd().get(i).getName().equalsIgnoreCase(process)) {
							boolean aux3 = false;
							for (int j = 0; j < manager.getListMyProcessAdd().size(); j++) {
								if (manager.getListMyProcessAdd().get(j).getPriority() == Integer.parseInt(priorityNumeric)) {
									System.out.println("siuuuuu");
									frameView.createAlert("Ya existe un proceso con esa Prioridad", this);
									aux3 = true;
									break;
								}
							}
							if (!aux3) {
								manager.getListMyProcessAdd().get(i).setPriority(Integer.parseInt(changuePriorityProcess.getUpdatePriority().getText()));
								frameView.createAlert("Se ha actualizado la prioridad Correctamente", this);
							}
						
						}
					}
			}
			
			
			}else {
				frameView.createAlert("El valor debe ser numerico y no puede estar vacio", this);
			}
			
			
			break;
		case COMUNICATE_PROCESS:
			comunicateProcess = new PanelComunicateProcess(manager.getListProcessReady(), this);
			replaceJPanel(null);
			frameView.getManagerPanel().setLayout(new GridBagLayout());
			frameView.getManagerPanel().add(comunicateProcess,bagConstraints);
			frameView.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frameView.setLocationRelativeTo(null);
			break;

		case ACCEPT_COMUNICATION:
			String processOne = String.valueOf(comunicateProcess.getBoxTransmition().getSelectedItem()).substring(
					String.valueOf(comunicateProcess.getBoxTransmition().getSelectedItem()).lastIndexOf(": ") +1).replaceAll("\\s+","");
			String processTwo = String.valueOf(comunicateProcess.getBoxReception().getSelectedItem()).substring(
					String.valueOf(comunicateProcess.getBoxReception().getSelectedItem()).lastIndexOf(": ") +1).replaceAll("\\s+","");
			manager.addMyProcessComunicate(processOne, processTwo);
			boolean verifyComunicate = manager.verifyComunicate2(manager.searchProcessReceiver(processOne));
			if (verifyComunicate) {
				frameView.createAlert("Comunicacion agregada", this);
			}else {
				frameView.createAlert("Uno de los procesos no tiene activa la comunicacion", this);
			}
			
		
			
			break;
		
		case RESET_SIMULATION:
			count = 0;
			manager.cleanList();
			frameView.createAlert("La simulacion se Reinicio Correctamente", this);

			break;
		default:
			break;
			
		case GO_BACK_MENU_SIMULATION:
			panelSimulation = new PanelSimulation(this);
			replaceJPanel(null);
			frameView.getManagerPanel().setLayout(new GridBagLayout());
			frameView.getManagerPanel().add(panelSimulation,bagConstraints);
			frameView.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frameView.setLocationRelativeTo(null);
			break;
			
		case EXIT:
			
			frameView.setVisible(false);
			frameView.dispose();
			
			break;
		}
	}
	
	public boolean convertBoxContent(JComboBox<String> box) {
		boolean aux = true;
		if (String.valueOf(box.getSelectedItem()).equalsIgnoreCase("No")) {
			return false;
		}
		return aux;
		
	}

	public void repaintTable(ArrayList<MyProcess> arrayList) {
		tableProcess = new PanelTableProcess(arrayList, this);
		replaceJPanel(null);
		frameView.getManagerPanel().setLayout(new GridBagLayout());
		frameView.getManagerPanel().add(tableProcess,bagConstraints);
		JButton button = new JButton("                Volver Al Menu De Eleccion De Listas                 ");
		button.setFont(new Font("Serif", Font.PLAIN, 35));
		button.addActionListener(this);
		button.setActionCommand(Events.GO_BACK_MENU_SIMULATION.name());;
		tableProcess.add(button,BorderLayout.SOUTH);
	}
	
	public void repaintTableTwo(ArrayList<MyProcess> arrayList) {
		panelTableComunicateProcess = new PanelTableComunicateProcess(arrayList, this,manager.getListProcessDestroyed());
		replaceJPanel(null);
		frameView.getManagerPanel().setLayout(new GridBagLayout());
		frameView.getManagerPanel().add(panelTableComunicateProcess,bagConstraints);
		JButton button = new JButton("                Volver Al Menu De Eleccion De Listas                 ");
		button.setFont(new Font("Serif", Font.PLAIN, 35));
		button.addActionListener(this);
		button.setActionCommand(Events.GO_BACK_MENU_SIMULATION.name());;
		panelTableComunicateProcess.add(button,BorderLayout.SOUTH);
	}

	public void showListProcess(int indexComboBox) {
		if (indexComboBox == 0 || indexComboBox == 1) {
			repaintTable(manager.getListProcessReady());
		} else if (indexComboBox == 2) {
			repaintTable(manager.getListProcessExecuting());
		} else if (indexComboBox == 3) {
			repaintTable(manager.getListProcessLocked());
		} else if (indexComboBox == 4) {
			repaintTable(manager.getListProcessWake());
		} else if (indexComboBox == 5) {
			repaintTable(manager.getListProcessExpiredTime());
		} else if (indexComboBox == 6) {
			repaintTable(manager.getListProcessEnd());//--------------------------
		}else if (indexComboBox == 7) {
			repaintTable(manager.getListProcessIsLayOff());
		}else if (indexComboBox == 8) {
			repaintTable(manager.getListProcessDiscontinued());
		}else if (indexComboBox == 9) {
			repaintTable(manager.getListProcessResume());
		}else if (indexComboBox == 10) {
			repaintTable(manager.getListProcessDestroyed());
		}else if (indexComboBox == 11) {
			repaintTable(manager.getDestroyedTheExecution());
		}else if (indexComboBox == 12) {
			repaintTable(manager.getListProcessSuspendedToDestroy());
		}else if (indexComboBox == 13) {
			repaintTable(manager.getListProcessResume());
		}else if (indexComboBox == 14) {
			repaintTable(manager.getListProcessWake());
		}else if (indexComboBox == 15) {
			repaintTable(manager.getListProcessLockedToDestroyed());
		}else if (indexComboBox == 16) {
			repaintTable(manager.getListProcessLockedToSuspended());
		}else if (indexComboBox == 17) {
			
			/*ArrayList<MyProcess> aux = new ArrayList<MyProcess>();
			for (int i = 0; i < manager.getListProcessReady().size(); i++) {
				if (i<manager.getSize()) {
					aux.add(manager.getListProcessReady().get(i));
				}
			}*/
			repaintTableTwo(manager.getListProcessComunicate());
			
		}
	}

	public void returnMainMenu() {
		new FrameView(null,Events.EXIT.name());
	}

	public FrameView getFrameView() {
		return frameView;
	}

	public void setFrameView(FrameView frameView) {
		this.frameView = frameView;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public static void main(String[] args) {
		new Controller();
	}

}
