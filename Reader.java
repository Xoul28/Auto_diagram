package Auto_BSA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Reader {
   // enum type  {forcycle,whilecycle,dowhilecycle,Block,InOut,IfRhombus};
	public static String[] read(String Path) throws FileNotFoundException{
		Scanner fin = new Scanner(new FileReader(Path));
		String line;
		LinkedList<String> crutch = new LinkedList<String>();
		while(fin.hasNextLine()){
			line = fin.nextLine();
			if(!(line.equals(""))&&(line.charAt(0)!='/')&&(line.charAt(0)!='#')){
				crutch.add(line);
			}
		}
		String[] ret = crutch.toArray(new String[0]);
		return Analyzer(ret);
		
	}

	public static String[] Analyzer(String[] arstr){
		int BracketsOpen = 0, BracketsClosed = 0;
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
	public static void main(String[] args) throws FileNotFoundException {
		String[] p = read(args[0]);
		for (int i = 0; i < p.length; i++) {
			System.out.println(p[i]);
		}
		

	}

}
