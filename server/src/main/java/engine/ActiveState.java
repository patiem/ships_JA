package engine;

import communication.MessageReceiver;
import communication.MessageSender;
import communication.Output;
import communication.PlayerRegistry;
import communication.SocketMessageSender;
import converter.dto.converter.TranscriptConverter;
import dto.TranscriptDTO;
import fleet.Fleet;
import messages.ServerLogger;
import messages.ShotMessage;
import common.model.Shot;
import persistence.database.query.dao.TranscriptDao;
import persistence.session.factory.configuration.SessionFactoryProvider;
import persistence.transcript.model.Transcript;
import responses.PlayResponse;
import responses.TranscriptResponse;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ActiveState implements GameState {
  private ServerLogger serverLogger = ServerLogger.getInstance();
  private final Round round = new Round();
  private final PlayerRegistry playerRegistry;
  private final Output output;
  private MessageReceiver messageReceiver = new MessageReceiver();
  private TranscriptDao transcriptDao = new TranscriptDao(SessionFactoryProvider.provide());

  public ActiveState(PlayerRegistry playerRegistry, Output output) {
    this.playerRegistry = playerRegistry;
    this.output = output;
  }

  @Override
  public GameState run() throws IOException {
    MessageSender messageSender = new SocketMessageSender();
    messageSender.sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());

    Socket socket = playerRegistry.getCurrentPlayer().getSocket();
    //todo first idea to separate shots from other msg(transcript)
    String s = messageReceiver.receiveMessage(socket);
    if(s.equals("transcript")) {
      List<Transcript> allData = transcriptDao.getAllData();
      List<TranscriptDTO> dtos = TranscriptConverter.convert(allData);
      messageSender.sendResponse(new TranscriptResponse(dtos), playerRegistry.getCurrentPlayer());
    }
    ShotMessage shotMessage = messageReceiver.receiveShotMessage(socket);
    Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();

    Shot shot = shotMessage.getShot();

    ShotResult result = round.fireShot(fleetUnderFire, shot);
    logShotInfo(shot, result);
    String transcriptMessage = "Player: %s fired a shot: %d; it's a %s";
    output.transcript(String.format(transcriptMessage, playerRegistry.getCurrentPlayerName(),
        shot.asInteger(), result.toString()));
    result.notifyClients(playerRegistry, shot);

    if (fleetUnderFire.isSunk()) {
      return new FinishedGame(playerRegistry, new SocketMessageSender(), output);
    }

    return this;
  }

  private void logShotInfo(final Shot shot, final ShotResult shotResult) {
    String messageTemplate = "Player: %s, shot: position: %s, shotState: %s;";
    String logMessage = String.format(messageTemplate, playerRegistry.getCurrentPlayerName(),
        shot.asInteger(), shotResult.toString());
    serverLogger.info(logMessage);
  }
}
