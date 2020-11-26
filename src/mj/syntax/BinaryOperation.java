package mj.syntax;

import mj.Int;
import mj.type_checker.TypeError;

public interface BinaryOperation {
	
	public Int eval(Int i1, Int i2);
	
	public void print();

	public String toString();

	public Type typeCheck(Type type1, Type type2) throws TypeError;

}
