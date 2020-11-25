package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Printer;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class WhileStatement implements Statement {
	public final Expression expression;
	public final Statement statement;

	public WhileStatement(Expression expression, Statement statement) {
		this.expression = expression;
		this.statement = statement;
	}

	public void eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		//TODO
	}

	public void print(Printer pp) {
		pp.indent();
		System.out.print("while (");
		expression.print();
		System.out.print(") ");
		statement.print(pp);
	}

	@Override
	public void typeCheck(TypeChecker t) throws TypeError {
		Type condType = this.expression.type(t);
		if(!condType.isSubtypeOf(new BooleanType(), t)) {
			throw new TypeError("Type mismatch: cannot convert from " + condType.toString() + " to boolean");
		}
		this.statement.typeCheck(t);
	}

}

