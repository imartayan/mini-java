package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class ArrayAllocationExpression implements Expression {
	public final Expression size;

	public ArrayAllocationExpression(Expression size) {
		this.size = size;
	}

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		try {
			return heap.allocArray((Int) size.eval(interp, heap, vars));
		} catch (ClassCastException e) {
			throw new ExecError("ArrayAllocationExpression : Unable to cast size to Int");
		}
	}

	public void print() {
		System.out.print(this.toString());
	}

	public String toString() {
		return "new int[" + size.toString() + "]";
	}

	public Type type(TypeChecker context) throws TypeError {
		Type sizeType = this.size.type(context);
		if(!sizeType.isSubtypeOf(new IntegerType(), context)) {
			throw new TypeError("Type mismatch: cannot convert from " + sizeType.toString() + " to integer");
		}
		return sizeType;
	}

    public void checkInitialization(TypeChecker context) throws TypeError {
        this.size.checkInitialization(context);
    }

}
