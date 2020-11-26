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
		return heap.allocObject(this.identifier);
   }

   public void print() {
      System.out.print(this.toString());
   }

   @Override
   public String toString() {
      return "new " + this.identifier.toString() + "()";
   }

   public Type type(TypeChecker context) throws TypeError {
      Type res = context.lookup(identifier);
      if (res == null || !context.isClass(identifier)) {
         throw new TypeError("Unbound class constructor " + identifier.name + "()");
      }
      return res;
   }
}
