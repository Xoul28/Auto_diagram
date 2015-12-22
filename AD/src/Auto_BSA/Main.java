package Auto_BSA;

import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
//		FileReader file = new FileReader("file.cpp");
//		Scanner scanner = new Scanner(file);
//		//LinkedList<String> code = new LinkedList<String>();
//		StringBuilder code = new StringBuilder();
//		while (scanner.hasNextLine()) {
//			code.append(scanner.nextLine() + '\n');
//		}
//		// System.out.println(code);
//		Interpreter icpp = new Interpreter(code.toString());
//		icpp.analyze();
//		scanner.close();
       Reader red = new Reader("./AD/src/Auto_BSA/file.cpp");
       //reading file and removing comments
       String[] s = red.read();
       Interpreter icpp = new Interpreter(red.OneStringConverter(s));
       icpp.analyze();
	
	  	
	}

}
