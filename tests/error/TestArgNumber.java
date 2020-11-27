class TestArgNumber{
    public static void main(String[] a){}
}

class TestArgNumberA{
    public int sum(int a, boolean b, int c) {
        return a + c;
    }

    public int test() {
        int res;
        // Fails because the third argument is missing
        res = this.sum(1, false);
        return res;
    }
}
