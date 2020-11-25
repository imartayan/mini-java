package mj.syntax;

import mj.Value;
import mj.type_checker.TypeChecker;

public class ArrayType implements Type {

    public void print() {
        System.out.print("int[]");
    }

    public boolean isSubtypeOf(Type t, TypeChecker context) {
        // TODO 
        return false;
    }

    @Override
    public String toString() {
        return "int[]";
    }

    public Value defaultValue() {
    	return null;
    };

}

