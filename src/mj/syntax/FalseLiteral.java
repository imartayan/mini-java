package mj.syntax;

import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class FalseLiteral implements Expression {

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) {
		return new Int(0);
	}

	public void print() {
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		return "false";
	}

	public Type type(TypeChecker context) throws TypeError {
		return new BooleanType();
	}

    public void checkInitialization(TypeChecker context) throws TypeError {}

}
