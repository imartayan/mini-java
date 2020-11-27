class TestVarType{
    public static void main(String[] a){}
}

class TestVarTypeA{
    int a;

    public boolean test() {
        // Fails because it should return a boolean
        return 3;
    }
}
