package Auto_BSA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FileReader file = new FileReader("file.cpp");
		Scanner scanner = new Scanner(file);
		//LinkedList<String> code = new LinkedList<String>();
		StringBuilder code = new StringBuilder();
		while (scanner.hasNextLine()) {
			code.append(scanner.nextLine() + '\n');
		}
		// System.out.println(code);
		Interpreter icpp = new Interpreter(code.toString());
		icpp.analyze();
		scanner.close();
	}

}
