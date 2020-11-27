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
        "tests/ok/BinaryTree.java",
        "tests/ok/BubbleSort.java",
        "tests/ok/Factorial.java",
        "tests/ok/LinearSearch.java",
        "tests/ok/MoreThan4.java",
        "tests/ok/QuickSort.java",
        "tests/ok/OperationTest.java",
        "tests/ok/TreeVisitor.java"
    };

    private static String[] invalidFiles = {
        "tests/error/TypecheckNotInitialized.java",
        "tests/error/BinaryTree-error.java",
        "tests/error/BubbleSort-error.java",
        "tests/error/Factorial-error.java",
        "tests/error/LinearSearch-error.java",
        "tests/error/MoreThan4-error.java",
        "tests/error/QuickSort-error.java",
        "tests/error/OperationTest-error.java",
        "tests/error/TreeVisitor-error.java"
    };

    private static void runPositiveTests() {
        for (String filename : validFiles) {
            try {
                Program p = Parser.run(new FileInputStream(filename));
                TypeChecker t = new TypeChecker();
                System.out.println("Typechecking " + filename);
                p.typeCheck(t);
                Interpreter interp = new Interpreter(p);
                System.out.println("Executing " + filename);
                interp.run(new SimpleHeap(interp));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (TypeError e) {
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
                Interpreter interp = new Interpreter(p);
                System.out.println("Executing " + filename);
                interp.run(new SimpleHeap(interp));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (TypeError e) {
                System.out.println("TypeError as expected");
            } catch (ExecError e) {
                System.out.println("ExecError as expected");
            }
        }
    }

    public static void main(String[] arg) {
        runPositiveTests();
        runNegativeTests();
    }
}
