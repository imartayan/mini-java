class TestVarType{
    public static void main(String[] a){}
}

class TestVarTypeA{
    int a;

    public int test() {
        // Fails because a should be an int
        a = false;
        return 0;
    }
}
