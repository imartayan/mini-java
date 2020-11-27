package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Printer;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class AssignmentStatement implements Statement {
	public final Identifier identifier;
	public final Expression expression;

	public AssignmentStatement(Identifier identifier, Expression expression) {
		this.identifier = identifier;
		this.expression = expression;
	}

	public void eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		Value value = expression.eval(interp, heap, vars);
		if (expression instanceof ArrayAllocationExpression) {
			interp.arrays.put(identifier, value);
		}
		heap.fieldUpdate(interp.currentObject.eval(interp, heap, vars),identifier,value);
	}

	public void print(Printer pp) {
		pp.indent();
		identifier.print();
		System.out.print(" = ");
		expression.print();
		System.out.println(";");
	}

	public void typeCheck(TypeChecker context) throws TypeError {
        this.expression.checkInitialization(context);
		Type exprType = this.expression.type(context);
		Type idType = this.identifier.type(context);
		if(!exprType.isSubtypeOf(idType, context)) {
			throw new TypeError("l:" + this.identifier.line + ", c:" + this.identifier.col + " - Type mismatch: cannot convert from " + exprType.toString() + " to " + idType.toString());
		}
        context.addInitVariable(this.identifier, true);
	}

}
