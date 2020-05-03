
public class BSTSet {
	private TNode root;
	
	//OTHER NEEDED
	
	
	/* The following method is used for the constructor, it is basically a sorter which takes the array and sorts it increasing order
	 * and sorts it in increasing order, this is useful for when finding the middle value for the root.*/
	
	public void sorter(int[] input) { 
		int placeholder = 0;
		for (int counter = 0; counter < input.length; counter++) {
			for(int counter2 = counter + 1 ; counter2<input.length; counter2++ ) {
				if(input[counter] > input[counter2]) {
					placeholder = input[counter];
					input[counter] = input[counter2];
					input[counter2] = placeholder;
				}
			}
		}
	}
	
	/* The following method is used to actually create the tree, it first searches for the middle value and sets that as the root.
	 * then using a recursive method it searches the two halves of the array then performs the same operation adding the values
	 * to its right or left accordingly.*/
	
	private TNode newTree (int[] input, int begining, int length) { 
        if (begining > length) {
            return null;
        }
        int middleValue = begining + (length - begining) / 2;
        TNode valueAdd = new TNode(input[middleValue], null, null);
        valueAdd.right = newTree(input, middleValue + 1, length);
        valueAdd.left = newTree(input, begining, middleValue - 1); 
        return valueAdd;
    }
	
	
	/* The following method is used to remove a value from a BST. It first iterates through all the nodes to find where the value is
	 * or if it is even in the set. Then for the three cases of removing a digit from a tree it identifies whihc case it is and removes 
	 * accordingly*/
	
	private TNode remove(TNode placeholder, int toRemove) {
		if (placeholder == null) {
			System.out.println("EMPTY");
		}
		else if (toRemove > placeholder.element) {
			placeholder.right = remove(placeholder.right, toRemove);
        }
		else if (toRemove < placeholder.element) {
			placeholder.left = remove(placeholder.left, toRemove);
        }
		else if(toRemove==placeholder.element) {
			if (placeholder.right!=null && placeholder.left!=null){ //for the case it has two childs
	        	placeholder.element = (low(placeholder.right)).element;
	        	placeholder.right = terminateLow(placeholder.right);
			}
			else { //for the case it has one child
				if(placeholder.left!=null) {
	        		placeholder = placeholder.left;
	        	}
	        	else {
	        		placeholder = placeholder.right;
	        	}
			}
		}
        return placeholder;
	}

	/* The following method is used in the remove method. It's purpose is to remove the lowest value in respect to a node*/
	public TNode terminateLow(TNode placeholder){
		
		if(placeholder.left == null) {
			TNode Soln = placeholder.right;
			return Soln;
		}
		else{
            placeholder.left = terminateLow(placeholder.left);//basically removes the lowest value , keeps going left
            TNode Soln = placeholder; 
			return Soln;
							
        }
            
    }
	
	/* The following method is used in the remove method. Its purpose is tp identify the lowest value in respect to a node.*/
	private TNode low(TNode placeholder) { 
		TNode reference = placeholder;
	    while (placeholder!=null)
	    {
	        if(placeholder.left == null) {
	        	reference = placeholder;  //loop breaks once reaches null and reference will be
	        	break;
	        }
	        placeholder = placeholder.left; //keep going left until null value is found
	    }					
	    return reference;
	}
	
	/* The following method is used in the difference method. It performs the same as inter but opposite */
	private void diff(TNode thisnode, BSTSet s, BSTSet soln) {
		if(thisnode!= null) {
			if(s.isIn(thisnode.element) == false) {
				soln.add(thisnode.element);
			}
			if(thisnode.left != null) {
				this.diff(thisnode.left,s, soln);
			}
			if(thisnode.right != null) {
				this.diff(thisnode.right,s, soln);
			}
		}
	}
	
	
	/* The following method is used in the height method. Using recursive method, it iterates through the various nodes of the tree
	 * obtaining its height */
	private int getHeight(TNode placeholder) {
		TNode reference = placeholder;
		int rightSide = 0;
		int leftSide = 0;
		
		if(placeholder==null) {
			return 0;
		}
		else {
			rightSide = getHeight(reference.right);
			leftSide = getHeight(reference.left);
			
			if(rightSide>leftSide) {
				return rightSide+1;
			}
			else {
				return leftSide+1;
			}
		} 
	}
	
	/* This method is used for the union method. It is basically a full set adder. It adds all values of a set to another set*/
	public void SetAdder(BSTSet s, TNode placeholder){
		if(placeholder!=null){
	           s.add(placeholder.element);
	           if(placeholder.right != null) {
	        	   SetAdder(s, placeholder.right);
	           }
	           if(placeholder.left != null) {
	        	   SetAdder(s, placeholder.left);
	           }
	       }
		
		else {
			return;
			}
		
		}
	
	
	/* This method is used to retrieve the size of the BST. Using recursive method it iterates through all the nodes adding 1 every time
	 * until a null is found.*/
	private int retrieveSize(TNode placeholder) {
		if(placeholder == null) {
			return 0;
		}
		else if(placeholder.right == null && placeholder.left == null) {
			return 1;
		}
		else {
			return retrieveSize(placeholder.left) + retrieveSize(placeholder.right) + 1;
		}
	}
	
	
	
	/* The following method is used for the intersection method. Its purpose is using recursive method to find what two values are 
	 * the same in each of the sets. It first checks if thisnode value is in set s, if it is, it adds it to the new array*/
	private void inter(TNode thisnode, BSTSet s, BSTSet soln) {
		if(thisnode!= null) {
			if(s.isIn(thisnode.element)) {
				soln.add(thisnode.element);
			}
			if(thisnode.left != null) {
				this.inter(thisnode.left,s, soln);
			}
			if(thisnode.right != null) {
				this.inter(thisnode.right,s, soln);
			}
		}
	}
	
	/*CONSTRUCTOR 1 - this just initiazlies a null set*/
	
	public BSTSet() {
		root = null; //initializes null
	}
	
	/*CONSTRUTOR 2 - This constructor is responsible for taking the values of the arrray and converting them into a set
	 * it does this by first obtaining the middle value and setting that as the root. Followed by doing the same for its left and right
	 * side recursively*/
	public BSTSet (int [] input) {
		int newLength = input.length; //sets length of new array with no repeats
		for (int counter = 0; counter < newLength; counter ++) {
			for(int counter2 = counter + 1; counter2< newLength; counter2++) { //begins at the index after counter to check for repetition
				if(input[counter] == input[counter2]) {
					
					int counter3 = counter2 + 1;
					int newPosition = counter2;
					while(counter3 < newLength) {
						input[newPosition] = input[counter3]; //shifts everything one to the left
						counter3++; 						  // this way the repeated element can get removed
					}
					counter3 = counter3 - 1;
					newLength -= 1; //changes size so we know what size the new array has to be
				}
				
			}
		}
		int[] newArray = new int[newLength];
		for (int i = 0; i<newLength; i ++) {
			newArray[i] = input[i]; // initializes new array with no repetition
		}
		sorter(newArray);
		root = newTree(newArray, 0, newLength-1);
	}
	

	/*IS IN METHOD - this method locates whether or not an integer is within the set. it does this by starting at the root
	 * and going right or left according to the size of the integer that is being searched for. */
	public boolean isIn(int v) {
		if (root != null) {
			TNode reference = root; //initialize variable at root so can iterate through
			while (reference != null) {
				if (reference.element == v) { //number found
					return true;
				}
				else if(v > reference.element) { //if its greater go right
					reference = reference.right;
				}
				else if(v < reference.element){ //if its less go left
					reference = reference.left;
				}
				else {
					return false; //number not found
				}
			}
			return false;
		}
		return false;
	}
	

	/*ADD METHOD - The purpose of this method is to add an integer to set. First it checks to see if the integer is already in the set,
	 * if it is it wont add it again. If it is not found, by using comparison of int t locates where the new number should be placed */
	public void add(int v) {
		if (root!= null) {
			TNode reference = root;
			while(reference!=null) {
				if(v < reference.element) { //if v is smaller then postion
					if(reference.left==null) {//if spot on the left is empty place int there
						reference.left = new TNode (v, null, null);
						reference = null;  
					}
					else { //else just go left
						reference = reference.left;
					}
				}
				else if(v > reference.element) {//if v is greater than position
					if(reference.right==null) { //if the spot to the right is empty place the int there
						reference.right = new TNode(v, null, null);
						reference = null;  
					}
					else { // else just go right
						reference = reference.right;
					}
				}
				else if(reference.element == v) { //if v == position
					return; //if the int is already in the array do noting
				}
			}
		}
		else {
			root = new TNode(v, null, null); //root was null, meaning set doesn't exist
		}
	}
	
	
	
	/*REMOVE METHOD - this method calls the remove method created above */
	public boolean remove(int v) {
		if (isIn(v)==false) {
			return false;
		}
		else {
			root = remove(root, v);
			return true;
		}
		
	}

	/*UNION METHOD - the purpose of this method is to find the union of two sets, the function does this by using set adder
	 * which compleetelys adds the value of two sets.*/
	public BSTSet union(BSTSet s) {
		if(this.root == null && s.root!=null) {
			return s;
		}
		else if(this.root != null && s.root==null) {
			return this;
		}
		else {
			BSTSet soln = new BSTSet();
			SetAdder(soln, this.root);
			SetAdder(soln, s.root);
			return soln;
		}
	}

	
	/*INTERSECTION - the purpose of this method is to find the intersection of two sets. It does ths by using the method created above */
	public BSTSet intersection(BSTSet s){
		BSTSet soln = new BSTSet();
		TNode thisRoot = this.root;
		TNode sRoot = s.root;
		if (this.root == null || s.root==null) {
			return soln;
		}
		else if(thisRoot.right == null && thisRoot.left == null && sRoot.right == null && sRoot.left == null) {
			if(thisRoot.element == sRoot.element) {
				soln.add(thisRoot.element);
				return soln;
			}
			else {
				return soln;
			}
		}
		else {
			inter(thisRoot, s, soln);
			return soln;
		}
	}

	
	/*DIFFERENCE METHOD - finds what is in A but not in B, it relies heavily on the same algorithm as the difference method*/
	public BSTSet difference(BSTSet s) {
		BSTSet soln = new BSTSet();
		TNode thisRoot = this.root;
		TNode sRoot = s.root;
		if (this.root == null && s.root==null) {
			return soln;
		}
		else if(thisRoot.right == null && thisRoot.left == null && sRoot.right == null && sRoot.left == null) {
			if(thisRoot.element != sRoot.element) {
				soln.add(thisRoot.element);
				return soln;
			}
			else {
				return soln;
			}
		}
		else {
			diff(thisRoot, s, soln);
			return soln;
		}
	}
	
	
	/*HEIGHT METHOD - first checks the two cases as seen below, if it deos not fall under those cases it uses getHeight method. Which 
	 * basically iterates through every node adding 1 every time it goes right or left */
	public int height() {
		if(root==null) {
			return -1;
		}
		else if(root.left == null && root.right == null) {
			return 0;
		}
		else {
			return this.getHeight(this.root);
		}
		
	}
	

	
	/*Size method - calls the retrieve size method, which basically just iterates through every node in the tree
	 * and keeps adding 1 until null value is found */
	public int size() {
		return this.retrieveSize(this.root);
	}
	
	
	
	//PRINT METHOD
	public void printBSTSet(){
		if(root == null) {
			System.out.println("The set is empty");
		}
		else {
			System.out.println("The Elements of the set are:");
			printBSTSet(root);
			System.out.println("\n");
		}
		
	}
	private void printBSTSet(TNode t){
		if(t!=null){
			printBSTSet(t.left);
			System.out.print(t.element + ", ");
			printBSTSet(t.right);
			}
		}
	
	
	/* This is a nonrec printing method. What it does is first adds all values on the left side to the stack and then pops them, then 
	 * it iterates through the right side adn prints all elements out*/
	public void printNonRec() {
		if(root == null) {
			throw new IllegalArgumentException("Empty");
		}
		else {
			newStack mystack = new newStack();
			TNode temp = root;
			
			while(temp !=null) {
				mystack.push(temp);
				temp = temp.left;
			}
			
			while(mystack.Exists() == true) {
				temp =  mystack.pop();
				System.out.print(temp.element + ", ");
				
				if (temp.right!=null) {
					temp = temp.right;
					
					while(temp !=null) {
						mystack.push(temp);
						temp = temp.left;
					}
				}
			}
		}
		
	}
	
}



/*BSTSet() - The first constructor has a time complexity of just 1. This is because it only creates an empty set.
 * 
 *BSTSet(int[] input) - this constructor has a time complexity n^2, as it uses a double nested for loop, it then 
 *calls the sorter and new tree method are non recursive and have a lower complexity thus O(n^2) is the time complexity
 * 
 *isIn(int v) - The complexity of this method depends on the size of the set. Best case is O(1) worst case is O(n) 
 *
 * add(int v) - the complexity of this function depends on the size, once the position is located it will add the value there.
 * best case is O(1) worst case is O(n)
 * 
 * remove (int v) - this method first finds out whether or not v is in the set or not. If it is it calls the recrusive method 
 * remove. That method will then locate the position of v, worst case being O(n) and best case being O(1). that function also 
 * low and terminate low. terminate low, will run reccrusively depending on how many nodes are after the node of the located element.
 * while low will run a finite number of times
 * 
 * union (BSTSet s) - running time is dependednt on the size of the sets and whether or not a set is empty. If none are empty then 
 * it calls setadder which has a complexity of O(n).
 * 
 * intersection(BSTSet s) - this method first checks the two conditions below and returns an answer arrodingly. if none of 
 * those conditions are met then it calls inter, whihc has a complexity of O(n) which is becasue it iterates through all the set nodes
 *  
 *  
 * difference(BSTSet s) - same complexity as intersection it uses a similar algorithm  
 * 
 * height() - first checks two cases below otehr wise uses a recursive method, which iterates n times
 * 
 * */
