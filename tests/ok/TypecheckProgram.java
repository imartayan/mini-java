class CheckProgram{
    public static void main(String[] a){
        System.out.println(42);
    }
}

class CheckProgramA{
    int a;
    public boolean initA() {
        a = 1;
        return true;
    }
}

// Check inheritance from super class
class CheckProgramB extends CheckProgramA {
    int b;
    public boolean initB() {
        boolean res;
        res = this.initA();
        b = a + 1;
        return res;
    }
}

// Check inheritance when the attribute is not
// directly defined in the super class
class CheckProgramC extends CheckProgramB {
    int c;
    public boolean initC() {
        boolean res;
        res = this.initA();
        c = a + 2;
        return res;
    }
}
