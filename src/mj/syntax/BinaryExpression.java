package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Int;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class BinaryExpression implements Expression {
	public final Expression operand1;
	public final BinaryOperation operation;
	public final Expression operand2;

	public BinaryExpression(Expression operand1, BinaryOperation operation, Expression operand2) {
		this.operand1 = operand1;
		this.operation = operation;
		this.operand2 = operand2;
	};

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		return null; //TODO
	}

	public void print() {
		System.out.print("(");
		operand1.print();
		operation.print();
		operand2.print();
		System.out.print(")");
	}

	public Type type(TypeChecker context) throws TypeError {
		return this.operation.typeCheck(operand1.type(context), operand2.type(context));
	}

}
