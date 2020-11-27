package mj.syntax;

import java.util.List;
import java.util.Iterator;
import java.util.Map.Entry;
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
		Identifier exCurrentObject = interp.currentObject;
		//Defining new currentObject
		Value recEval = receiver.eval(interp, heap, vars);
		boolean changed = false;
		for (Entry<Identifier, Value> entry : interp.objects.entrySet()) {
			if (entry.getValue().equals(recEval)) {
				interp.currentObject = entry.getKey();
				changed = true;
			}
		}
		if (!changed) {
			throw new ExecError("MessageSend : Unknown "+receiver);
		}
		// Getting method
		ClassDeclaration methodsClass = interp.classes.get(heap.classname(interp.objects.get(interp.currentObject)));
		Identifier methodsClassName = methodsClass.name;
		MethodDeclaration method = null;
		while (method == null) {
			if (interp.methods.get(methodsClassName).containsKey(this.name)) {
				method = interp.methods.get(methodsClassName).get(this.name);
			} else if (methodsClass.superClass.isPresent()) {
                // If not found, check for superClass
				methodsClassName = methodsClass.superClass.get();
				methodsClass = interp.classes.get(methodsClassName);
			} else {
				throw new ExecError("MessageSend : No definition for this method");
			}
		}
		// Setting-up variables
		// Parameters
		LocalVar workVars = new LocalVar(method.params);
		Iterator<VarDeclaration> paramsIterator = method.params.iterator();
		Iterator<Expression> argumentsIterator = this.arguments.iterator();
		while (paramsIterator.hasNext() && argumentsIterator.hasNext()) {
			Identifier paramIdentifier = paramsIterator.next().identifier;
			Value argValue = argumentsIterator.next().eval(interp, heap, vars);
			workVars.store(paramIdentifier, argValue);
		}
		// Objects fields
		ClassDeclaration currentClass = interp.classes.get(heap.classname(interp.objects.get(interp.currentObject)));
		Value object = interp.objects.get(interp.currentObject);
		for (VarDeclaration varDec : currentClass.varDeclarations) {
			if (!(workVars.types.containsKey(varDec.identifier))) {
                // Si l'identifiant a déjà été donné pour un
				// paramètre, la valeur du champs est ignoré.
				workVars.init(varDec.identifier, varDec.type, heap.fieldLookup(object, varDec.identifier));
			}
		}
		while (currentClass.superClass.isPresent()) {
			currentClass = interp.classes.get(currentClass.superClass.get());
			for (VarDeclaration varDec : currentClass.varDeclarations) {
				if (!(workVars.types.containsKey(varDec.identifier))) {
                    // Si l'identifiant a déjà été donné pour un
			        // paramètre, la valeur du champs est ignoré.
					workVars.init(varDec.identifier, varDec.type, heap.fieldLookup(object, varDec.identifier));
				}
			}
		}
		// Evaluation
		method.body.eval(interp, heap, workVars);
		Value res = method.result.eval(interp, heap, workVars);
		// Leaving
		interp.currentObject = exCurrentObject;
		return res;
	}

	public void print() {
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		String argsString = "";
		boolean start = true;
		for (Expression e : arguments) {
			if (start) {
				argsString += e.toString();
				start = false;
			} else {
				argsString += ", " + e.toString();
			}
		}
		return this.receiver.toString() + "." + this.name.toString() + "(" + argsString + ")";
	}

	public Type type(TypeChecker context) throws TypeError {
		try {
			Identifier classId = (Identifier) this.receiver.type(context);
			// This throws an exception if the expression isn't an identifier
			// and more specifically a class name
			if (!context.isClass(classId)) {
                throw new ClassCastException();
                // See the catch block error
			}
			if (!context.isMethod(classId, this.name)) {
                // Check that the method is defined
				throw new TypeError(
                    "l:" + classId.line + ", c:" + classId.col + " - Method " + this.name.toString() + " undefined for class " + classId.toString()
                );
			}
			Couples<Type, List<Type>> expectedType = context.lookupMethod(classId, this.name);
			if (this.arguments.size() != expectedType.second.size()) {
                // Check the number of arguments is correct
				throw new TypeError(
                    "l:" + this.name.line + ", c:" + this.name.col + " - Unexpected number of arguments"
                );
			}

			Iterator<Expression> argsIterator = this.arguments.iterator();
			Iterator<Type> expectedIterator = expectedType.second.iterator();
			while (argsIterator.hasNext() && expectedIterator.hasNext()) {
                // Select the next arguments
                Type argsNext = argsIterator.next().type(context);
                // and check if the variable passed
                Type expectedNext = expectedIterator.next();
                // is of the proper declared type
				if (!argsNext.isSubtypeOf(expectedNext, context)) {
					throw new TypeError(
                        "l:" + this.name.line + ", c:" + this.name.col + " - Argument type does not match: expected " + expectedNext.toString() + " but was " + argsNext.toString()
                    );
				}
			}
            // Return the function's declared return type if no exception was thrown
            return expectedType.first;
		} catch (ClassCastException e) {
			throw new TypeError(
                "l:" + this.name.line + ", c:" + this.name.col + " - " + receiver.toString() + " cannot be evaluated to an existing class"
            );
		}
	}

	public void checkInitialization(TypeChecker context) throws TypeError {
		for (Expression e : arguments) {
			e.checkInitialization(context);
		}
	}

}
