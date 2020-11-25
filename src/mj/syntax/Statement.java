package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Printer;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public interface Statement {

	public void eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError;

	public void typeCheck(TypeChecker context) throws TypeError;
		
	public void print(Printer pp);
}

