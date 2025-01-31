package com.project.ETour.service;

import com.project.ETour.model.Destination;

import java.util.List;

public interface DestinationService {

    List<Destination> fetchAllDestination();

    List<Destination> fetchDestinationsByType(String type);

}
