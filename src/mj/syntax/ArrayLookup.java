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
		expression1.print();
		System.out.print("[");
		expression2.print();
		System.out.print("]");
	}

	@Override
	public Type type(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}
}

