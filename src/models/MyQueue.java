package models;

public class MyQueue<T> {

	private MyNode<T> firstNode;
	private MyNode<T> node;
	
	public MyQueue() {
		this.firstNode = null;
	}
	
	public int size() {
		int size = 0;
		MyNode<T> actualNode = firstNode;
		while (actualNode != null) {
			size++;
			actualNode = actualNode.getNext();
		}
		return size;
	}

	public void recorrer() {
		MyNode<T> temp = firstNode;
		while (temp != null) {
			System.out.println(temp.getData() + " " + temp.getPriority());
			temp = temp.getNext();
		}
	}

	public void push(T data, int priority) {
		MyNode<T> temp = new MyNode<T>(data, priority);
		if (firstNode == null) {
			firstNode = temp;
		} else if (firstNode.getPriority() > priority) {
			MyNode<T> aux = firstNode;
			firstNode = temp;
			firstNode.setNext(aux);
		} else {
			pushPriority(temp);
		}

	}

	private void pushPriority(MyNode<T> temp) {
		MyNode<T> tempNode = firstNode;
		while (tempNode != null) {
			if (tempNode.getPriority() > temp.getPriority()) {
				temp.setNext(tempNode);
				searchDad(tempNode).setNext(temp);
				tempNode = null;
			}else if (tempNode.getNext() == null) {
				tempNode.setNext(temp);
				tempNode = null;
			}else {
				tempNode = tempNode.getNext();
			}
		}
	}

	private MyNode<T> searchDad(MyNode<T> node) {
		MyNode<T> tempNode = firstNode;
		while (tempNode != null) {
			if (tempNode.getNext().equals(node)) {
				return tempNode;
			}
			tempNode = tempNode.getNext();
		}
		return null;
	}
	
	/*private MyNode<T> validePushByPriority(MyNode<T> temp, MyNode<T> tempNode) {
		if (tempNode.getNext().getPriority() > temp.getPriority()) {
			tempNode = pushByPriorty(temp, tempNode);
		} else {
			tempNode = valideFinishQueueProprity(temp, tempNode);
		}
		return tempNode;
	}*/

	/*private MyNode<T> pushByPriorty(MyNode<T> temp, MyNode<T> tempNode) {
		temp.setNext(tempNode.getNext());
		tempNode.setNext(temp);
		tempNode = null;
		return tempNode;
	}*/

	/*private MyNode<T> valideFinishQueueProprity(MyNode<T> temp, MyNode<T> tempNode) {
		tempNode = tempNode.getNext();
		if (tempNode.getNext() == null) {
			tempNode.setNext(temp);
			tempNode = null;
		}
		return tempNode;
	}*/

	public boolean isEmpty() {
		return firstNode == null;
	}

	public MyNode<T> peek() {
		return firstNode;
	}

	public T pop() {
		MyNode<T> tempNode = firstNode;
		firstNode = firstNode.getNext();
		return tempNode.getData();
	}

	public MyNode<T> getNode() {
		return node;
	}

	public void setNode(MyNode<T> node) {
		this.node = node;
	}

}