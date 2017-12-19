package communication;

public class LanguageVersion {

    public static final String CONFIRM_CONNECTION = "You are connected";
    public static final String WAIT_FOR_SECOND_PLAYER = "You're first PlayerClient. Please wait for second PlayerClient.";
    public static final String FLEET_RECEIVED = "Server received your fleet";

    private String serverRunning = "Server running ! ";

    public String getServerRunning() {
        return serverRunning;
    }

}
