package leetcode.veryCommon.design;

import java.util.HashMap;

public class DesignUndergroundSystem {
    class UndergroundSystem {
        class UserInteraction {
            int id;
            String station;
            int time;

            public UserInteraction(int id, String station, int time) {
                this.id = id;
                this.station = station;
                this.time = time;
            }
        }

        HashMap<String, Integer[]> routeHist;
        HashMap<Integer, UserInteraction> checkInStat;

        public UndergroundSystem() {
            routeHist = new HashMap<>();
            checkInStat = new HashMap<Integer, UserInteraction>();
        }

        public void checkIn(int id, String stationName, int t) {
            checkInStat.put(id, new UserInteraction(id, stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            if (checkInStat.containsKey(id)) {
                UserInteraction checkIn = checkInStat.get(id);
                int timeTravelled = t - checkIn.time;
                if (!routeHist.containsKey(stationName + "-" + checkIn.station)) {
                    Integer[] travelAggr = {timeTravelled,1};
                    routeHist.put(stationName + "-" + checkIn.station, travelAggr);
                    Integer[] travelAggr2 = {timeTravelled,1};
                    routeHist.put(checkIn.station + "-" + stationName, travelAggr2);
                } else {
                    routeHist.get(stationName + "-" + checkIn.station)[0] += timeTravelled;
                    routeHist.get(stationName + "-" + checkIn.station)[1]++;
                    routeHist.get(checkIn.station + "-" + stationName)[0] += timeTravelled;
                    routeHist.get(checkIn.station + "-" + stationName)[1]++;
                }
                checkInStat.remove(id);
            }
        }

        public double getAverageTime(String startStation, String endStation) {
            Integer[] travelAggr = routeHist.get(startStation + "-" + endStation);
            System.out.println(travelAggr[0]+"-->"+travelAggr[1]);
            return (double)travelAggr[0] / travelAggr[1];
        }
    }
}
