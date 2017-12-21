package engine;

import java.io.BufferedReader;

public interface ShotReceiver {
    Shot readShot(BufferedReader currentReader);
}
