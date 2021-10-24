package races.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Race {
    private final int[] TRACKS = {100, 200, 300};
    private final int RIDERS_AMOUNT = 100;
    private final int RACE_RIDERS_AMOUNT = 5;
    private final Random randomGenerator = new Random();
    private final List<Rider> riders;

    private int[] raceRidersIndexes;
    private static final Race instance = new Race();

    public Race getInstance(){
        return this.instance;
    }

    private Race() {
        this.riders = new ArrayList<>();

        for (int i = 0; i < this.RIDERS_AMOUNT; i++) {
            this.riders.add(new Rider(i + ""));
        }
    }

    public void R() {
        chooseRidersForRace();

        // Default race track
        int[] ridersIndexesRaceResult = startRace(1);

        // Called before end race to use steps info before reset
        terminalDisplayRaceResult(ridersIndexesRaceResult);
        endRace();
    }

    private void chooseRidersForRace() {
        this.raceRidersIndexes = new int[this.RACE_RIDERS_AMOUNT];
        boolean[] numbers = new boolean[this.RIDERS_AMOUNT];

        // Choose random race riders
        for (int raceRidersIndex = 0; raceRidersIndex < this.RACE_RIDERS_AMOUNT; raceRidersIndex++) {
            int riderIndex = this.randomGenerator.nextInt(this.riders.size());

            if(numbers[riderIndex]){
                raceRidersIndex--;
            } else {
                numbers[raceRidersIndex] = true;
                this.raceRidersIndexes[riderIndex] = riderIndex;
            }
        }

        // TODO: 10/24/2021 check the random indexes && the init boolean array
    }

    private int[] startRace(int trackIndex) {
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

                // TODO: 10/24/2021 check that riders change in the arrayList
            }

            //TODO: sort raceRiders by race distance
        }

        return this.raceRidersIndexes;
    }

    private void endRace() {
        //TODO: 10/24/2021 take care of winner and losers stats by making func for it in Rider class
        Rider currRider = this.riders.get(this.raceRidersIndexes[0]);
        currRider.resetDistanceInRace();

        for (int raceRidersIndex = 1; raceRidersIndex < this.RACE_RIDERS_AMOUNT; raceRidersIndex++) {
            currRider = this.riders.get(this.raceRidersIndexes[raceRidersIndex]);
            currRider.resetDistanceInRace();
        }
    }

    private void terminalDisplayRaceResult(int[] ridersIndexesRaceResult) {
        Rider currRider;

        for (int raceRidersIndex = 0; raceRidersIndex < this.RACE_RIDERS_AMOUNT; raceRidersIndex++) {
            currRider = this.riders.get(this.raceRidersIndexes[raceRidersIndex]);
            System.out.println("Place no. "
                    + (raceRidersIndex + 1)
                    + " =>  "
                    + currRider.name()
                    + " ||| Final distance => "
                    + currRider.distanceInRace());
        }
    }
}
