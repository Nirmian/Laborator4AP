package com.company;

public class Main {

    public static void main(String[] args) {

        int prefer[][] = new int[][]{{5, 4, 6, 7},
                                    {7, 4, 5, 6},
                                    {4, 6, 5, 7},
                                    {5, 6, 4, 7},
                                    {0, 2, 1, 3},
                                    {2, 3, 0, 1},
                                    {3, 1, 2, 0},
                                    {2, 1, 0, 3}};

        Bonus bonus = new Bonus();
        bonus.stableMatching(prefer);
    }
}