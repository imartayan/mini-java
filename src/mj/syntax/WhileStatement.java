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
		Int cond = (Int) expression.eval(interp, heap, vars);
		while(cond.val != 0) {
			statement.eval(interp, heap, vars);
			cond = (Int) expression.eval(interp, heap, vars);
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
	public void typeCheck(TypeChecker t) throws TypeError {
		// TODO Auto-generated method stub

	}

}

