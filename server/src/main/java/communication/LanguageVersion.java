package communication;

public class LanguageVersion {

    static final String CONFIRM_CONNECTION = "You are connected";
    static final String WAIT_FOR_SECOND_PLAYER = "You're first PlayerClient. Please wait for second PlayerClient.";
    static final String FLEET_RECEIVED = "Server received your fleet";

    private String serverRunning = "Server running ! ";

    String getServerRunning() {
        return serverRunning;
    }

}
