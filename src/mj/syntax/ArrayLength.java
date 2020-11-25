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
		return null; //TODO
	}

	public void print() {
		argument.print();
		System.out.print(".length");
	}

	@Override
	public Type type(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}

}

