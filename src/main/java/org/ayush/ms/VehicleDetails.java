package org.ayush.ms;

import lombok.Getter;

import java.time.Instant;

@Getter
public class VehicleDetails {
    private final String vehicleNumber;
    private final Integer checkPointId;
    private final Instant instant;

    public VehicleDetails(String vehicleNumber, int checkPointId) {
        this.vehicleNumber = vehicleNumber;
        this.checkPointId = checkPointId;
        this.instant = Instant.now();
    }
}
