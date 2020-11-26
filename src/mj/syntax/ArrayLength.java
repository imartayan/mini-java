package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class ArrayLength implements Expression {
	public final Expression argument;

	public ArrayLength(Expression argument) {
		this.argument = argument;
	}

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		Value array = argument.eval(interp, heap, vars);
		return heap.arrayLength(array);
	}

	public void print() {
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		return argument.toString() + ".length";
	}

	@Override
	public Type type(TypeChecker context) throws TypeError {
		Type argType = this.argument.type(context);
		if (!argType.isSubtypeOf(new ArrayType(), context)) {
			throw new TypeError("The type of the expression must be an array but it resolved to " + argType.toString());
		}
		return new IntegerType();
	}

}
