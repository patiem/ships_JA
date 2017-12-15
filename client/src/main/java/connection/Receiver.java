package connection;

public interface Receiver {
    String readMessage();
    void distributeMessage();
}
