package com.ing.be.tia.statemachine.data;

import com.ing.be.tia.statemachine.state.Listing;

import java.util.List;

public interface State {
    List<Listing> process(List<Listing> input);
    String getName();
}

