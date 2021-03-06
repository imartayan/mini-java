package mj.syntax;

import mj.Value;
import mj.type_checker.TypeChecker;
import mj.Int;

public class ArrayType implements Type {

    public void print() {
        System.out.print("int[]");
    }

    public boolean isSubtypeOf(Type t, TypeChecker context) {
        return t instanceof ArrayType;
    }

    @Override
    public String toString() {
        return "int[]";
    }

    public Value defaultValue() {
    	return new Int(0);
    };

}

