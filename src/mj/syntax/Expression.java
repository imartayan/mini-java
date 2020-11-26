package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public interface Expression {

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError;

	public void print();

	public String toString();

	public Type type(TypeChecker context) throws TypeError;

}

