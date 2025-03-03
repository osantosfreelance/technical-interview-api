package com.ing.be.tia.repository;
import com.ing.be.tia.model.Listings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends CrudRepository<Listings, String> {
    @Query("SELECT l from Listings l WHERE " +
            "LOWER(l.episodeTitle) LIKE LOWER(CONCAT('%',:searchQuery ,'%')) OR "+
            "LOWER(l.showType) LIKE LOWER(CONCAT('%',:searchQuery ,'%')) OR "+
            "LOWER(l.description) LIKE LOWER(CONCAT('%',:searchQuery ,'%'))")
//    @Query("select l from Listings l WHERE l.number=161")
    List<Listings> searchList(String searchQuery);
}