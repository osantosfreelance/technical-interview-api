package com.ing.be.tia.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Listings {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @JsonIgnore
    private int Number;
    private int channelNumber;
    private int subChannelNumber;
    private int stationID;
    private String name;
    private String callsign;
    private String network;
    private String StationType;
    private String webLink;
    private String logoFilename;
    private int listingID;
    private String listDateTime;
    private int duration;
    private int showID;
    private int seriesID;
    private String showName;
    private String episodeTitle;
    private boolean genericEpisode;
    private String episodeNumber;
    private int seasonNumber;
    private int seriesSeqNo;
    private int seasonSeqNo;
    private int parts;
    private int partNum;
    private boolean seriesPremiere;
    private boolean seasonPremiere;
    private boolean seriesFinale;
    private boolean seasonFinale;
    private boolean repeat;
    private boolean NEW;
    private String rating;
    private boolean captioned;
    private boolean educational;
    private boolean blackWhite;
    private boolean subtitled;
    private boolean live;
    private boolean hd;
    private boolean descriptiveVideo;
    private boolean inProgress;
    private String showTypeID;
    private int breakoutLevel;
    private String showType;
    @Column(name="year1")
    private String year;
    private String guest;
    @Column(name="cast1")
    private String cast;
    private String director;
    private int starRating;
    private String description;
    private String league;
    private int team1ID;
    private int team2ID;
    private String team1;
    private String team2;
    private String event;
    private int eventID;
    private String location;
    private List<Integer> artwork=new ArrayList<Integer>();
    private String showHost;

}
