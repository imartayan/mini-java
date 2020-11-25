package mj.type_checker;

import mj.syntax.Identifier;

public class TypeError extends Exception {
    public final boolean hasLocation;

    public TypeError(String message, Identifier id) {
        super(message + 
                "At line " + Integer.toString(id.line) + 
                ", column" + Integer.toString(id.col));
        this.hasLocation = true;
    }

    public TypeError(String message) {
        super(message);
        this.hasLocation = false;
    }

    public TypeError addLocation(TypeError e, Identifier id) {
        if (! e.hasLocation) {
            return new TypeError(e.getMessage(), id);
        } else {
            return e;
        }
    }

    private static final long serialVersionUID = 1L;

}
