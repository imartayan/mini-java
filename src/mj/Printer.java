package mj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import mj.syntax.*;

public class Printer {
	private final static String INDENT_STEP = "    ";
	private int indentLevel = 0;

	public void indent() {
		for (int i = 0; i < indentLevel; i++)
			System.out.print(INDENT_STEP);
	}

	public void incrLevel() { indentLevel++; };

	public void decrLevel() { indentLevel--; };

	public static void main(String[] arg) {
		try {
			Program prog = mj.parser.Parser.run(new FileInputStream(arg[0]));
			prog.print();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
