package Auto_BSA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class Reader {
	
	private static String Path;
	
	public Reader(String P) throws FileNotFoundException{
		Path = P;
	}

	//enum type  {forcycle,whilecycle,dowhilecycle,Block,InOut,IfRhombus};

    //Reads file and reterns String array interpretation of it.	
    public static String[] read() throws FileNotFoundException {
		Scanner fin = new Scanner(new FileReader(Path));
		String line;
		LinkedList<String> crutch = new LinkedList<String>();
		while(fin.hasNextLine()){
			line = fin.nextLine();
			if(line.length()>0){
				crutch.add(line);
			}
		}
		
		String[] ret = crutch.toArray(new String[crutch.size()]);
		return isComment(ret);	
	}
   
	//Deletes all comments from String array interpretation of file.
	private static String[] isComment(String[] ret) {
		for (int i = 0;i<ret.length;i++) {
				if (ret[i].contains("//")) {
					//if(line.subSequence(0, line.indexOf("//")).length()!=0)
					ret[i] = (String) ret[i].subSequence(0, ret[i].indexOf("//"));		
				}
			    if (ret[i].contains("/*")) {
			    	if (ret[i].contains("*/")) {
			    		ret[i] = (String) ret[i].subSequence(0, ret[i].indexOf("/*")) + (String) ret[i].subSequence( ret[i].indexOf("*/")+2, ret[i].length());	
			    	} else {
				    	ret[i] = (String) ret[i].subSequence(0, ret[i].indexOf("/*"));
				    	for (int j = i+1;j<ret.length;j++) {
				    		if (!ret[j].contains("*/")) {
				    			ret[j]="";
				    		} else {
				    			ret[j]=(String)ret[j].subSequence(ret[j].indexOf("*/")+2, ret[j].length());
				    			break;
				    		}
				    	}
			    	}
			    }
	
		}
		return ret;
	}
	 
//converts from string array to the one line
	public String OneStringConverter(String[] ret){
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < ret.length; i++) {
			if(ret[i].length()>0){
				line.append(ret[i] + '\n');
			}
		}
		return line.toString();
	}
}



