package gen;
import java.util.HashMap;


public  class Module {

	static String llvm_output="";
	static HashMap<String, String> context = new HashMap<>(); // String funktionsmname String inhalt(llvm)
	
	
	//TODO Stringbuilder
	static void builder(String str){
		StringBuilder sb = new StringBuilder();
		sb.append(llvm_output).append(str);
		llvm_output = sb.toString();
	}
}
