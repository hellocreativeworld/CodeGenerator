package inter;

import CPP.Absyn.EAnd;
import CPP.Absyn.EApp;
import CPP.Absyn.EAss;
import CPP.Absyn.EDecr;
import CPP.Absyn.EDiv;
import CPP.Absyn.EDouble;
import CPP.Absyn.EEq;
import CPP.Absyn.EFalse;
import CPP.Absyn.EGt;
import CPP.Absyn.EGtEq;
import CPP.Absyn.EId;
import CPP.Absyn.EIncr;
import CPP.Absyn.EInt;
import CPP.Absyn.ELt;
import CPP.Absyn.ELtEq;
import CPP.Absyn.EMinus;
import CPP.Absyn.ENEq;
import CPP.Absyn.EOr;
import CPP.Absyn.EPDecr;
import CPP.Absyn.EPIncr;
import CPP.Absyn.EPlus;
import CPP.Absyn.EString;
import CPP.Absyn.ETimes;
import CPP.Absyn.ETrue;
import CPP.Absyn.ETyped;
import CPP.Absyn.Exp.Visitor;
import CPP.Absyn.Type;
/**
 * this class is supposed to be the main exp handling stuff so far
 * TODO all the errors occou  coz it  is copy paste from the type checker
 * @author soenke
 *
 */
public class InferExp implements Visitor<Type, Env>{

	@Override
	public Type visit(ETrue p, Env arg) {
		// TODO Auto-generated method stub
		return new TBool();
	}

	@Override
	public Type visit(EFalse p, Env arg) {
		// TODO Auto-generated method stub
		return new TBool();
	}

	@Override
	public Type visit(EInt p, Env arg) {
		// TODO Auto-generated method stub
		return new TInt();
	}

	@Override
	public Type visit(EDouble p, Env arg) {
		// TODO Auto-generated method stub
		return new TDouble();
	}

	@Override
	public Type visit(EString p, Env arg) {
		// TODO Auto-generated method stub
		return new TString();
	}

	@Override
	public Type visit(EId p, Env arg) {
		for(int i =0; i<Env.contexts.size(); i++){
			if(Env.contexts.get(i).containsKey(p.id_)){
				return CodeType(Env.lookupVar(p.id_));
			}
		}
		System.out.println("ERROR Type missmatch @ " + p.id_ +" is not defined yet");
		System.exit(-1);
		return null; 
		
	}

	@Override
	public Type visit(EApp p, Env arg) {
		for(int i =0; i< Env.contexts.size(); i++){
			if(Env.contexts.get(i).containsKey(p.id_)){
				String s= Env.contexts.get(i).toString();
				Interpreter.eval(p.listexp_.get(i));
				return CodeType(Env.extractValue(s));
			}
		}
		return null;
	}

	@Override
	public Type visit(EPIncr p, Env arg) {
		if(typeCode(p.exp_.accept(this, arg))!= TypeCode.CInt){
			System.out.println("Error Type missmatch @ Incr");
			System.exit(-1);
			
		}
		return null;
	}

	@Override
	public Type visit(EPDecr p, Env arg) {
		if(typeCode(p.exp_.accept(this, arg))!= TypeCode.CInt){
			System.out.println("Error Type missmatch @ EPDEct");
			System.exit(-1);
			
		}
		return null;
	}

	@Override
	public Type visit(EIncr p, Env arg) {
		if(typeCode(p.exp_.accept(this, arg))!= TypeCode.CInt){
			System.out.println("Error Type missmatch @ EIncr");
			System.exit(-1);
			
		}
		return null;
	}

	@Override
	public Type visit(EDecr p, Env arg) {
		if(typeCode(p.exp_.accept(this, arg))!= TypeCode.CInt){
			System.out.println("Error Type missmatch @ EDEcr");
			System.exit(-1);
			
		}
		return null;
	}

	@Override
	public Type visit(ETimes p, Env arg) {
		Type t1 = p.exp_1.accept(this, arg);
		Type t2 = p.exp_2.accept(this, arg);
		if(typeCode(t1)== TypeCode.CInt && typeCode(t2) == TypeCode.CInt){
			return new TInt();
		}else if(typeCode(t1)== TypeCode.CDouble && typeCode(t2) == TypeCode.CDouble){
			return new TDouble();
		}		
		else {
			System.out.println("Error Type Missmatch @ ETimes");
			System.exit(-1);
			return null;
		}
	}

	@Override
	public Type visit(EDiv p, Env arg) {
		Type t1 = p.exp_1.accept(this, arg);
		Type t2 = p.exp_2.accept(this, arg);
		if(typeCode(t1)== TypeCode.CInt && typeCode(t2) == TypeCode.CInt){
			return new TInt();
		}else if(typeCode(t1)== TypeCode.CDouble && typeCode(t2) == TypeCode.CDouble){
			return new TDouble();
		}		
		else {
			System.out.println("Error Type Missmatch @ EDiv");
			System.exit(-1);
			return null;
		}
	}

	@Override
	public Type visit(EPlus p, Env arg) {
		Type t1 = p.exp_1.accept(this, arg);
		Type t2 = p.exp_2.accept(this, arg);
		if(typeCode(t1)== TypeCode.CInt && typeCode(t2) == TypeCode.CInt){
			return new TInt();
		}else if(typeCode(t1)== TypeCode.CDouble && typeCode(t2) == TypeCode.CDouble){
			return new TDouble();
		}		
		else {
			System.out.println("Error Type Missmatch @ Eplus");
			System.exit(-1);
		}
		return null;
	}

	@Override
	public Type visit(EMinus p, Env arg) {
		Type t1 = p.exp_1.accept(this, arg);
		Type t2 = p.exp_2.accept(this, arg);
		if(typeCode(t1)== TypeCode.CInt && typeCode(t2) == TypeCode.CInt){
			return new TInt();
		}else if(typeCode(t1)== TypeCode.CDouble && typeCode(t2) == TypeCode.CDouble){
			return new TDouble();
		}		
		else {
			System.out.println("Error Type Missmatch @ EMinus");
			System.exit(-1);
		}
		return null;
	}

	@Override
	public Type visit(ELt p, Env arg) {
	if(typeCode(p.exp_1.accept(this, arg)) !=  typeCode( p.exp_2.accept(this, arg))){
			
			System.out.println("Error Type Missmatch @ ELt");
			System.exit(-1);
			
		}
		return new TBool();
	}

	@Override
	public Type visit(EGt p, Env arg) {
	if(typeCode(p.exp_1.accept(this, arg)) !=   typeCode(p.exp_2.accept(this, arg))){
			
			System.out.println("Error Type Missmatch @ EGt");
			System.exit(-1);
			
		}
		return new TBool();
		
	}

	@Override
	public Type visit(ELtEq p, Env arg) {
	if(typeCode(p.exp_1.accept(this, arg)) !=   typeCode(p.exp_2.accept(this, arg))){
			
			System.out.println("Error Type Missmatch @ ELtEq");
			System.exit(-1);
			
		}
		return new TBool();
		
	}

	@Override
	public Type visit(EGtEq p, Env arg) {
	if(typeCode(p.exp_1.accept(this, arg)) !=   typeCode(p.exp_2.accept(this, arg))){
			
			System.out.println("Error Type Missmatch @ EGtEq");
			System.exit(-1);
			
		}
		return new TBool();
		
	}

	@Override
	public Type visit(EEq p, Env arg) {
		if(typeCode(p.exp_1.accept(this, arg)) !=   typeCode(p.exp_2.accept(this, arg))){
			
			System.out.println("Error Type Missmatch @ EEq");
			System.exit(-1);
			
		}
		return new TBool();
	}

	@Override
	public Type visit(ENEq p, Env arg) {
	if(typeCode(p.exp_1.accept(this, arg)) !=   typeCode(p.exp_2.accept(this, arg))){
			
			System.out.println("Error Type Missmatch @ ENEq");
			System.exit(-1);
			
		}
		return new TBool();
	}

	@Override
	public Type visit(EAnd p, Env arg) {
		Type t1 = p.exp_1.accept(this, arg);
		Type t2 = p.exp_2.accept(this, arg);
		if(typeCode(t1)== TypeCode.CBool && typeCode(t2) == TypeCode.CBool){
			return new TBool();
		}		
		else {
			System.out.println("Error Type Missmatch @ EAnd");
			System.exit(-1);
			return null;
		}
		
	}

	@Override
	public Type visit(EOr p, Env arg) {
		Type t1 = p.exp_1.accept(this, arg);
		Type t2 = p.exp_2.accept(this, arg);
		if(typeCode(t1)== TypeCode.CBool && typeCode(t2) == TypeCode.CBool){
			return new TBool();
		}		
		else {
			System.out.println("Error Type Missmatch @ EOr");
			System.exit(-1);
		}
		return null;
	}

	@Override
	public Type visit(EAss p, Env arg) {
		if(typeCode(p.exp_1.accept(this, arg)) !=   typeCode(p.exp_2.accept(this, arg))){
		
			System.out.println("Error Type Missmatch @ EAss");
			System.exit(-1);
		}
		return null;
	}

	@Override
	public Type visit(ETyped p, Env arg) {
		
		return null;
	}
	
	public static TypeCode typeCode(Type ty){
	 try{
//		if(ty.getClass().equals(TBool.class.getClass()))return TypeCode.CBool;  //1
//		if(ty.getClass().equals(TBool.class.getClass()))return TypeCode.CString;
//		if(ty.getClass().equals(TBool.class.getClass()))return TypeCode.CInt;
//		if(ty.getClass().equals(TBool.class.getClass()))return TypeCode.CDouble;
//		if(ty.getClass().equals(TBool.class.getClass()))return TypeCode.CVoid;
		System.out.println(ty.getClass().getName());
		if(ty.getClass().getName().equals("main.TBool"))return TypeCode.CBool;//2
		if(ty.getClass().getName().equals("main.TString"))return TypeCode.CString;
		if(ty.getClass().getName().equals("main.TInt"))return TypeCode.CInt;
		if(ty.getClass().getName().equals("main.TDouble"))return TypeCode.CDouble;
		if(ty.getClass().getName().equals("main.TVoid"))return TypeCode.CVoid;
	 }catch(Exception e){
		 System.out.println("here " + e.getMessage()+ "-Pointer Ex BEcause i dont know how to Encode the functioncall");
	 }
//		if(ty.getClass().isAssignableFrom(TBool.class.getClass()))return TypeCode.CBool;//3
//		if(ty.getClass().isAssignableFrom(TString.class.getClass()))return TypeCode.CString;
//		if(ty.getClass().isAssignableFrom(TInt.class.getClass()))return TypeCode.CInt;
//		if(ty.getClass().isAssignableFrom(TDouble.class.getClass()))return TypeCode.CDouble;
//		if(ty.getClass().isAssignableFrom(TVoid.class.getClass()))return TypeCode.CVoid;
		/*else{
			System.out.println("ERROR Type was not found");
			System.exit(-1);
			return null;
		}*/
		return null;
	}
	public static Type CodeType(TypeCode t){
		if(t==TypeCode.CBool)return new TBool();
		if(t==TypeCode.CInt)return new TInt();
		if(t==TypeCode.CDouble)return new TDouble();
		if(t==TypeCode.CString)return new TString();
		if(t==TypeCode.CVoid)return new TVoid();
		else{
			System.out.println("ERROR Type was not found");
			System.exit(-1);
			return null;
		}
	}

}
