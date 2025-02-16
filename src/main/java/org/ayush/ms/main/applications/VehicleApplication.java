package org.ayush.ms.main.applications;

import org.ayush.ms.VehicleDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleApplication {
    static int fixCharge = 100;
    static int fixPriceRate = 15;

    public static void main(String[] args) {
        List<VehicleDetails> vehicleDetails = new ArrayList<>();
        vehicleDetails.add(new VehicleDetails("KA01HH1234", 1));
        vehicleDetails.add(new VehicleDetails("KA01HH1234", 2));
        vehicleDetails.add(new VehicleDetails("KA01HH1234", 3));

        vehicleDetails.add(new VehicleDetails("KA01HH1236", 1));
        vehicleDetails.add(new VehicleDetails("KA01HH1236", 2));
        vehicleDetails.add(new VehicleDetails("KA01HH1236", 3));
        vehicleDetails.add(new VehicleDetails("KA01HH1236", 4));
        vehicleDetails.add(new VehicleDetails("KA01HH1236", 5));

        vehicleDetails.add(new VehicleDetails("KA01HH1235", 1));
        vehicleDetails.add(new VehicleDetails("KA01HH1235", 2));

        VehicleApplication vehicleApplication = new VehicleApplication();
        Map<String, Integer> result = vehicleApplication.calculateCharge(vehicleDetails);
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static int getCharge(List<VehicleDetails> detailList) {
        int totalCharge = 0;

        if (detailList.size() == 1) {
            totalCharge = fixCharge;
            return totalCharge;
        }

        for (int i = 0; i < detailList.size() - 1; i++) {
            Integer pointA = detailList.get(i).getCheckPointId();
            Integer pointB = detailList.get(i + 1).getCheckPointId();

            Integer distance = someAPI(pointA, pointB);
            totalCharge += (distance * fixPriceRate);
        }
        return totalCharge + fixCharge;
    }

    private static Integer someAPI(Integer point1, Integer point2) {
        return Math.abs(point1 - point2);
    }

    private Map<String, Integer> calculateCharge(List<VehicleDetails> vehicleDetails) {
        Map<String, List<VehicleDetails>> vehicleWise = vehicleDetails.parallelStream()
                .collect(Collectors.groupingByConcurrent(VehicleDetails::getVehicleNumber));

        return vehicleWise.entrySet().parallelStream()
                .collect(Collectors.toConcurrentMap(
                        Map.Entry::getKey,
                        entry -> getCharge(entry.getValue()))
                );
    }

    public Map<String, Integer> calculateChargeNormalWay(List<VehicleDetails> vehicleDetails) {
        Map<String, Integer> result = new HashMap<>();
        Map<String, List<VehicleDetails>> vehicleWise = new HashMap<>();

        for (VehicleDetails vehicleDetail : vehicleDetails) {
            if (vehicleWise.containsKey(vehicleDetail.getVehicleNumber())) {
                vehicleWise.get(vehicleDetail.getVehicleNumber()).add(vehicleDetail);
            } else {
                List<VehicleDetails> details = new ArrayList<>();
                details.add(vehicleDetail);
                vehicleWise.put(vehicleDetail.getVehicleNumber(), details);
            }
        }

        for (Map.Entry<String, List<VehicleDetails>> entry : vehicleWise.entrySet()) {
            int charge = getCharge(entry.getValue());
            result.put(entry.getKey(), charge);
        }
        return result;
    }
}
