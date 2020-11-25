package mj.syntax;

import mj.Int;
import mj.type_checker.TypeError;

public class LtOperation implements BinaryOperation {

	public Int eval(Int i1, Int i2) {
		return new Int((i1.val < i2.val) ? 1 : 0);
	}

	@Override
	public Type typeCheck(Type type1, Type type2) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}

	public void print() {
		System.out.print("<");
	}

}
