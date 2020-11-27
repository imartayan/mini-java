class TestArgType{
    public static void main(String[] a){}
}

class TestArgTypeA{
    public int sum(int a, boolean b, int c) {
        return a + c;
    }

    public int test() {
        int res;
        // Fails because the second argument is not a boolean
        res = this.sum(1, 2, 3);
        return res;
    }
}
