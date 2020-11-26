package mj.syntax;

import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Map;
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
		//Saving data before method appliciation
		Identifier exCurrentObject= interp.currentObject;
		try {
			interp.currentObject = (Identifier) receiver;
		} catch (ClassCastException e) {
			throw new ExecError("MessageSend : cannot cast receiver to Identifier");
		}
		Map<Identifier, Value> exObjects = Collections.emptyMap();
		exObjects.putAll(interp.objects);
		//Getting method
		MethodDeclaration method=(interp.methods.get(interp.currentObject).get(name));
		//Add arguments as local variables
		LocalVar WorkVars = new LocalVar(method.params);
		WorkVars.types.putAll(vars.types);
		WorkVars.values.putAll(vars.values);
		Iterator<VarDeclaration> paramsIterator = method.params.iterator();
		Iterator<Expression> argumentsIterator = this.arguments.iterator();
		while (paramsIterator.hasNext() && argumentsIterator.hasNext()) {
			Identifier paramIdentifier = paramsIterator.next().identifier;
			Value argValue = argumentsIterator.next().eval(interp, heap, vars);
			WorkVars.store(paramIdentifier, argValue);
		}
		//Evaluation
		for (VarDeclaration varDec: method.declarations) {
			WorkVars.init(varDec.identifier,varDec.type, varDec.type.defaultValue());
		}
		method.body.eval(interp, heap, WorkVars);
		Value res= method.result.eval(interp, heap, WorkVars);
		//Resetting initial paramaters (vars is not changed due to our use of WorkVars)
		interp.currentObject = exCurrentObject;
		interp.objects = exObjects;
		return res;
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
