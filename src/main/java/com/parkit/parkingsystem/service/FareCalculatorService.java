package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.util.ParkingUtils;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        long duration = ParkingUtils.CalculateDurationBetweenDate(ticket.getInTime(), ticket.getOutTime());

        if (duration <= Fare.FREE_DURATION_MINUTES) {
            ticket.setPrice(0);
        } else {
            switch (ticket.getParkingSpot().getParkingType()){
                case CAR: {
                    ticket.setPrice((duration * Fare.CAR_RATE_PER_HOUR)/60);
                    break;
                }
                case BIKE: {
                    ticket.setPrice((duration * Fare.BIKE_RATE_PER_HOUR)/60);
                    break;
                }
                default: throw new IllegalArgumentException("Unkown Parking Type");
            }
        }


    }
}