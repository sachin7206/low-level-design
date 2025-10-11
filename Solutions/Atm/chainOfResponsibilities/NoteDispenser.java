package chainOfResponsibilities;

public abstract class NoteDispenser implements DispenseChain {
    private DispenseChain nextChain;
    private int numOfNotes;
    private final int noteValue;

    public NoteDispenser(int numOfNotes, int noteValue) {
        this.numOfNotes = numOfNotes;
        this.noteValue = noteValue;
    }

    @Override
    public synchronized void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public synchronized boolean canDispenseCash(int amount) {
        if (amount < 0) return false;
        if (amount == 0) return true;
        int numToUse = Math.min(amount / noteValue, this.numOfNotes);
        int remainingAmount = amount - (numToUse * noteValue);

        if (remainingAmount == 0) return true;
        if (this.nextChain != null) {
            return this.nextChain.canDispenseCash(remainingAmount);
        }
        return false;
    }

    @Override
    public void dispenseCash(int amount) {
       if(amount >= noteValue) {
           int numOfNotesToDispense = Math.min(amount / noteValue, this.numOfNotes);
           if(numOfNotesToDispense > 0) {
               this.numOfNotes -= numOfNotesToDispense;
               int remainingAmount = amount - (numOfNotesToDispense * noteValue);
               System.out.println("Dispensing " + numOfNotesToDispense + " notes of " + noteValue);
               if(remainingAmount != 0 && this.nextChain != null) {
                   this.nextChain.dispenseCash(remainingAmount);
               }
           } else {
               if(this.nextChain != null) {
                   this.nextChain.dispenseCash(amount);
               }
           }
       } else {
           if(this.nextChain != null) {
               this.nextChain.dispenseCash(amount);
           }
       }
    }
}
