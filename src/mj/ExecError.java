package mj;

import mj.syntax.Identifier;

public class ExecError extends Exception {
    public final boolean hasLocation;

    public ExecError(String message, Identifier id) {
        super(message + 
                "At line " + Integer.toString(id.line) + 
                ", column" + Integer.toString(id.col));
        this.hasLocation = true;
    }

    public ExecError(String message) {
        super(message);
        this.hasLocation = false;
    }

    public ExecError addLocation(ExecError e, Identifier id) {
        if (! e.hasLocation) {
            return new ExecError(e.getMessage(), id);
        } else {
            return e;
        }
    }

    private static final long serialVersionUID = 1L;

}
