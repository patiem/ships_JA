package connection.chain;

public class ChainConfigFactory {

private ChainConfigFactory() {}

public static Chain configureChainOfResponsibilities() {
    Chain firstLinkInTheChain = new HitLink();
    Chain chain2 = new MissedLink();
    firstLinkInTheChain.setNextChain(chain2);

    Chain chain3 = new WinLink();
    chain2.setNextChain(chain3);

    Chain chain4 = new PlayLink();
    chain3.setNextChain(chain4);

    Chain chain5 = new LostLink();
    chain4.setNextChain(chain5);

    Chain chain6 = new OpponentHitLink();
    chain5.setNextChain(chain6);

    Chain chain7 = new OpponentMissedLink();
    chain6.setNextChain(chain7);

    Chain chain8 = new EndLink();
    chain7.setNextChain(chain8);

    return firstLinkInTheChain;
    }
}
