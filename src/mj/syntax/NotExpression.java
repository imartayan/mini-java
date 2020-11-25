package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class NotExpression implements Expression {
	public final Expression expression;

	public NotExpression(Expression expression) {
		this.expression = expression;
	}

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		Value toNegate = expression.eval(interp, heap, vars);
		try {
			if (((Int) toNegate).val == 0) {
				return new Int(1);
			} else {
				return new Int(0);
			}
		} catch (ClassCastException e) {
			throw new ExecError("NotExpression.eval() : Unable to cast expression to Boolean");
		}
	}

	public void print() {
		System.out.print("!");
		expression.print();
	}

	public Type type(TypeChecker context) throws TypeError {
		Type condType = this.expression.type(context);
		if (condType.isSubtypeOf(new BooleanType(), context)) {
			throw new TypeError("Type mismatch: cannot convert from " + condType.toString() + " to boolean");
		}
		return condType;
	}

}
