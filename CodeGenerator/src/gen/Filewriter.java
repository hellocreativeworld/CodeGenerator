package gen;

import java.io.File;
import java.io.IOException;

/**
 * suppose to write the file that is then executed with jasmin
 * 
 * 
 * @author soenke
 *
 */
public class Filewriter {
	
	public File jasminfile; 
	
 public Filewriter(){
	 jasminfile= new File("jasminfile.txt");
	 try {
		jasminfile.createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
 }
 
 
 /**
  * write file dismiss constructor maybe?
  * @param somekindofString
  */
 public void writeFile(String somekindofString){
	 
 }
}
