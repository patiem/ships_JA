package engine;

import model.Shot;

import java.io.BufferedReader;

/**
 * It receives data on each shot.
 *
 * @author Emilia Ciastek
 * @version 1.5
 */
interface ShotReceiver {
  Shot readShot(BufferedReader currentReader);
}
