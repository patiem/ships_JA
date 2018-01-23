package connection.chain;

public class ChainConfigFactory {

  private ChainConfigFactory() {
  }

  public static Chain configureChainOfResponsibilities() {
    Chain hitLink = new HitLink();
    Chain missedLink = new MissedLink();
    hitLink.setNextChain(missedLink);

    Chain winLink = new WinLink();
    missedLink.setNextChain(winLink);

    Chain playLink = new PlayLink();
    winLink.setNextChain(playLink);

    Chain lostLink = new LostLink();
    playLink.setNextChain(lostLink);

    Chain opponentHitLink = new OpponentHitLink();
    lostLink.setNextChain(opponentHitLink);

    Chain opponentMissedLink = new OpponentMissedLink();
    opponentHitLink.setNextChain(opponentMissedLink);

    Chain hitAgainLink = new HitAgainLink();
    opponentMissedLink.setNextChain(hitAgainLink);

    Chain sunkLink = new SunkLink();
    hitAgainLink.setNextChain(sunkLink);

    Chain endLink = new EndLink();
    sunkLink.setNextChain(endLink);

    return hitLink;
  }
}
