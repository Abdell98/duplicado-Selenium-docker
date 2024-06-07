package com.vinsguru.tests.flightreservation.model;

public record FlightReservationTestData(String firstName,
                                        String LastName,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String zip,
                                        String passengersCount,
                                        String expectedPrice) {
}
