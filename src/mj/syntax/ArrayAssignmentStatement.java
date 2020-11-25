package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Printer;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class ArrayAssignmentStatement implements Statement {
	public final Identifier identifier;
	public final Expression expression1;
	public final Expression expression2;

	public ArrayAssignmentStatement(Identifier identifier, Expression expression1, Expression expression2) {
		this.identifier = identifier;
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	public void eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		Value arr = identifier.eval(interp, heap, vars);
		Value index = expression1.eval(interp, heap, vars);
		Value i = expression2.eval(interp, heap, vars);

		heap.arrayUpdate(arr, (Int) index, (Int) i);
	}

	public void print(Printer pp) {
		pp.indent();
		identifier.print();
		System.out.print("[");
		expression1.print();
		System.out.print("] = ");
		expression2.print();
		System.out.println(";");
	}

	public void typeCheck(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub

	}
	
}

