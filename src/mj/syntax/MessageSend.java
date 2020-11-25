package mj.syntax;

import java.util.List;

import mj.ExecError;
import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class MessageSend implements Expression {
	public final Expression receiver;
	public final Identifier name;
	public final List<Expression> arguments;

	public MessageSend(Expression receiver, Identifier name, List<Expression> arguments) {
		this.receiver = receiver;
		this.name = name;
		this.arguments = arguments;
	}

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		// TODO
		return null;
	}

	public void print() {
		receiver.print();
		System.out.print(".");
		name.print();
		System.out.print("(");
		int i = 0;
		for (Expression e: arguments) 
			if (i++==0) e.print();
			else {
				System.out.print(",");
				e.print();
			}
		System.out.print(")");
	}

	public Type type(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}

}

