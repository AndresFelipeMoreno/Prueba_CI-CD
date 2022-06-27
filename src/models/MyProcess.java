package models;

import java.util.ArrayList;

public class MyProcess implements Comparable<MyProcess>{
	
	private String name;
	private int timeProcess;
	private int priority = 0;
	private boolean isBlock;
	//layOff = SUSPENDER
	private boolean isLayOff;
	private boolean isDestroy;
	private boolean comunicates;
	private ArrayList<MyProcess> processWithCommunicated;
	
	public MyProcess(String name,int timeProcess,boolean isBlock, boolean isLayOff, boolean isDestroy ,boolean comunicates) {
		this.name = name;
		this.timeProcess = timeProcess;
		this.isBlock = isBlock;
		this.isLayOff = isLayOff;
		this.isDestroy = isDestroy;
		this.comunicates = comunicates;
		processWithCommunicated = new ArrayList<>();
	}
	
	public MyProcess(String name,int timeProcess,int priority,boolean isBlock, boolean isLayOff, boolean isDestroy, boolean comunicates) {
		this.name = name;
		this.timeProcess = timeProcess;
		this.priority = priority;
		this.isBlock = isBlock;
		this.isLayOff = isLayOff;
		this.isDestroy = isDestroy;
		this.comunicates = comunicates;
		processWithCommunicated = new ArrayList<>();
	}
	
	public int getTimeProcess() {
		return timeProcess;
	}
	public void setTimeProcess(int timeProcess) {
		this.timeProcess = timeProcess;
	}
	public boolean isBlock() {
		return isBlock;
	}
	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + getName() + "Tiempo: " + getTimeProcess() + "Estado: " + isBlock();
	}

	@Override
	public int compareTo(MyProcess o) {
		return getPriority() - o.priority;
	}

	public boolean isLayOff() {
		return isLayOff;
	}

	public void setLayOff(boolean isLayOff) {
		this.isLayOff = isLayOff;
	}

	public ArrayList<MyProcess> getProcessWithCommunicated() {
		return processWithCommunicated;
	}

	public void setProcessWithCommunicated(ArrayList<MyProcess> processWithCommunicated) {
		this.processWithCommunicated = processWithCommunicated;
	}

	public boolean isDestroy() {
		return isDestroy;
	}

	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}

	public boolean isComunicates() {
		return comunicates;
	}

	public void setComunicates(boolean comunicates) {
		this.comunicates = comunicates;
	}

}
