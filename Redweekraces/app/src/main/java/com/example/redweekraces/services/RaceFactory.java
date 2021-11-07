package com.example.redweekraces.services;

import com.example.redweekraces.models.Rider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RaceFactory {
    private final int[] TRACKS = {100, 200, 300};
    private final int RIDERS_AMOUNT = 99;
    private final int RACE_RIDERS_AMOUNT = 5;
    private final Random randomGenerator = new Random();
    private final List<Rider> riders;

    // Integer for easy sorting
    private Integer[] raceRidersIndexes;
    private static final RaceFactory instance = new RaceFactory();

    public static RaceFactory getInstance(){
        return instance;
    }

    private RaceFactory() {
        this.riders = new ArrayList<>();

        for (int i = 1; i <= this.RIDERS_AMOUNT; i++) {
            this.riders.add(new Rider(i + ""));
        }
        //System.out.println(Arrays.deepToString(this.riders.toArray()));

        this.raceRidersIndexes = new Integer[this.RACE_RIDERS_AMOUNT];
    }

    public String race() {
        // Start race and get winners with default race track (index 0 in tracks)
        Integer[] ridersIndexesRaceResult = startRace(0);

        // Called before endRace() to use steps info before racers reset
        terminalDisplayRaceResult(ridersIndexesRaceResult);

        // Get winners name for display
        String winnerName = this.riders.get(ridersIndexesRaceResult[0]).name();
        endRace();

        return winnerName;
    }

    public String[] prepareRaceRiders() {
        boolean[] numbers = new boolean[this.RIDERS_AMOUNT];

        // Choose random race riders
        for (int raceRidersIndex = 0; raceRidersIndex < this.RACE_RIDERS_AMOUNT; raceRidersIndex++) {
            int riderIndex = this.randomGenerator.nextInt(this.riders.size());

            if(numbers[riderIndex]){
                raceRidersIndex--;
            } else {
                numbers[riderIndex] = true;
                this.raceRidersIndexes[raceRidersIndex] = riderIndex;
            }
        }

        return racersNames();
    }

    private String[] racersNames(){
        String[] racersNames = new String[this.raceRidersIndexes.length];

        for (int nameIndex = 0; nameIndex < this.raceRidersIndexes.length; nameIndex++) {
            racersNames[nameIndex] = this.riders.get(this.raceRidersIndexes[nameIndex]).name();
        }

        return racersNames;
    }

    private Integer[] startRace(int trackIndex) {
        final int trackLength = this.TRACKS[trackIndex];
        final int ESTIMATED_TURNS = 10;
        final int DISTANCE_FACTOR = trackLength / ESTIMATED_TURNS;
        boolean raceOn = true;
        Rider currRider;

        while (raceOn) {
            for (int raceRidersIndex = 0; raceRidersIndex < this.RACE_RIDERS_AMOUNT; raceRidersIndex++) {
                currRider = this.riders.get(this.raceRidersIndexes[raceRidersIndex]);
                int addDistanceFactor = DISTANCE_FACTOR + currRider.stepsBonusChances();
                int trackDistance = this.randomGenerator.nextInt(addDistanceFactor) + 1;
                currRider.raiseDistanceInRace(trackDistance);

                if (currRider.distanceInRace() >= trackLength) {
                    raceOn = false;
                    break;
                }
            }

            Comparator<Integer> stepsCmp =  Comparator.comparing(nextRider -> riders.get(nextRider).distanceInRace());
            Arrays.sort(this.raceRidersIndexes, stepsCmp.reversed());
        }

        return this.raceRidersIndexes;
    }

    private void endRace() {
        // TODO: 10/30/2021 add user money arrangement
        Rider currRider = this.riders.get(this.raceRidersIndexes[0]);
        currRider.makeWinArrangement();

        // For the losers
        for (int raceRidersIndex = 1; raceRidersIndex < this.RACE_RIDERS_AMOUNT; raceRidersIndex++) {
            currRider = this.riders.get(this.raceRidersIndexes[raceRidersIndex]);
            currRider.makeLoseArrangement();
        }

        this.raceRidersIndexes = new Integer[this.RACE_RIDERS_AMOUNT];
    }

    private void terminalDisplayRaceResult(Integer[] ridersIndexesRaceResult) {
        Rider currRider;

        for (int raceRidersIndex = 0; raceRidersIndex < ridersIndexesRaceResult.length; raceRidersIndex++) {
            currRider = this.riders.get(ridersIndexesRaceResult[raceRidersIndex]);
            System.out.println("Place no. "
                    + (raceRidersIndex + 1)
                    + " =>  "
                    + currRider.name()
                    + " ||| With distance of => "
                    + currRider.distanceInRace());
        }
    }
}