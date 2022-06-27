package models;

public class MyNode<T> {

	private T data;
	private MyNode<T> next;
	private int priority;

	public MyNode(T data, int priority) {
		this.data = data;
		this.priority = priority;
	}
	
	public MyNode() {
		
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public MyNode<T> getNext() {
		return next;
	}

	public void setNext(MyNode<T> next) {
		this.next = next;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}