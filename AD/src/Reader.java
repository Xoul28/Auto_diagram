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

	public static String[] Analyzer(String[] arstr){
		//int BracketsOpen = 0, BracketsClosed = 0;
		LinkedList<String> crutch = new LinkedList<String>();
		for (int i = 0; i < arstr.length; i++) {
			if(arstr[i].contains("for")){
				crutch.add("cycle opening " + arstr[i]);
			}else if(arstr[i].contains(";")){
				crutch.add("block element " + arstr[i]);
			}else if(arstr[i].contains("if")){
				crutch.add("if element " + arstr[i]);
			}
		}
		String[] ret = crutch.toArray(new String[0]);
		return ret;	 
	}
	} 


