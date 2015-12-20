package Auto_BSA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;


public class Reader {
	//путь к файлу
	private static String Path;
	//конструктор
	public Reader(String P) throws FileNotFoundException{
		Path = P;
	}

	// enum type  {forcycle,whilecycle,dowhilecycle,Block,InOut,IfRhombus};
	public static String[] read() throws FileNotFoundException {
		Scanner fin = new Scanner(new FileReader(Path));
		String line;
		LinkedList<String> crutch = new LinkedList<String>();
		while(fin.hasNextLine()){
			line = fin.nextLine();
			if(!(line.equals(""))) {
				if(line.length()>1){
					if(((line.charAt(0)!='/')&&(line.charAt(1)!='/'))&&(line.charAt(0)!='#')){
						crutch.add(line);
					}
				}
			}
		}
		String[] ret = crutch.toArray(new String[0]);
		return Analyzer(ret);
		
	}

	public static String[] Analyzer(String[] arstr) {
		//int BracketsOpen = 0, BracketsClosed = 0;
		LinkedList<String> crutch = new LinkedList<String>();
		for (int i = 0; i < arstr.length; i++) {
		 		  
		}
		String[] ret = crutch.toArray(new String[0]);
		return ret;	
	}
	private static String[] isComment(String[] line) {
		for (int i = 0;i<line.length;i++) {
				if (line[i].contains("//")) {
					//if(line.subSequence(0, line.indexOf("//")).length()!=0)
					line[i] = (String) line[i].subSequence(0, line[i].indexOf("//"));		
				}
			    if (line[i].contains("/*")) {
			    	if (line[i].contains("*/")) {
			    		line[i] = (String) line[i].subSequence(0, line[i].indexOf("/*")) + (String) line[i].subSequence( line[i].indexOf("/*")+1, line[i].length());	
			    	} else {
				    	line[i] = (String) line[i].subSequence(0, line[i].indexOf("/*"));
				    	for (int j = i+1;j<line.length;j++) {
				    		if (!line[j].contains("*/")) {
				    			line[j]="";
				    		} else {
				    			line[j]=(String)line[j].subSequence(line[j].indexOf("*/")+1, line[j].length());
				    			break;
				    		}
				    	}
			    	}
			    }
	
		}
		return line;
	}
	} 



