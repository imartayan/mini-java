package mj.type_checker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mj.Couples;
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
    protected Map<Identifier, Map<Identifier, Couples<Type, List<Type>>>> classMethods = new Hashtable<>();
    protected Map<Identifier, Type> currentVariables = new Hashtable<>();

    public void getClassAttributesTypes(ClassDeclaration classDec) {
        Map<Identifier, Type> varMap = new Hashtable<>();
        Map<Identifier, Couples<Type, List<Type>>> methodsMap = new Hashtable<>();

        for (VarDeclaration newVar : classDec.varDeclarations) {
            varMap.put(newVar.identifier, newVar.type);
        }

        for (MethodDeclaration newMethod : classDec.methodDeclarations) {
            List<Type> newParamsType = new LinkedList<>();
            for (VarDeclaration newParam : newMethod.params) {
                newParamsType.add(newParam.type);
            }
            methodsMap.put(newMethod.name, new Couples<>(newMethod.resType, newParamsType));
        }

        this.classVariables.put(classDec.name, varMap);
        this.classMethods.put(classDec.name, methodsMap);
    }

    public void addVariables(Identifier classId) {
        this.classVariables.get(classId).forEach((k, v) -> this.currentVariables.put(k, v));
    }

    public void removeVariables(Map<Identifier, Type> localVars) {
        localVars.forEach((k, v) -> this.currentVariables.remove(k));
    }

    public Map<Identifier, Type> getClassVars(Identifier classId) {
        return this.classVariables.get(classId);
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
