public class newStack {
	private stackNode topValue;
	
	
	public void push(TNode value) {
		topValue = new stackNode(value, topValue);
	}
	
	public boolean Exists() {
		if(topValue == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public TNode pop() {
		if(Exists() == true) {
			TNode value = topValue.value;
			topValue = topValue.next;
			return value;
		}
		else {
			throw new IllegalArgumentException("NO values");
		}
	}
   
}
