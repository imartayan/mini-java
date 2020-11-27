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
		System.out.print(this.toString());
	}

	@Override
	public String toString() {
		return "&&";
	}

	@Override
	public Type typeCheck(Type type1, Type type2) throws TypeError {
		if (!(type1 instanceof BooleanType && type2 instanceof BooleanType)) { // Here, we aren't given context by the
																				// typeCheck function, so we need to use
																				// instanceof instead to verify both
																				// given types are booleans. 
			throw new TypeError("The operator && is undefined for the argument type(s) " + type1.toString() + ", "
					+ type2.toString());
		}
		return type1;
	}

}
