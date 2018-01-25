package connection.chain;

public class ChainConfigFactory {

  private ChainConfigFactory() {
  }

  public static Chain configureChainOfResponsibilities() {
    Chain playLink = new PlayLink();
    Chain hitLink = new HitLink();
    playLink.setNextChain(hitLink);

    Chain opponentHitLink = new OpponentHitLink();
    hitLink.setNextChain(opponentHitLink);

    Chain hitAgainLink = new HitAgainLink();
    opponentHitLink.setNextChain(hitAgainLink);

    Chain sunkLink = new SunkLink();
    hitAgainLink.setNextChain(sunkLink);

    Chain winLink = new WinLink();
    sunkLink.setNextChain(winLink);

    Chain lostLink = new LostLink();
    winLink.setNextChain(lostLink);

    Chain endLink = new EndLink();
    lostLink.setNextChain(endLink);

    return playLink;
  }
}
