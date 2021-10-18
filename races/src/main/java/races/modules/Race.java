package races.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Race {
    private final int[] TRACKS = {100, 200, 300};
    private final int RIDERS_AMOUNT = 100;
    private final int RACE_RIDERS_AMOUNT = 5;

    private List<Rider> riders;
    private Random randomGenerator;
    private List<Rider> raceRiders;
    private Race instance = new Race();

    public Race getInstance(){
        return instance;
    }

    private Race() {
        this.riders = new ArrayList<>();

        for (int i = 0; i < RIDERS_AMOUNT; i++) {
            this.riders.add(new Rider(i + ""));
        }

        this.randomGenerator = new Random();
        this.raceRiders = new ArrayList<>();
    }

    public void R() {
        prepareRace();

        //default race track
        List<Rider> raceResult = startRace(1);
        endRace();
        terminalDisplayRaceResult(raceResult);
    }

    private void prepareRace() {
        // TODO: 10/18/2021 refactor raceRiders primitive array of riders indexes (call it raceRidersIndexes)
        //choose random riders
        for (int i = 0; i < RACE_RIDERS_AMOUNT; i++) {
            int riderIndex = this.randomGenerator.nextInt(this.riders.size());
            this.raceRiders.add(this.riders.remove(riderIndex));
        }
    }

    private List<Rider> startRace(int trackIndex) {
        final int ESTIMATED_TURNS = 10;
        final int ADD_DISTANCE_FACTOR = TRACKS[trackIndex] / ESTIMATED_TURNS;
        boolean raceOn = true;

        while (raceOn) {
            for (int raceRidersIndex = 0; raceRidersIndex < RACE_RIDERS_AMOUNT; raceRidersIndex++) {
                int additionalDistance = this.randomGenerator.nextInt(ADD_DISTANCE_FACTOR) + 1;
                this.raceRiders.get(raceRidersIndex).raiseDistanceInRace(additionalDistance);

                if (this.raceRiders.get(raceRidersIndex).distanceInRace() >= TRACKS[trackIndex]) {
                    raceOn = false;
                    break;
                }
            }

            //TODO: sort raceRiders by race distance
        }

        //TODO: deep copy
        return this.raceRiders;
    }

    private void endRace() {
        //TODO: take care of winner and losers stats
        this.raceRiders.get(0).resetDistanceInRace();

        for (int raceRidersIndex = 1; raceRidersIndex < RACE_RIDERS_AMOUNT; raceRidersIndex++) {
            this.raceRiders.get(raceRidersIndex).resetDistanceInRace();
        }

        this.riders.addAll(this.raceRiders);
        this.raceRiders = new ArrayList<>();
    }

    private void terminalDisplayRaceResult(List<Rider> raceSummary) {
        for (int raceRidersIndex = 0; raceRidersIndex < RACE_RIDERS_AMOUNT; raceRidersIndex++) {
            System.out.println("Place no. "
                    + (raceRidersIndex + 1)
                    + " =>  "
                    + raceSummary.get(raceRidersIndex).name()
                    + " ||| Final distance => "
                    + raceSummary.get(raceRidersIndex).distanceInRace());
        }
    }
}
