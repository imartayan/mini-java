package mj.syntax;

import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class ThisExpression implements Expression {

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) {
		return interp.currentObject.eval(interp, heap, vars);
	}

	public void print() {
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		return "this";
	}

	@Override
	public Type type(TypeChecker context) throws TypeError {
		return context.lookup(context.getCurrentClass());
	}

    public void checkInitialization(TypeChecker context) throws TypeError {}

}
