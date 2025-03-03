package com.ing.be.tia.service;

import com.ing.be.tia.model.Listings;
import com.ing.be.tia.repository.ListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListService {
    private final ListRepository listRepository;

    public ListService(ListRepository listRepository){
        this.listRepository= listRepository;
    }

    public Iterable<Listings> list() {
        return listRepository.findAll();
    }
    public List save(Listings list){
            return (List) listRepository.save(list);
    }

    //Below method will save or update data
    public Iterable<Listings> save(List<Listings> lists){
        return listRepository.saveAll(lists);
    }

    public List<Listings> searchList(String searchQuery) {
        System.out.println(searchQuery);
        return listRepository.searchList(searchQuery);

    }
}
