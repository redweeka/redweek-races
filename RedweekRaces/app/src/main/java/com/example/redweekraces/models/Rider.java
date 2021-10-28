package com.example.redweekraces.models;

public class Rider implements Comparable<Rider>{
    private final double DEFAULT_PRICE = 2.0;
    private final double MIN_VAL_PRICE = 1.1;
    private final double CHANGE_PRICE_FACTOR = 0.1;
    private final int CHANGE_STEPS_CHANCES_FACTOR = 1;

    private String name;
    private double price;
    private int stepsBonusChance;
    private int wins;
    private int loses;
    private int distanceInRace;

    public Rider(String name) {
        this.name = name;
        this.price = DEFAULT_PRICE;
        this.stepsBonusChance = 0;
        this.wins = 0;
        this.loses = 0;
        this.distanceInRace = 0;
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
        if (this.price > MIN_VAL_PRICE) {
            this.price -= CHANGE_PRICE_FACTOR;
        }
    }

    public int stepsBonusChances() {
        return this.stepsBonusChance;
    }

    public void resetStepsBonusChances() {
        this.stepsBonusChance = 0;
    }

    public void raiseStepsBonusChances() {
        this.stepsBonusChance += CHANGE_STEPS_CHANCES_FACTOR;
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

    public int distanceInRace(){
        return this.distanceInRace;
    }

    public void raiseDistanceInRace(int additionalDistance){
        this.distanceInRace += additionalDistance;
    }

    public void resetDistanceInRace(){
        this.distanceInRace = 0;
    }

    @Override
    public int compareTo(Rider rider) {
        // Compare by steps
        return this.distanceInRace() - rider.distanceInRace();
    }

    @Override
    public String toString() {
        return "Rider " + name +
                ", price=" + price +
                ", stepsBonusChance=" + stepsBonusChance +
                ", wins=" + wins +
                ", loses=" + loses +
                ", distanceInRace=" + distanceInRace + " ~~~~~~ ";
    }
}