package mj.syntax;

import java.util.List;
import java.util.Iterator;
import mj.Couples;

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
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		String argsString = "";
		boolean start = true;
		for (Expression e : arguments)
			if (start) {
				argsString += e.toString();
				start = false;
			} else {
				argsString += ", " + e.toString();
			}
		return this.receiver.toString() + "." + this.name.toString() + "(" + argsString + ")";
	}

	public Type type(TypeChecker context) throws TypeError {
		try {
			Identifier exprId = (Identifier) this.receiver.type(context);
			if (!context.isClass(exprId)) {
				throw new ClassCastException();
			}
			Couples<Type, List<Type>> expectedType = context.lookupMethod(exprId, this.name);
			if (expectedType == null) {
				throw new TypeError("Method " + this.name.toString() + " undefined for class " + exprId.toString());
			}
			if (this.arguments.size() != expectedType.second.size()) {
				throw new TypeError("Unexpected number of arguments");
			}
			Iterator<Expression> argsIterator = this.arguments.iterator();
			Iterator<Type> expectedIterator = expectedType.second.iterator();
			while (argsIterator.hasNext() && expectedIterator.hasNext()) {
				Type argsNext = argsIterator.next().type(context);
				Type expectedNext = expectedIterator.next();
				if (argsNext != expectedNext) {
					throw new TypeError("Argument type does not match: expected " + expectedNext.toString()
							+ " but was " + argsNext.toString());
				}
			}
			return expectedType.first;
		} catch (ClassCastException e) {
			throw new TypeError(receiver.toString() + " cannot be evaluated to an existing class");
		}
	}

}
