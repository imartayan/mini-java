package mj.syntax;

import mj.Value;
import mj.type_checker.TypeChecker;

public interface Type {
    public void print();
    public String toString();
    public boolean isSubtypeOf(Type t, TypeChecker context);
    public Value defaultValue();
}

