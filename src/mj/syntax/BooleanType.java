package mj.syntax;

import java.util.Optional;

import mj.Int;
import mj.Value;
import mj.type_checker.TypeChecker;

public class BooleanType implements Type {

    public void print() {
        System.out.print("boolean");
    }

    public boolean isSubtypeOf(Type t, TypeChecker context) {
        return t instanceof BooleanType;
    }
    
    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public Value defaultValue() {
    	return new Int(0);
    };

}

