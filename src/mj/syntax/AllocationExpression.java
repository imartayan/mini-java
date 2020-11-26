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
      return null; // TODO
   }

   public void print() {
      // TODO Auto-generated method stub

   }

   public Type type(TypeChecker context) throws TypeError {
      Type res = context.lookup(identifier);
      if (res == null || !context.isClass(identifier)) {
         throw new TypeError("Unbound class constructor " + identifier.name + "()");
      }
      return res;
   }
}
