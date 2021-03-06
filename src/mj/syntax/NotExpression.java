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
			// On cast vers Int car l'expression doit s'evaluer en un booleen
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
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		return "!" + this.expression.toString();
	}

	public Type type(TypeChecker context) throws TypeError {
		Type condType = this.expression.type(context);
		if (!condType.isSubtypeOf(new BooleanType(), context)) {
			throw new TypeError("Type mismatch: cannot convert from " + condType.toString() + " to boolean");
		}
		return condType;
	}

    public void checkInitialization(TypeChecker context) throws TypeError {
        this.expression.checkInitialization(context);
    }

}
