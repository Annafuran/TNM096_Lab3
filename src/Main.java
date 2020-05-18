import java.util.Vector;

public class Main {
	
	public static void main(String[] args) {
		
		
		String[] Bob = {"-sun V -money V ice", "-money V ice V movie", "-movie V money", "-movie V -ice", "movie"};
		ClauseSolver theClauseSolver = new ClauseSolver(Bob);
		theClauseSolver.Solver();
		
		//Should return a V b
		String a = "a V b V -c" ;
		String b = "c V b";
		
		//Should return false
		//String a = "a V b V -c";
		//String b = "d V b V -g";
		
		//Should return false
		//String a =  "-b V c V t";
		//String b = 	"-c V z V b";
		
		Clause A_clause = new Clause(a);
		Clause B_clause = new Clause(b);
		
		Clause result = Resolution(A_clause, B_clause);
		
		if(result != null)
		result.printClause();

			
	}
	
	public static Clause Resolution(Clause A, Clause B) {
		
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
			System.out.print("False");
			return null;
		}
				
		
		if(resultClause.isContradictory() && snitt != null) {
			System.out.print("False");
			return null;
		}
			
		return resultClause;	
	}
	

}
