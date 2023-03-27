
public class Table {

    private int bet;
    private int tableType;
    private boolean fast;
    private boolean headsUp;
    private boolean rematch;

    public Table(int bet, boolean fast, boolean headsUp, boolean rematch) {
        this.bet = bet;
        this.fast = fast;
        this.headsUp = headsUp;
        this.rematch = rematch;
        this.tableType = (fast ? 1 : 0) + (headsUp ? 2 : 0) + (rematch ? 4 : 0);
    }
    
    public int getBet() {
        return bet;
    }

    public int getTableType() {
        return tableType;
    }
    public boolean isFast() {
        return fast;
    }

    public boolean isHeadsUp() {
        return headsUp;
    }

    public boolean isRematch() {
        return rematch;
    }

    @Override
    public String toString() {
        return "Masa{" + "Bahis=" + bet +"$" + ", Masa Tipi=" + (fast ? "Hızlı" : " ") + (headsUp ? " Teketek" : " ") + (rematch ? " Rövanş" : " ") + "}";
    }
}
