package connection.chain;

public class ChainConfigFactory {

  private ChainConfigFactory() {
  }

  public static Chain configureChainOfResponsibilities() {
    Chain firstLinkInTheChain = new HitLink();
    Chain chain2 = new MissedLink();
    Chain chain3 = new WinLink();
    Chain chain4 = new PlayLink();
    Chain chain5 = new LostLink();
    Chain chain6 = new OpponentHitLink();
    Chain chain7 = new OpponentMissedLink();
    Chain chain8 = new EndLink();

    firstLinkInTheChain.setNextChain(chain2);
    chain2.setNextChain(chain3);
    chain3.setNextChain(chain4);
    chain4.setNextChain(chain5);
    chain5.setNextChain(chain6);
    chain6.setNextChain(chain7);
    chain7.setNextChain(chain8);

    return firstLinkInTheChain;
  }
}
