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
		return new Int(this.value);
	}

	public void print() {
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	public Type type(TypeChecker context) throws TypeError {
		return new IntegerType();
	}

    public void checkInitialization(TypeChecker context) throws TypeError {}

}
