package mj.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import mj.parser.Parser;
import mj.syntax.Program;

import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

import mj.SimpleHeap;
import mj.Interpreter;
import mj.ExecError;

public class Test {
    private static String[] validFiles = {
            "tests/ok/TypecheckProgram.java",
            "tests/ok/TypecheckMain.java",
            "tests/ok/TypecheckClass.java",
            "tests/ok/TypecheckMethod.java",
            "tests/ok/OperationTest.java",
            "tests/ok/BinaryTree.java"
    };
    private static String[] invalidFiles = {
        "tests/error/TypecheckNotInitialized.java",
    };

    private static void runPositiveTests() {
        for (String filename : validFiles) {
            try {
                Program p = Parser.run(new FileInputStream(filename));
                TypeChecker t = new TypeChecker();
                System.out.println("Typechecking " + filename);
                p.typeCheck(t);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (TypeError e) {
                e.printStackTrace();
            }
            try {
                Program p = Parser.run(new FileInputStream(filename));
                Interpreter interp = new Interpreter(p);
                interp.run(new SimpleHeap(interp));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ExecError e) {
                e.printStackTrace();
            }    
        }
    }

    private static void runNegativeTests() {
        for (String filename : invalidFiles) {
            try {
                Program p = Parser.run(new FileInputStream(filename));
                TypeChecker t = new TypeChecker();
                System.out.println("Typechecking " + filename);
                p.typeCheck(t);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (TypeError e) {
                System.out.println("TypeError");
            }
        }
    }

    public static void main(String[] arg) {
        runPositiveTests();
        runNegativeTests();
    }
}
