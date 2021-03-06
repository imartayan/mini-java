package mj.syntax;

import mj.Value;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;
import mj.ExecError;
import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;

public class AllocationExpression implements Expression {
   public final Identifier identifier;

   public AllocationExpression(Identifier identifier) {
      this.identifier = identifier;
   }

   public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
      UniqueIdentifier id = new UniqueIdentifier();

      Value value = heap.allocObject(this.identifier);
      interp.objects.put(id,value);
      return value;
   }

   public void print() {
      System.out.print(this.toString());
   }

   @Override
   public String toString() {
      return "new " + this.identifier.toString() + "()";
   }

   public Type type(TypeChecker context) throws TypeError {
        if (context.isClass(this.identifier)) {
            return this.identifier;
        }
        throw new TypeError("l:" + identifier.line + ", c:" + identifier.col + " - Unbound class constructor " + identifier.name + "()");
   }

   public void checkInitialization(TypeChecker context) throws TypeError {}
}
