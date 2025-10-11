package chainOfResponsibilities;

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispenseCash(int amount);
    boolean canDispenseCash(int amount);
}
