package mj.syntax;

public class UniqueIdentifier extends Identifier{
    public static int comptor = 0;

    public UniqueIdentifier() {
        super("UniqueIdentifier"+comptor);
        comptor++;
    }
}
