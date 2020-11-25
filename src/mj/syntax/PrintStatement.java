package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Printer;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class PrintStatement implements Statement {
	public Expression expression;

	public PrintStatement(Expression expression) {
		this.expression = expression;
	}

	public void eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		// TODO
	}

	public void print(Printer pp) {
		pp.indent();
		System.out.print("System.out.println(");
		expression.print();
		System.out.println(");");
	}

	public void typeCheck(TypeChecker context) throws TypeError {
		expression.type(context);

	}

}

