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

    public void prepareRace(){
        //choose random riders
        for (int i = 0; i < RACE_RIDERS_SUM; i++) {
            int riderIndex = this.randomGenerator.nextInt(this.riders.size());
            this.raceRiders.add(this.riders.remove(riderIndex));
        }
    }

    
}
