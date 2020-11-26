package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class ArrayLookup implements Expression {
	public final Expression expression1;
	public final Expression expression2;

	public ArrayLookup(Expression expression1, Expression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		Value array = expression1.eval(interp, heap, vars);
		try {
			Int index = (Int) expression2.eval(interp, heap, vars);
			return heap.arrayLookup(array, index);
		} catch (ClassCastException e) {
			throw new ExecError("ArrayLookup.eval() : Unable to cast index argument to Int");
		}
	}

	public void print() {
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		return this.expression1.toString() + "[" + this.expression2.toString() + "]";
	}

	@Override
	public Type type(TypeChecker context) throws TypeError {
		Type expr1Type = this.expression1.type(context);
		Type expr2Type = this.expression2.type(context);
		if (!expr1Type.isSubtypeOf(new ArrayType(), context)) {
			throw new TypeError(
					"The type of the expression must be an array but it resolved to " + expr1Type.toString());
		}
		if (!expr2Type.isSubtypeOf(new IntegerType(), context)) {
			throw new TypeError("Type mismatch: cannot convert from " + expr2Type.toString() + " to integer");
		}
		return new IntegerType();
    }

    public void checkInitialization(TypeChecker context) throws TypeError {
        this.expression1.checkInitialization(context);
        this.expression2.checkInitialization(context);
    }

}
