package com.ing.be.tia.controller;

import com.ing.be.tia.model.Listings;
import com.ing.be.tia.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lists")
public class ListingController {
    @Autowired
    private ListService listService;
    public ListingController(ListService listService){
        this.listService=listService;
    }

    @PostMapping("/listing/search")
//    public ResponseEntity<List<Listings>> SearchQuery(@RequestParam String searchQuery){
    public ResponseEntity<List<Listings>> SearchQuery(@RequestBody Map<String,String> searchQuery){
        System.out.println(searchQuery);
        List<Listings> searchResult = listService.searchList(searchQuery.get("searchQuery"));
        return new ResponseEntity<>(searchResult, HttpStatus.OK);

    }
}
