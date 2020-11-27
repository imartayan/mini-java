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
		// On cast en Int parce que l'expression doit etre un booleen
		try {
			Int cond = (Int) expression.eval(interp, heap, vars);
			while(cond.val != 0) {
				statement.eval(interp, heap, vars);
				cond = (Int) expression.eval(interp, heap, vars);
			}
		} catch (ClassCastException e) {
			throw new ExecError("WhileStatement : Unable to cast condition to boolean");
		}
	}

	public void print(Printer pp) {
		pp.indent();
		System.out.print("while (");
		expression.print();
		System.out.print(") ");
		statement.print(pp);
	}

	@Override
	public void typeCheck(TypeChecker context) throws TypeError {
        this.expression.checkInitialization(context);
        Type condType = this.expression.type(context);
		if(!condType.isSubtypeOf(new BooleanType(), context)) {
			throw new TypeError("Type mismatch: cannot convert from " + condType.toString() + " to boolean");
		}
		this.statement.typeCheck(context);
	}

}
