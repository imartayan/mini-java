class CheckClass{
    public static void main(String[] a){
        System.out.println(42);
    }
}

class CheckClassA{
    int a;
    public boolean initA() {
        a = 1;
        return true;
    }
}

// Check inheritance from super class
class CheckClassB extends CheckClassA {
    int b;
    public boolean initB() {
        boolean res = initA();
        b = a + 1;
        return res;
    }
}

// Check that other class attributes are not kept in memory
class CheckClassC extends CheckClassA {
    boolean b;
    public boolean initC() {
        boolean res = initA();
        b = false;
        return res;
    }
}
