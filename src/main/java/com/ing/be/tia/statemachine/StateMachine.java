package com.ing.be.tia.statemachine;

import com.ing.be.tia.statemachine.data.State;
import com.ing.be.tia.statemachine.state.Listing;

import java.util.List;

public class StateMachine {
    private final List<State> states;

    public StateMachine(List<State> states) {
        this.states = states;
    }

    public List<Listing> process(List<Listing> listings) {
        List<Listing> result = listings;
        for (State state : states) {
            result = state.process(result);
        }
        return result;
    }
}

