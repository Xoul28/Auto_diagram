package auto_BSA;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static String  NameOfFunc = null;
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0) {
			System.out.println("Command line arguments:");
			System.out.println("nameOfFileToRead nameOfFunction");
			System.out.println("if nameOfFunction is missing, reading 1st function in the file");
		}
		if (args.length > 0) {
			FileReader file = new FileReader(args[0]);
			Scanner scanner = new Scanner(file);
			StringBuilder code = new StringBuilder();
			while (scanner.hasNextLine()) {
				code.append(scanner.nextLine() + '\n');
			}
			Interpreter icpp = new Interpreter(code.toString());
			icpp.deleteComments();
			Block[] b;
			if (args.length > 1) {
				NameOfFunc = args[1];
				b = icpp.analyze(args[1]).toArray(new Block[0]);
			} else {
				b = icpp.analyze().toArray(new Block[0]);				
			}
			scanner.close();
			new DrawerSimple(b[0]).setVisible(true);
		}
	}

}
