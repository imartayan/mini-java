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
    protected Map<Identifier, Identifier> inheritance = new Hashtable<>();
    protected Map<Identifier, Type> currentVariables = new Hashtable<>();
    protected Map<Identifier, Boolean> initializedVariables = new Hashtable<>();
    protected Identifier currentClass;

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

    public void getInheritance(List<ClassDeclaration> declarations) {
        for (ClassDeclaration classDec : declarations) {
            if (classDec.superClass.isPresent()) {
                this.inheritance.put(classDec.name, classDec.superClass.get());
            }
        }
    }

    public void copyParentAttributesTypes(Identifier classId) {
        if (this.inheritance.containsKey(classId)) {
            Identifier parentId = this.inheritance.get(classId);
            copyParentAttributesTypes(parentId);
            // copy variables from parent
            this.classVariables.get(parentId).forEach((k, v) -> this.classVariables.get(classId).put(k, v));
            // copy methods from parent
            this.classMethods.get(parentId).forEach((k, v) -> this.classMethods.get(classId).put(k, v));
        }
    }

    public void setCurrentClass(ClassDeclaration classDec) {
        this.currentClass = classDec.name;
    }

    public Identifier getCurrentClass() {
        return this.currentClass;
    }

    public boolean isClass(Identifier id) {
        return this.classVariables.containsKey(id);
    }

    public void addVariable(Identifier varId, Type type) {
        this.currentVariables.put(varId, type);
    }

    public void addClassVariables(ClassDeclaration classDec) {
        this.classVariables.get(classDec.name).forEach((k, v) -> this.addVariable(k, v));
    }

    public void removeVariable(Identifier varId) {
        this.currentVariables.remove(varId);
    }

    public void removeClassVariables(ClassDeclaration classDec) {
        this.classVariables.get(classDec.name).forEach((k, v) -> this.removeVariable(k));
    }

    public void addInitVariable(Identifier varId, boolean initialized) {
        this.initializedVariables.put(varId, initialized);
    }

    public void removeInitVariable(Identifier varId) {
        this.initializedVariables.remove(varId);
    }

    public Type lookup(Identifier id) {
        Type res = null;
        if (this.currentVariables.containsKey(id)) {
            res = this.currentVariables.get(id);
        } else if (this.classVariables.containsKey(id)) {
            res = id;
        }
        return res;
    }

    public Couples<Type, List<Type>> lookupMethod(Identifier classId, Identifier methodId) {
        return this.classMethods.get(classId).get(methodId);
    }

    public boolean inheritsFrom(Identifier classId, Type superType) {
        Identifier parentId = classId;
        while (this.lookup(parentId) != superType && this.inheritance.containsKey(parentId)) {
            parentId = this.inheritance.get(parentId);
        }
        return this.lookup(parentId) == superType;
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
