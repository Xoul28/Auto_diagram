package Auto_BSA;

import java.util.Scanner;

public class Analyzer {
	static int p = -1;
	private static String[] isComment(String[] line) {
		for (int i = 0;i<p;i++) {
				if (line[i].contains("//")) {
					//if(line.subSequence(0, line.indexOf("//")).length()!=0)
					line[i] = (String) line[i].subSequence(0, line[i].indexOf("//"));		
				}
			    if (line[i].contains("/*")) {
			    	if (line[i].contains("*/")) {
			    		line[i] = (String) line[i].subSequence(0, line[i].indexOf("/*")) + (String) line[i].subSequence( line[i].indexOf("/*")+1, line[i].length());	
			    	} else {
				    	line[i] = (String) line[i].subSequence(0, line[i].indexOf("/*"));
				    	for (int j = i+1;j<p;j++) {
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
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String nline[] = new String[100];
		
		int h = -1;
		do{
			h++;
			nline[h]=sc.nextLine();
		    p = h;
		}while(!nline[h].equals("stop"));
		sc.close();
		String[]  pline = isComment(nline);
		for (h = 0; h < p; h++) {
			if (pline[h].length() > 0) {
			System.out.println(pline[h]); 
			}
		}
		
	}
}
