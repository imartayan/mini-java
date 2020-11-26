package mj.syntax;

import mj.Int;
import mj.type_checker.TypeError;

public class LtOperation implements BinaryOperation {

	public Int eval(Int i1, Int i2) {
		return new Int((i1.val < i2.val) ? 1 : 0);
	}

	@Override
	public Type typeCheck(Type type1, Type type2) throws TypeError {
		if (!(type1 instanceof IntegerType && type2 instanceof IntegerType)) {
			throw new TypeError("The operator < is undefined for the argument type(s) " + type1.toString() + ", "
					+ type2.toString());
		}
		return new BooleanType();
	}

	public void print() {
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		return "<";
	}

}
