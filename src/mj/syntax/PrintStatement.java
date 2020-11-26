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
		try {
			System.out.println(((Int) expression.eval(interp, heap, vars)).toString());
		} catch (ClassCastException e) {
			throw new ExecError("PrintStatement : Unable to cast to Int from expression");
		}
	}

	public void print(Printer pp) {
		pp.indent();
		System.out.print("System.out.println(");
		expression.print();
		System.out.println(");");
	}

	public void typeCheck(TypeChecker context) throws TypeError {
		if (expression.type(context).isSubtypeOf(new IntegerType(), context)) {
			throw new TypeError("Cannot print non integer parameter");
		}

	}

}
