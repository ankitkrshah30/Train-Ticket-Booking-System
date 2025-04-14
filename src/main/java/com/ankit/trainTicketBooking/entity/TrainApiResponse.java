/*
package com.ankit.trainTicketBooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainApiResponse {
    private String trainName;
    private String trainNo;
    private StationInfo source;
    private StationInfo destination;
    private List<RouteInfo> trainRoute;
    @Data
    public static class StationInfo{
        private String stationCode;
        private String stationName;
    }
    @Data
    public static class RouteInfo{
        private String stationCode;
        private String stationName;
        private String arrivalTime;
        private String departureTime;
        private String distance;
    }
}
*/
