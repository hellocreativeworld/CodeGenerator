package inter;


import CPP.Absyn.PDefs;
import CPP.Absyn.Program;
import CPP.Absyn.Program.Visitor;

public class CheckProgram implements Visitor<Program, TypeCode> {

	@Override
	public Program visit(PDefs p, TypeCode arg) {
		System.out.println("PDef");
		
		for(int i=0; i<p.listdef_.size(); i++){
		
			Interpreter.eval(p.listdef_.get(i));
			Env.clearTable();
			
		}
		 // here list of definitions fetchen
		
		return null;
	}

}
