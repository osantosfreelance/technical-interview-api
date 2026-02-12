package com.ing.be.tia.statemachine.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.be.tia.statemachine.state.Listing;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListingLoader {
    public static List<Listing> loadFromJsonFile(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> rawList = mapper.readValue(new File(filePath), new TypeReference<List<Map<String, Object>>>() {});
        return rawList.stream().map(Listing::new).collect(Collectors.toList());
    }
}

