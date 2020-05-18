import java.util.*;

public class ClauseSolver {
	
	public Vector<Clause> clausesVec = new Vector<Clause>();
	
	//Only sen in one string that is out clause, 
	//then create a new clause and put it in a vector of clauses.
	ClauseSolver(String[] inputString){
		
		for(int i =0; i < inputString.length; i++) {
			Clause temp = new Clause(inputString[i]);
			
			if(!temp.isEmpty()) {
				clausesVec.addElement(temp);
				
			}
				
				
		}	
	}
	
	public Clause Resolution(Clause A, Clause B) {
		
		String snitt = null;
		Clause resultClause = new Clause();
		
		Vector<String> An = A.neg;
		Vector<String> Ap = A.pos;
		Vector<String> Bn = B.neg;
		Vector<String> Bp = B.pos;
		
		for(int i = 0; i < Ap.size(); i++) {
			if(Bn.contains(Ap.get(i)))
				snitt = Ap.get(i);
		}
		for(int i = 0; snitt == null && i < Bp.size(); i++) {
			if(An.contains(Bp.get(i)))
				snitt=Bp.get(i);
		}
		
		
		
		if(snitt != null) {
			for(int i = 0; i < Ap.size();i++) {
				if(!Ap.get(i).equals(snitt) && !resultClause.pos.contains(Ap.get(i)))
						resultClause.pos.add(Ap.get(i));
			}
			for (int i = 0; i < An.size(); i++) {
				if (!An.get(i).equals(snitt) && !resultClause.neg.contains(An.get(i)))
					resultClause.neg.add(An.get(i));
			}
			for (int i = 0; i < Bp.size(); i++) {
				if (!Bp.get(i).equals(snitt) && !resultClause.pos.contains(Bp.get(i)))
					resultClause.pos.add(Bp.get(i));
			}
			for (int i = 0; i < Bn.size(); i++) {
				if (!Bn.get(i).equals(snitt) && !resultClause.neg.contains(Bn.get(i)))
					resultClause.neg.add(Bn.get(i));
			}
		}		
		else {
			return null;
		}
				
		
		if(snitt != null && resultClause.isContradictory()) {
			return null;
		}
		
		return resultClause;
	}
	
	public void Solver() {
		
		Clause C;
		//Vector<Clause> S = new Vector<Clause>();
		Vector<Clause> KB = new Vector<Clause>();
		Vector<Clause> S;
			
		do {
			
			C = new Clause();
			S = new Vector<Clause>();
			KB = clausesVec;
			
			for(int i = 0; i < clausesVec.size()-1;i++) {
				for(int j = i+1; j < clausesVec.size(); j++) {;
					C = Resolution(clausesVec.get(i), clausesVec.get(j));		
					if(C!=null) {
						S.addElement(C);
					}		
				}
			}

			if(S.isEmpty())
				break;
			
			clausesVec = Incorporate(S);	
			
		}while(clausesVec != KB);
		
		clausesVec = Incorporate(S);
	
		
		System.out.println("Solved");
		for(int i = 0; i < clausesVec.size(); i++) {
			if(clausesVec.get(i)!=null && !clausesVec.get(i).isEmpty()) {
				clausesVec.get(i).printClause();
			
			}
		}
		
	}
	
	public Vector<Clause> Incorporate(Vector<Clause> KB){
		
		
		for(int i = 0; i < clausesVec.size();i++) {

			KB = Incorporate_clause(clausesVec.get(i), KB);
			
		}
		
		return KB;
		
	}
	
	public Vector<Clause> Incorporate_clause(Clause A, Vector<Clause> KB)
	{
		for(int i = 0; i < KB.size(); i++) {
			if(KB.get(i).testSubset(A)) {
				return KB;
				
			}
		}
		
		for(int i = 0; i < KB.size(); i++) {
		
			if(A.testSubset(KB.get(i))) {
				KB.remove(KB.get(i));
			}
				
		}
		
		KB.add(A);
		
		return KB;
		
	}
	
	
	
	

}
