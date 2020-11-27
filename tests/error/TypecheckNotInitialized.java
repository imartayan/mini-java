class CheckNotInitialized{
    public static void main(String[] a){
        System.out.println(42);
    }
}

class CheckNotInitializedA{
    public boolean initA() {
        int a;
        int b;
        b = a + 1;
        return true;
    }
}
