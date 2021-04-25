# redweek races

- 99 riders
- 5 riders every race
- race every day
- 3 different length tracks (100, 200, 300)
- races play like this: 
    - a random rider starts the first round of steps (gets between 1 - 10 (multiply by track length / 100))
    - every next round until the end of the race, by order from the furthest one till the closest one, will get their steps (the idea is to see who won first, and its reasonable that the furthest one will cross the finish line first)
- for every rider the starting price is the bet X2
- for every rider race
    - if won
        - decrease his price by 0.1 (the minimum price for a bet is 1.1)
        - raise his chances to win (by raising his random steps chances)
    - if lose
        - raise his price by 0.1


