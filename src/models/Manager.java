package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Manager {

	private Processor processor;
	private ArrayList<MyProcess> listProcessReady; // ok
	private MyQueue<MyProcess> processReadyQueue; // ok
	private MyQueue<MyProcess> processReadyAux; // ok
	private ArrayList<MyProcess> listProcessEnd; // ok
	private ArrayList<MyProcess> listProcessExpiredTime; // ok
	private ArrayList<MyProcess> listProcessLocked; // ok
	private ArrayList<MyProcess> listProcessWake; // ok
	private ArrayList<MyProcess> listProcessExecuting; // ok
	private ArrayList<MyProcess> listProcessToBlock; // ok
	private ArrayList<MyProcess> listMyProcessAdd; // ok
	private ArrayList<Integer> listPriority;
	private ArrayList<MyProcess> listProcessLockedToSuspended;
	private ArrayList<MyProcess> listProcessLockedToDestroyed;
	private ArrayList<MyProcess> listProcessSuspendedToDestroy;
	private ArrayList<MyProcess> listProcessIsSuspended;
	private ArrayList<MyProcess> listProcessDiscontinued;
	private ArrayList<MyProcess> listProcessSuspendedToReady;
	private ArrayList<MyProcess> listProcessIsDestroyed;
	private ArrayList<MyProcess> listProcessDestroyed;
	private ArrayList<MyProcess> destroyedTheExecution;
	private ArrayList<MyProcess> listProcessComunicate;

	private int size;
	
	public Manager(Processor processor) {
		this.processor = processor;
		listProcessReady = new ArrayList<>();
		listProcessEnd = new ArrayList<>();
		listProcessExpiredTime = new ArrayList<>();
		listProcessLocked = new ArrayList<>();
		listProcessWake = new ArrayList<>();
		listProcessExecuting = new ArrayList<>();
		listProcessToBlock = new ArrayList<>();
		listMyProcessAdd = new ArrayList<>();
		processReadyQueue = new MyQueue<MyProcess>();
		processReadyAux = new MyQueue<MyProcess>();
		listPriority = new ArrayList<>();
		listProcessIsSuspended = new ArrayList<>();
		listProcessDiscontinued = new ArrayList<>();
		listProcessSuspendedToReady = new ArrayList<>();
		setListProcessIsDestroyed(new ArrayList<>());
		setListProcessDestroyed(new ArrayList<>());
		setDestroyedTheExecution(new ArrayList<>());
		listProcessSuspendedToDestroy = new ArrayList<>();
		listProcessLockedToDestroyed = new ArrayList<>();
		listProcessLockedToSuspended = new ArrayList<>();
		listProcessComunicate = new ArrayList<>();

	}

	public void addProcess(MyProcess process, int priority) {
		process.setPriority(priority);
		processReadyQueue.push(process, priority);
		// processReadyAux.push(process, priority);
		addProcess(listProcessReady, process);
		addProcess(listMyProcessAdd, process);
		// listPriority.add(priority);
		// Collections.sort(listPriority);
	}

	// Convierte una cola auxiliar a un arraylist para mostrar la lista de procesos
	// listos en orden de prioridad

	/*
	 * public void covertQueueinArraList() { while (!processReadyAux.isEmpty()) {
	 * listProcessReady.add(processReadyAux.pop()); } }
	 */

	public void covertQueueinArraListForComunicate() {
		while (!processReadyAux.isEmpty()) {
			for (int i = 0; i < listPriority.size(); i++) {
				listProcessReady.add(new MyProcess(processReadyAux.peek().getData().getName(),
						processReadyAux.peek().getData().getTimeProcess(), listPriority.get(i),
						processReadyAux.peek().getData().isBlock(), processReadyAux.peek().getData().isLayOff(),
						processReadyAux.peek().getData().isDestroy(),
						processReadyAux.peek().getData().isComunicates()));
				processReadyAux.pop();
			}

		}
	}

	public void showListForComunicate() {
		for (int i = 0; i < listProcessReady.size(); i++) {
			System.out.println(listProcessReady.get(i).getName() + listProcessReady.get(i).getPriority());
		}

	}

	public boolean covertIntToBoolean(int status) {
		if (status == 1) {
			return true;
		} else
			return false;
	}

	public void showProcess() {

		System.out.println("Procesos Listos");

		for (int i = 0; i < listProcessReady.size(); i++) {

			System.out.println(listProcessReady.get(i).getName() + " | " + listProcessReady.get(i).getTimeProcess()
					+ " | " + listProcessReady.get(i).getPriority());

		}

		System.out.println("Procesos terminados");
		for (int i = 0; i < listProcessEnd.size(); i++) {

			System.out.println(listProcessEnd.get(i).getName());
		}

		System.out.println("Procesos Bloqueados ");
		for (int i = 0; i < listProcessLocked.size(); i++) {
			System.out.println(listProcessLocked.get(i).getName() + " | " + listProcessLocked.get(i).getTimeProcess());

		}

		System.out.println("Procesos Despachados");
		for (int i = 0; i < listProcessReady.size(); i++) {
			System.out.println(listProcessReady.get(i).getName() + " | " + listProcessReady.get(i).getTimeProcess());

		}

		System.out.println("Procesos en Ejecucion");
		for (int i = 0; i < listProcessExecuting.size(); i++) {
			System.out.println(
					listProcessExecuting.get(i).getName() + " | " + listProcessExecuting.get(i).getTimeProcess());
		}

		System.out.println("Procesos expirados");
		for (int i = 0; i < listProcessExpiredTime.size(); i++) {
			System.out.println(
					listProcessExpiredTime.get(i).getName() + " | " + listProcessExpiredTime.get(i).getTimeProcess());
		}

		System.out.println("Procesos a suspencion");
		for (int i = 0; i < listProcessIsSuspended.size(); i++) {
			System.out.println(
					listProcessIsSuspended.get(i).getName() + " | " + listProcessIsSuspended.get(i).getTimeProcess());
		}

		System.out.println("Procesos suspendidos");
		for (int i = 0; i < listProcessDiscontinued.size(); i++) {
			System.out.println(
					listProcessDiscontinued.get(i).getName() + " | " + listProcessDiscontinued.get(i).getTimeProcess());
		}

		System.out.println("Procesos reanudados");
		for (int i = 0; i < listProcessSuspendedToReady.size(); i++) {
			System.out.println(listProcessSuspendedToReady.get(i).getName() + " | "
					+ listProcessSuspendedToReady.get(i).getTimeProcess());
		}

		System.out.println("Procesos Destruidos");
		for (int i = 0; i < listProcessDestroyed.size(); i++) {
			System.out.println(
					listProcessDestroyed.get(i).getName() + " | " + listProcessDestroyed.get(i).getTimeProcess());
		}

		System.out.println("Procesos Destruidos a ejecucion");
		for (int i = 0; i < destroyedTheExecution.size(); i++) {
			System.out.println(
					destroyedTheExecution.get(i).getName() + " | " + destroyedTheExecution.get(i).getTimeProcess());
		}

		System.out.println("Procesos Suspendidos a destruccion");
		for (int i = 0; i < listProcessSuspendedToDestroy.size(); i++) {
			System.out.println(listProcessSuspendedToDestroy.get(i).getName() + " | "
					+ listProcessSuspendedToDestroy.get(i).getTimeProcess());
		}

		System.out.println("Procesos Suspendidos a listos");
		for (int i = 0; i < listProcessSuspendedToReady.size(); i++) {
			System.out.println(listProcessSuspendedToReady.get(i).getName() + " | "
					+ listProcessSuspendedToReady.get(i).getTimeProcess());
		}

		System.out.println("Procesos Suspendidos a listos");
		for (int i = 0; i < listProcessSuspendedToReady.size(); i++) {
			System.out.println(listProcessSuspendedToReady.get(i).getName() + " | "
					+ listProcessSuspendedToReady.get(i).getTimeProcess());
		}

		System.out.println("Procesos despertados");
		for (int i = 0; i < listProcessWake.size(); i++) {
			System.out.println(listProcessWake.get(i).getName() + " | " + listProcessWake.get(i).getTimeProcess());
		}

		System.out.println("Procesos bloqueados a destruidos");
		for (int i = 0; i < listProcessLockedToDestroyed.size(); i++) {
			System.out.println(listProcessLockedToDestroyed.get(i).getName() + " | "
					+ listProcessLockedToDestroyed.get(i).getTimeProcess());
		}

		System.out.println("Procesos bloqueados a suspendidos");
		for (int i = 0; i < listProcessLockedToSuspended.size(); i++) {
			System.out.println(listProcessLockedToSuspended.get(i).getName() + " | "
					+ listProcessLockedToSuspended.get(i).getTimeProcess());
		}

	}

	public int changeTime(int number) {
		if (number > 0) {
			return 0;
		}
		return number;
	}

	public ArrayList<MyProcess> getListMyProcessAdd() {
		return listMyProcessAdd;
	}

	public void setListMyProcessAdd(ArrayList<MyProcess> listMyProcessAdd) {
		this.listMyProcessAdd = listMyProcessAdd;
	}

	public Processor getProcessor() {
		return processor;
	}

	public ArrayList<MyProcess> getListProcessReady() {
		return listProcessReady;
	}

	public MyQueue<MyProcess> getProcessReadyQueue() {
		return processReadyQueue;
	}

	public ArrayList<MyProcess> getListProcessEnd() {
		return listProcessEnd;
	}

	public ArrayList<MyProcess> getListProcessExpiredTime() {
		return listProcessExpiredTime;
	}

	public ArrayList<MyProcess> getListProcessLocked() {
		return listProcessLocked;
	}

	public ArrayList<MyProcess> getListProcessWake() {
		return listProcessWake;
	}

	public ArrayList<MyProcess> getListProcessExecuting() {
		return listProcessExecuting;
	}

	public ArrayList<MyProcess> getListProcessToBlock() {
		return listProcessToBlock;
	}

	private void addProcess(ArrayList<MyProcess> listProcess, MyProcess process) {
		listProcess.add(new MyProcess(process.getName(), process.getTimeProcess(), process.getPriority(),
				process.isBlock(), process.isLayOff(), process.isDestroy(), process.isComunicates()));
	}

	private void validateStatus(MyProcess process) {

		if (process.isBlock()) {
			addProcess(listProcessLocked, process);
			addProcess(listProcessToBlock, process);
			if (process.isLayOff()) {
				addProcess(listProcessLockedToSuspended, process);
				addProcess(listProcessDiscontinued, process);

				if (process.isDestroy()) {
					searchInList(process.getName(), listProcessReady).setComunicates(false);
					addProcess(listProcessSuspendedToDestroy, process);
					validateDestroyed(process);
				} else {
					addProcess(listProcessSuspendedToReady, process);
					addProcess(listProcessReady, process);
				}
			} else if (process.isDestroy()) {
				searchInList(process.getName(), listProcessReady).setComunicates(false);
				addProcess(listProcessLockedToDestroyed, process);
				validateDestroyed(process);
				
			} else {
				addProcess(listProcessWake, process);
				addProcess(listProcessReady, process);
				processReadyQueue.pop();
				processReadyQueue.push(process, process.getPriority());
			}
		} else if (process.isLayOff()) {
			addProcess(listProcessIsSuspended, process);
			addProcess(listProcessDiscontinued, process);
			if (process.isDestroy()) {
				searchInList(process.getName(), listProcessReady).setComunicates(false);
				addProcess(listProcessSuspendedToDestroy, process);
				validateDestroyed(process);
			} else {
				addProcess(listProcessSuspendedToReady, process);
				addProcess(listProcessReady, process);
				processReadyQueue.pop();
				processReadyQueue.push(process, process.getPriority());
			}
		} else if (process.isDestroy()) {
			searchInList(process.getName(), listProcessReady).setComunicates(false);
			validateDestroyed(process);

		} else {
			addProcess(listProcessReady, process);
			addProcess(listProcessExpiredTime, process);
			processReadyQueue.pop();
			processReadyQueue.push(process, process.getPriority());
		}

		// Collections.sort(listProcessReady);

	}

	private void validateDestroyed(MyProcess process) {
		addProcess(listProcessIsDestroyed, process);
		addProcess(listProcessDestroyed, process);
		addProcess(destroyedTheExecution, process);
		MyProcess temp;
		int updateTime = process.getTimeProcess() - processor.getTimeProcesssor();
		temp = new MyProcess(process.getName(), updateTime, process.getPriority(),process.isBlock(), process.isLayOff(), process.isDestroy(),
				process.isComunicates());
		addProcess(listProcessExecuting, temp);
		if (process.getTimeProcess() - 5 > 0) {
			process.setTimeProcess(process.getTimeProcess() - 5);
			addProcess(listProcessReady, process);
			addProcess(listProcessExpiredTime, process);
			processReadyQueue.pop();
			processReadyQueue.push(process, process.getPriority());

		} else {
			addProcess(listProcessEnd, process);
			processReadyQueue.pop();
		}
	}

	private void overTimeProcess(MyProcess process) {
		MyProcess temp;
		int updateTime;
		updateTime = Math.abs(processor.getTimeProcesssor() - process.getTimeProcess());
		temp = new MyProcess(process.getName(), changeTime(updateTime), process.getPriority(),process.isBlock(), process.isLayOff(),
				process.isDestroy(), process.isComunicates());
		addProcess(listProcessEnd, temp);
		processReadyQueue.pop();
	}

	private void updateTimeProcess(MyProcess process) {
		process.setTimeProcess(process.getTimeProcess() - 5);
		validateStatus(process);
	}

	public void Simulate() {
		while (!processReadyQueue.isEmpty()) {
			MyProcess process = processReadyQueue.peek().getData();
			
			MyProcess temp;
			int updateTime = process.getTimeProcess() - processor.getTimeProcesssor();
			temp = new MyProcess(process.getName(), (updateTime < 0 ? 0 : updateTime), process.getPriority(),process.isBlock(),
					process.isLayOff(), process.isDestroy(), process.isComunicates());
			addProcess(listProcessExecuting, temp);
			
			MyProcess searchInList = searchInList(process.getName(), listProcessReady);
			if (verifyComunicate2(searchInList)) {
				listProcessComunicate.add(searchInList);
				
			}

			if ((process.getTimeProcess() - processor.getTimeProcesssor()) > 0) {
				updateTimeProcess(process);
			} else {
				overTimeProcess(process);
			}
		}
		Collections.sort(listProcessReady);
		for (int i = 0; i < listProcessExecuting.size(); i++) {
			listProcessExecuting.get(i).setPriority(0);
		}
		for (int i = 0; i < listProcessEnd.size(); i++) {
			listProcessEnd.get(i).setPriority(0);
		}
	}

	public void cleanList() {
		processReadyQueue = new MyQueue<MyProcess>();
		listProcessReady.clear();
		listProcessEnd.clear();
		listProcessExpiredTime.clear();
		listProcessLocked.clear();
		listProcessWake.clear();
		listProcessExecuting.clear();
		listProcessToBlock.clear();
		listMyProcessAdd.clear();
		listProcessComunicate.clear();
		listProcessDestroyed.clear();
		listProcessDiscontinued.clear();
		listProcessIsDestroyed.clear();
		listProcessIsSuspended.clear();
		listProcessLockedToDestroyed.clear();
		listProcessLockedToSuspended.clear();
		listProcessSuspendedToDestroy.clear();
		listProcessSuspendedToReady.clear();
	}

	public void changePriority(String nameProcess, int updatePriority) {
		MyNode<MyProcess> temp = processReadyQueue.peek();
		while (temp != null) {
			if (temp.getData().getName().equalsIgnoreCase(nameProcess)) {
				temp.setPriority(updatePriority);
				deleteProccess(nameProcess);
				addProcess(new MyProcess(temp.getData().getName(), temp.getData().getTimeProcess(),
						temp.getData().getPriority(),temp.getData().isBlock(), temp.getData().isLayOff(), temp.getData().isDestroy(),
						temp.getData().isComunicates()), updatePriority);

			}
			temp = temp.getNext();
		}
	}

	private MyProcess searchInList(String name, ArrayList<MyProcess> myProcesses) {
		for (MyProcess myProcess : myProcesses) {
			if (name.equals(myProcess.getName())) {
				return myProcess;
			}
		}
		return null;
	}

	public boolean deleteProccess(String name) {
		boolean isDelete = false;
		MyNode<MyProcess> temp = processReadyQueue.peek();
		listProcessReady.remove(searchInList(name, listProcessReady));
		if (temp.getData().getName().equals(name)) {
			processReadyQueue.pop();
			isDelete = true;
		} else {
			isDelete = deleteProcess(name, isDelete, temp);
		}
		return isDelete;
	}

	private boolean deleteProcess(String name, boolean isDelete, MyNode<MyProcess> temp) {
		while (temp.getNext() != null) {
			if (temp.getNext().getData().getName().equals(name)) {
				temp.setNext(temp.getNext().getNext());
				isDelete = true;
			} else {
				temp = temp.getNext();
			}
		}
		return isDelete;
	}

	public void searchInQueue(MyProcess process) {
		while (!processReadyQueue.isEmpty()) {

		}
	}

	// Busca el proceso con el que otro proceso se quiera comunicar

	public MyProcess searchProcessReceiver(String nameProcessReceiver) {
		MyProcess aux = null;
		for (int i = 0; i < listProcessReady.size(); i++) {
			if (listProcessReady.get(i).getName().equalsIgnoreCase(nameProcessReceiver)) {
				aux = listProcessReady.get(i);
			}
		}
		return aux;
	}

	// Agrega un proceso a la lista de procesos que un proceso se desea comunicar

	public void addMyProcessComunicate(String nameProcessTransmitter, String nameProcessReceiver) {
		if (nameProcessTransmitter != nameProcessReceiver) {
			for (int i = 0; i < listProcessReady.size(); i++) {
				if (listProcessReady.get(i).getName().equalsIgnoreCase(nameProcessTransmitter)) {
					listProcessReady.get(i).getProcessWithCommunicated().add(searchProcessReceiver(nameProcessReceiver));
				}
			}
		} else {
			System.out.println("el proceso no se puede comunicar con el mismo" + " " + nameProcessTransmitter + " "
					+ nameProcessReceiver);
		}

	}

	public boolean verifyListDestroyed(MyProcess process) {
		for (int i = 0; i < listProcessDestroyed.size(); i++) {
			if (listProcessDestroyed.get(i).getName().equalsIgnoreCase(process.getName())) {
				return true;
			}
		}
		return false;
	}

	// Metodo que verifica si un proceso x se puede comuncar con un proceso y

	public void verifyComunicate(MyProcess process) {
		if (process.getProcessWithCommunicated().size() > 0) {
			if (process.isComunicates() == true && process.getProcessWithCommunicated().get(0).isComunicates() == true/*&& verifyListDestroyed(process.getProcessWithCommunicated().get(0)) == false*/ && process.getProcessWithCommunicated().get(0).getPriority() > process.getPriority()) {
				System.out.println("El proceso: " + process.getName() + " se pudo comunicar con el proceso:"
						+ process.getProcessWithCommunicated().get(0).getName());
			} else {
				System.out.println("El proceso: " + process.getName() + " no se pudo comunicar con el proceso:"+ process.getProcessWithCommunicated().get(0).getName());
			}
		}
	}
	
	public boolean verifyComunicate2(MyProcess process) {
		if (process.getProcessWithCommunicated().size() > 0) {
			if (process.isComunicates() == true && process.getProcessWithCommunicated().get(0).isComunicates() == true/*&& verifyListDestroyed(process.getProcessWithCommunicated().get(0)) == false*/ /*&& process.getProcessWithCommunicated().get(0).getPriority() > process.getPriority()*/) {
				//System.out.println("El proceso: " + process.getName() + " se pudo comunicar con el proceso:"
					//	+ process.getProcessWithCommunicated().get(0).getName());
				return true;
			} else {
				//System.out.println("El proceso: " + process.getName() + " no se pudo comunicar con el proceso:"+ process.getProcessWithCommunicated().get(0).getName());
				return false;
			}
		}
		return false;
	}

	/*public static void main(String[] args) {
		Manager m = new Manager(new Processor(5));
		m.addProcess(new MyProcess("p1", 15, false, false, false, true), 2);
		m.addProcess(new MyProcess("p2", 20, false, false, false, true), 1);
		// m.addProcess(new MyProcess("p3", 13, false, false, true, true), 3);
		// m.addProcess(new MyProcess("p4", 6, false, false, false, true), 4);
		// m.addProcess(new MyProcess("p5", 1, true, false, false, true), 2);

		
		 * m.getProcessReadyQueue().recorrer();
		 * System.out.println("------------------------------"); m.changePriority("p1",
		 * 6); System.out.println("******************************");
		 * 
		 * m.getProcessReadyQueue().recorrer();
		 

		m.covertQueueinArraListForComunicate();

		System.out.println("//////////////////////////////////////////////////////////////");

		m.addMyProcessComunicate("p2", "p1");
		m.addMyProcessComunicate("p1", "p2");
		
		System.out.println();
		// m.addMyProcessComunicate("p1", "p4");
		// m.addMyProcessComunicate("p2", "p3");
		// m.addMyProcessComunicate("p3", "p5");
		// m.addMyProcessComunicate("p3", "p3");
		// m.addMyProcessComunicate("p3", "p4");
		// m.addMyProcessComunicate("p5", "p2");

		m.Simulate();

		m.showProcess();
	}*/

	public ArrayList<MyProcess> getListProcessResume() {
		return listProcessSuspendedToReady;
	}

	public void setListProcessResume(ArrayList<MyProcess> listProcessResume) {
		this.listProcessSuspendedToReady = listProcessResume;
	}

	public ArrayList<MyProcess> getListProcessIsLayOff() {
		return listProcessIsSuspended;
	}

	public void setListProcessIsLayOff(ArrayList<MyProcess> listProcessIsLayOff) {
		this.listProcessIsSuspended = listProcessIsLayOff;
	}

	public ArrayList<MyProcess> getListProcessDiscontinued() {
		return listProcessDiscontinued;
	}

	public void setListProcessDiscontinued(ArrayList<MyProcess> listProcessDiscontinued) {
		this.listProcessDiscontinued = listProcessDiscontinued;
	}

	public ArrayList<MyProcess> getListProcessIsDestroyed() {
		return listProcessIsDestroyed;
	}

	public void setListProcessIsDestroyed(ArrayList<MyProcess> listProcessIsDestroyed) {
		this.listProcessIsDestroyed = listProcessIsDestroyed;
	}

	public ArrayList<MyProcess> getListProcessDestroyed() {
		return listProcessDestroyed;
	}

	public void setListProcessDestroyed(ArrayList<MyProcess> listProcessDestroyed) {
		this.listProcessDestroyed = listProcessDestroyed;
	}

	public ArrayList<MyProcess> getDestroyedTheExecution() {
		return destroyedTheExecution;
	}

	public void setDestroyedTheExecution(ArrayList<MyProcess> destroyedTheExecution) {
		this.destroyedTheExecution = destroyedTheExecution;
	}

	public ArrayList<MyProcess> getListProcessLockedToDestroyed() {
		return listProcessLockedToDestroyed;
	}

	public ArrayList<MyProcess> getListProcessLockedToSuspended() {
		return listProcessLockedToSuspended;
	}

	public ArrayList<MyProcess> getListProcessSuspendedToDestroy() {
		return listProcessSuspendedToDestroy;
	}

	public MyQueue<MyProcess> getProcessReadyAux() {
		return processReadyAux;
	}

	public int getSize() {
		return size;
	}
	
	public ArrayList<MyProcess> getListProcessComunicate() {
		return listProcessComunicate;
	}
	
	
	
	
	

}
