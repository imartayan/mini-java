package mj.type_checker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import mj.parser.Parser;
import mj.syntax.Program;

public class TypeChecker {
    // TODO : Rajoutez autant de champs et méthodes que nécessaire dans cette classe.

    public static void main(String[] arg) {
        try {
            String filename;
            if (arg.length == 0) {
				filename = "tests/ok/BinaryTree.java";
            } else {
                filename = arg[0];
            }
            Program p = Parser.run(new FileInputStream(filename));
            TypeChecker t = new TypeChecker();
            p.typeCheck(t);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TypeError e) {
            e.printStackTrace();
        }
	}
}
