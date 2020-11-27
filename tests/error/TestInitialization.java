class TestInit{
    public static void main(String[] a){}
}

class TestInitA{
    public boolean initA() {
        int a;
        int b;
        // Fails because a is not initialized
        b = a + 1;
        return true;
    }
}
