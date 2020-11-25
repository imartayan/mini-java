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
		System.out.print("new int[");
		size.print();
		System.out.print("]");
	}

	public Type type(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}

}

