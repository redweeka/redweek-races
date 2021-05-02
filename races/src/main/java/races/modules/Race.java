package races.modules;

import java.util.Random;

public class Race {
    public final int[] TRACKS = {100, 200, 300};

    private Rider[] riders;
    private Random randomGenerator;
    private Rider[] racersRiders;

    public Race() {
        this.riders = new Rider[99];

        for (int i = 0; i < this.riders.length; i++) {
            this.riders[i] = new Rider(i + "");
        }

        this.randomGenerator = new Random();
        this.racersRiders = new Rider[5];
    }

    public void prepareRace(){
        //choose 5 riders randomly
        int ridersCnt = 0;

        while (ridersCnt < racersRiders.length){
            int riderIndex = this.randomGenerator.nextInt(this.riders.length);

            if(this.riders[riderIndex] != null){
                racersRiders[ridersCnt] = this.riders[riderIndex];
                this.riders[riderIndex] = null;
                ridersCnt++;
            }
        }
    }
}
