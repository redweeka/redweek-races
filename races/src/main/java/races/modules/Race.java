package races.modules;

import java.util.*;

public class Race {
    private final int[] TRACKS = {100, 200, 300};
    private final int RIDERS_AMOUNT = 99;
    private final int RACE_RIDERS_AMOUNT = 5;
    private final Random randomGenerator = new Random();
    private final List<Rider> riders;

    // Integer for easy sorting
    private Integer[] raceRidersIndexes;
    private static final Race instance = new Race();

    public static Race getInstance(){
        return instance;
    }

    private Race() {
        this.riders = new ArrayList<>();

        for (int i = 1; i <= this.RIDERS_AMOUNT; i++) {
            this.riders.add(new Rider(i + ""));
        }
        //System.out.println(Arrays.deepToString(this.riders.toArray()));

        this.raceRidersIndexes = new Integer[this.RACE_RIDERS_AMOUNT];
    }

    public void R() {
        chooseRidersForRace();

        // Default race track
        Integer[] ridersIndexesRaceResult = startRace(0);

        // Called before end race to use steps info before reset
        terminalDisplayRaceResult(ridersIndexesRaceResult);
        endRace();

    }

    private void chooseRidersForRace() {
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
        //TODO: 10/24/2021 take care of winner and losers stats by making func for it in Rider class
        Rider currRider = this.riders.get(this.raceRidersIndexes[0]);
        currRider.resetDistanceInRace();

        // For the losers
        for (int raceRidersIndex = 1; raceRidersIndex < this.RACE_RIDERS_AMOUNT; raceRidersIndex++) {
            currRider = this.riders.get(this.raceRidersIndexes[raceRidersIndex]);
            currRider.resetDistanceInRace();
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
