package game.engine.exceptions;

public class InvalidMoveException extends GameActionException {

    private static final String MSG = "Invalid move attempted";

    public InvalidMoveException() {
        super(MSG);
    }

    public InvalidMoveException(String msg) {
        super(msg);
    }
}
