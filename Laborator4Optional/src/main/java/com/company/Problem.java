package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Problem {

    private Map<Resident, Hospital> matching = new HashMap<>();

    public Map<Resident, Hospital> getMatching() {
        return matching;
    }

    public void addPair(Resident resident, Hospital hospital) {
        matching.put(resident, hospital);
    }

    /**
     * Takes all the hospitals' preferences as an argument. We then iterate through each individual hospital's
        list of preferred residents and use a First come, first served type of solution. While the hospital has capacity
        for more residents and if the resident isn't hired already make pair of (resident, hospital), mark resident as
        hired and decrease the hospital's capacity.
     * @param hosPrefMap hospitals' list of preferred residents
     */
    public void createMatching(Map<Hospital, List<Resident>> hosPrefMap) {
        Iterator<Map.Entry<Hospital, List<Resident>>> itr = hosPrefMap.entrySet().iterator();

        while(itr.hasNext()) {
            Map.Entry<Hospital, List<Resident>> entry = itr.next();
            Hospital hospital = entry.getKey();
            List<Resident> residentList = entry.getValue();
            for (int i = 0; i < residentList.size(); i++) {
                if(hospital.getCapacity() != 0 && residentList.get(i).getHired() != true) {
                    hospital.addResident(residentList.get(i));
                    residentList.get(i).setHired(true);
                    addPair(residentList.get(i), hospital);
                }
            }
        }
    }

    public void printMatching(Hospital[] hospitals) {
        for(int i = 0; i < hospitals.length; i++) {
            System.out.println(hospitals[i].getName() + " " + hospitals[i].getPersonnel());
        }
    }

    /**
     *  Iterate through each resident's list of preferred hospitals. If there is a resident of the first matched set
        which prefers another hospital of the second matched set over the element to which resident is already matched,
        and the hospital also prefers this resident over the element to which hospital is already matched then
        matching is NOT stable.
     * @param hosPrefMap hospitals' list of preferred residents
     * @param resPrefMap residents' list of preferred hospitals
     * @return true if matching is stable, otherwise false
     */
    public boolean isStable(Map<Hospital, List<Resident>> hosPrefMap, Map<Resident, List<Hospital>> resPrefMap) {
        Iterator<Map.Entry<Resident, List<Hospital>>> itr = resPrefMap.entrySet().iterator();

        while(itr.hasNext()) {
            Map.Entry<Resident, List<Hospital>> entry = itr.next();
            Resident resident = entry.getKey();
            List<Hospital> hosPreferences = entry.getValue();
            for (int i = 0; i < hosPreferences.size(); i++) {
                if(hosPreferences.get(i) != matching.get(resident)) {
                    int index = 0;
                    for(Resident resident1 : hosPrefMap.get(matching.get(resident))) {
                        if(resident1 == resident && !hosPreferences.get(i).getPersonnel().contains(resident)) {
                            return false;
                        }
                        else if(hosPreferences.get(i).getPersonnel().contains(resident1)) break;
                    }
                }
                else break;
            }
        }
        return true;
    }
}
