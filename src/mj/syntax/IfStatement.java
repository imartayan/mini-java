package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Printer;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class IfStatement implements Statement {
	public final Expression expression;
	public final Statement statement1;
	public final Statement statement2;

	public IfStatement(Expression expression, Statement statement1, Statement statement2) {
		this.expression = expression;
		this.statement1 = statement1;
		this.statement2 = statement2;
	}

	public void eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		//TODO
	}

	public void print(Printer pp) {
		pp.indent();
		System.out.print("if (");
		expression.print();
		System.out.println(") ");
		pp.incrLevel();
		statement1.print(pp);
		pp.decrLevel();
		pp.indent();
		System.out.println("else ");
		pp.incrLevel();
		statement2.print(pp);
		pp.decrLevel();
	}

	public void typeCheck(TypeChecker context) throws TypeError {
		Type condType = this.expression.type(context);
		if(!condType.isSubtypeOf(new BooleanType(), context)) {
			throw new TypeError("Type mismatch: cannot convert from " + condType.toString() + " to boolean");
		}
		this.statement1.typeCheck(context);
		this.statement2.typeCheck(context);
	}

}

