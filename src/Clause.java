import java.util.Vector;

public class Clause {
	
	public Vector<String> pos = new Vector<String>();
	public Vector<String> neg = new Vector<String>();
	
	Clause(){}
	
	Clause(String inputString){
		
		String[] splittedString;
		
		inputString = inputString.replaceAll(" ", "");
		inputString = inputString.replaceAll("V", " ");
		
		splittedString = inputString.split(" ");
		
		
		for(int i = 0; i < splittedString.length; i++) {
			
			if(splittedString[i].contains("-")) {
				String temp = splittedString[i];
				temp = temp.replaceAll("-", "");
				neg.addElement(temp);
			}else {
				
				pos.addElement(splittedString[i]);
			}
			
		}
	}
	
	
	public void removeDuplicates() {
		
		for(int i =0; i < neg.size();i++) {
			for(int j = 0; j <pos.size(); j++) {
				if(neg.get(i).equals(pos.get(j))) {
					neg.removeElement(i);
					pos.removeElement(j);
				}
			}
		}
		
	}
	
	public boolean isContradictory(){
		
		for(int i=0; i<pos.size(); i++){
			for(int j=0; j<neg.size(); j++){
				
				if(pos.get(i).equals(neg.get(j)))
					return true;
				
			}
		}
		
		return false;
	}

    public boolean isEmpty() {
	    return (neg.size() + pos.size()) == 0;
    }

	public boolean testSubset(Clause inputClause) {
		
		for(int i = 0; i < pos.size(); i++) {
			if(inputClause.pos.contains(pos.get(i)))
				return true;
		}
		for(int i = 0; i < neg.size(); i++) {
			if(inputClause.neg.contains(neg.get(i)))
				return true;
		}
				return false;
		
	}
	
	public void printClause() {
		
		System.out.print("[");

		if(neg.size() > 0){
			for(int i=0; i<neg.size(); i++){
				
				System.out.print("-"+neg.elementAt(i));
				if(i != neg.size()-1)
					System.out.print(" V ");
			}
		}
		
		if(pos.size() > 0 && neg.size() > 0)
			System.out.print(" V ");
		
		if(pos.size() > 0){
			for(int i=0; i<pos.size(); i++){
				System.out.print(pos.elementAt(i));
				if(i != pos.size()-1)
					System.out.print(" V ");
			}
		}
		System.out.print("] \n");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((neg == null) ? 0 : neg.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		return result;
	}
	
	/*
	// We need an override function for equals
		@Override
		public boolean equals(Object inputObject) {
			
			if (this == inputObject)
				return true;
			if (inputObject == null)
				return false;
			if (getClass() != inputObject.getClass())
				return false;

			Clause tempClause = (Clause) inputObject;

			if (neg == null) {
				if (tempClause.neg != null)
					return false;
			} else if (!neg.equals(tempClause.neg))
				return false;
			if (pos == null) {
				if (tempClause.pos != null)
					return false;
			} else if (!pos.equals(tempClause.pos))
				return false;
			return true;
		}*/
}


