package responses;

public abstract class Response {

    PerformAction performAction;
    abstract ResponseHeader getHeader();

    public void makeMove(SuperiorMessage message) {
        performAction.act(message);
    }
}