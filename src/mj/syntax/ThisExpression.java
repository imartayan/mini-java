package mj.syntax;

import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class ThisExpression implements Expression {

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) {
		return interp.CurrentObject;
	}

	public void print() {
		System.out.print("this");
	}

	@Override
	public Type type(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}



}

