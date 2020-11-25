package mj.syntax;

import mj.Int;
import mj.type_checker.TypeError;

public class MultOperation implements BinaryOperation {

	public Int eval(Int i1, Int i2) {
		return new Int(i1.val * i2.val);
	}

	public void print() {
		System.out.print("*");
	}

	public Type typeCheck(Type type1, Type type2) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}

}
