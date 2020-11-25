package mj.syntax;

import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class IntegerLiteral implements Expression {
	public final Integer value;

	public IntegerLiteral(Integer value) {
		this.value = value;
	}

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) {
		return null; //TODO
	}

	public void print() {
		System.out.print(value);
	}

	public Type type(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}

}

