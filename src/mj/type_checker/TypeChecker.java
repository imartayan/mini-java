package mj.type_checker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import mj.syntax.ClassDeclaration;
import mj.syntax.Identifier;
import mj.syntax.MethodDeclaration;
import mj.syntax.Type;
import mj.syntax.VarDeclaration;
import mj.parser.Parser;
import mj.syntax.Program;

public class TypeChecker {
    // TODO : Rajoutez autant de champs et méthodes que nécessaire dans cette
    // classe.

    protected Map<Identifier, Map<Identifier, Type>> classVariables = new Hashtable<>();
    protected Map<Identifier, Type> localVariables = new Hashtable<>();

    public void getClassAttributesTypes(ClassDeclaration classDec) {
        Map<Identifier, Type> varMap = new Hashtable();
        for(VarDeclaration newVar : classDec.varDeclarations) {
            varMap.put(newVar.identifier, newVar.type);
        }
        for(MethodDeclaration newMethod : classDec.methodDeclarations) {
            varMap.put(newMethod.name, newMethod.resType);
        }
        this.classVariables.put(classDec.name, varMap);
    }

    public void addVariables(Identifier classId) {
        
    }

    public static void main(String[] arg) {
        try {
            String filename;
            if (arg.length == 0) {
                filename = "tests/ok/BinaryTree.java";
            } else {
                filename = arg[0];
            }
            Program p = Parser.run(new FileInputStream(filename));
            TypeChecker t = new TypeChecker();
            p.typeCheck(t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TypeError e) {
            e.printStackTrace();
        }
    }
}
