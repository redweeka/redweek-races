package races.modules;

public class Rider {
    public final double DEFAULT_PRICE = 2.0;
    public final double MIN_VAL_PRICE = 1.1;
    public final double CHANGE_PRICE_FACTOR = 0.1;
    public final int DEFAULT_STEPS_CHANCES = 10;
    public final int CHANGE_STEPS_CHANCES_FACTOR = 1;

    private String name;
    private double price;
    private int stepsChances;
    private int wins;
    private int loses;

    public Rider(String name) {
        this.name = name;
        this.price = DEFAULT_PRICE;
        this.stepsChances = DEFAULT_STEPS_CHANCES;
        this.wins = 0;
        this.loses = 0;
    }

    public String name() {
        return this.name;
    }

    public void rename(String name) {
        this.name = name;
    }

    public double price() {
        return this.price;
    }

    public void raisePrice() {
        this.price += CHANGE_PRICE_FACTOR;
    }

    public void reducePrice() {
        if (this.price >= (MIN_VAL_PRICE + CHANGE_PRICE_FACTOR)) {
            this.price -= CHANGE_PRICE_FACTOR;
        }
    }

    public int stepsChances() {
        return this.stepsChances;
    }

    public void resetStepsChances() {
        this.stepsChances = DEFAULT_STEPS_CHANCES;
    }

    public void raiseStepsChances() {
        this.stepsChances += CHANGE_STEPS_CHANCES_FACTOR;
    }

    public int wins() {
        return this.wins;
    }

    public void addWin() {
        this.wins++;
    }

    public int loses() {
        return this.loses;
    }

    public void addLose() {
        this.loses++;
    }

    public int racesSum(){
        return this.wins + this.loses;
    }
}
