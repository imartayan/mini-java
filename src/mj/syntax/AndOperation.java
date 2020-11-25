package mj.syntax;

import mj.Int;
import mj.type_checker.TypeError;

public class AndOperation implements BinaryOperation {

	public Int eval(Int i1, Int i2) {
		if ((i1.val != 0) && (i2.val != 0)) {
			return new Int(1);
		} else {
			return new Int(0);
		}
	}

	public void print() {
		System.out.print("&&");
	}

	@Override
	public Type typeCheck(Type type1, Type type2) throws TypeError {
		if (!(type1 instanceof BooleanType && type2 instanceof BooleanType)) {
			throw new TypeError("The operator && is undefined for the argument type(s) " + type1.toString() + ", "
					+ type2.toString());
		}
		return type1;
	}

}
