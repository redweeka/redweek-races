package races.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Race {
    public final int[] TRACKS = {100, 200, 300};
    public final int RIDERS_SUM = 100;
    public final int RACE_RIDERS_SUM = 5;

    private List<Rider> riders;
    private Random randomGenerator;
    private List<Rider> raceRiders;

    public Race() {
        this.riders = new ArrayList<>();

        for (int i = 0; i < RIDERS_SUM; i++) {
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
        //choose random riders
        for (int i = 0; i < RACE_RIDERS_SUM; i++) {
            int riderIndex = this.randomGenerator.nextInt(this.riders.size());
            this.raceRiders.add(this.riders.remove(riderIndex));
        }
    }

    private List<Rider> startRace(int trackIndex) {
        final int ESTIMATED_TURNS = 10;
        final int ADD_DISTANCE_FACTOR = TRACKS[trackIndex] / ESTIMATED_TURNS;
        boolean raceOn = true;

        while (raceOn) {
            for (int raceRidersIndex = 0; raceRidersIndex < RACE_RIDERS_SUM; raceRidersIndex++) {
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

        for (int raceRidersIndex = 1; raceRidersIndex < RACE_RIDERS_SUM; raceRidersIndex++) {
            this.raceRiders.get(raceRidersIndex).resetDistanceInRace();
        }

        this.riders.addAll(this.raceRiders);
        this.raceRiders = new ArrayList<>();
    }

    private void terminalDisplayRaceResult(List<Rider> raceSummary) {
        for (int raceRidersIndex = 0; raceRidersIndex < RACE_RIDERS_SUM; raceRidersIndex++) {
            System.out.println("Place no. "
                    + (raceRidersIndex + 1)
                    + " =>  "
                    + raceSummary.get(raceRidersIndex).name()
                    + " ||| Final distance => "
                    + raceSummary.get(raceRidersIndex).distanceInRace());
        }
    }
}
