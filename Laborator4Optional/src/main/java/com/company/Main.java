package com.company;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        Resident[] r = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Resident(new Faker().name().fullName(), false))
                .toArray(Resident[]::new);

        Hospital[] h = IntStream.rangeClosed(0,2)
                .mapToObj(i -> new Hospital(new Faker().medical().hospitalName()))
                .toArray(Hospital[]::new);

        List<Resident> residentList = new ArrayList<>();
        residentList.addAll(Arrays.asList(r));

        Collections.sort(residentList,
                Comparator.comparing(Resident::getName));

        Set<Hospital> hospitalSet = new TreeSet<>();
        hospitalSet.addAll(Arrays.asList(h));
        h[0].setCapacity(1);
        h[1].setCapacity(2);
        h[2].setCapacity(2);

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0], h[2]));
        System.out.println("Printing residents' preferences:\n" + resPrefMap.toString() + "\n");

        Map<Hospital, List<Resident>> hosPrefMap = new TreeMap<>();
        hosPrefMap.put(h[0], Arrays.asList(r[3], r[0], r[1], r[2]));
        hosPrefMap.put(h[1], Arrays.asList(r[0], r[2], r[1]));
        hosPrefMap.put(h[2], Arrays.asList(r[0], r[1], r[3]));
        System.out.println("Printing hospitals' preferences:\n" + hosPrefMap.toString() + "\n");

        Problem pb = new Problem();
        pb.createMatching(hosPrefMap);
        pb.printMatching(h);
        System.out.println("\n" + pb.getMatching());
        System.out.println("Matching is stable = " + pb.isStable(hosPrefMap, resPrefMap));
    }
}