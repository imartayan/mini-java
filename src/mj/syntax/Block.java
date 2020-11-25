package mj.syntax;

import java.util.List;

import mj.ExecError;
import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Printer;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class Block implements Statement {
	public final List<Statement> statements;

	public Block(List<Statement> statements) {
		this.statements = statements;
	}

	public void eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		// TODO
	}

	public void print(Printer pp) {
		System.out.println("{");
		pp.incrLevel();
		for (Statement stmt : statements)
			stmt.print(pp);
		pp.decrLevel();
		pp.indent();
		System.out.println("}");
	}

	public void typeCheck(TypeChecker context) throws TypeError {
		// TODO Auto-generated method stub

	}
}

