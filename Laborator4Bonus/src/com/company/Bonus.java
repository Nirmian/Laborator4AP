package com.company;

import java.util.Arrays;

public class Bonus {
    private int preferSize;
    private int prefer[][];

    public int[][] getPrefer() {
        return prefer;
    }

    public void setPrefer(int[][] prefer) {
        this.prefer = prefer;
    }

    /**
     * @param prefer matrix with preferSize*2 rows and preferSize columns containing preferences of residents and hospitals
     * @param h hospital
     * @param r resident
     * @param r1 resident1
     * @return true if hospital h prefers resident1 over resident
     */
    public boolean hosPrefersR1OverR(int prefer[][], int h, int r, int r1) {
        for (int i = 0; i < preferSize; i++) {
            if (prefer[h][i] == r1)
                return true;

            if (prefer[h][i] == r)
                return false;
        }
        return false;
    }

    /**
     * prefer is the matrix of preferences that contains the preferences of the residents on the first preferSize rows
       from 0 to preferSize-1 and the preferences of the hospitals on the next preferSize rows from preferSize to
       (preferSize * 2) - 1. The algorithm ensures the making of a stableMatching given a valid matrix.
     * @param prefer matrix with preferSize*2 rows and preferSize columns containing preferences of residents and hospitals
     */
    public void stableMatching(int prefer[][]) {

        preferSize = prefer.length / 2;
        int hosResident[] = new int[preferSize];
        boolean resHired[] = new boolean[preferSize];

        Arrays.fill(hosResident, -1);
        int toHire = preferSize;

        while (toHire > 0) {
            int resIndex;

            for (resIndex = 0; resIndex < preferSize; resIndex++)
                if (resHired[resIndex] == false)
                    break;

            for (int i = 0; i < preferSize && resHired[resIndex] == false; i++) {
                int hosIndex = prefer[resIndex][i];

                if (hosResident[hosIndex - preferSize] == -1) {
                    hosResident[hosIndex - preferSize] = resIndex;
                    resHired[resIndex] = true;
                    toHire--;
                }

                else {
                    int res1Index = hosResident[hosIndex - preferSize];

                    if (hosPrefersR1OverR(prefer, hosIndex, resIndex, res1Index) == false) {
                        hosResident[hosIndex - preferSize] = resIndex;
                        resHired[resIndex] = true;
                        resHired[res1Index] = false;
                    }
                }
            }
        }

        System.out.println("Hospital \t Resident");
        for (int i = 0; i < preferSize; i++)
        {
            System.out.print(" ");
            System.out.println(i + "\t\t\t\t" +
                    hosResident[i]);
        }
    }
}
