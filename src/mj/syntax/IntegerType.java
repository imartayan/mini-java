package mj.syntax;

import mj.Int;
import mj.Value;
import mj.type_checker.TypeChecker;

public class IntegerType implements Type {

	public void print() {
		System.out.print("int");
    }

	@Override
    public boolean isSubtypeOf(Type t, TypeChecker context) {
        return t instanceof IntegerType;
    }

    @Override
    public String toString() {
        return "int";
    }
    
    @Override
    public Value defaultValue() {
    	return new Int(0);
    };
}

