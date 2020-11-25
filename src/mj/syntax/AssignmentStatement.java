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
		// TODO
	}

	public void print(Printer pp) {
		pp.indent();
		identifier.print();
		System.out.print(" = ");
		expression.print();
		System.out.println(";");
	}

	public void typeCheck(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub

	}


}

