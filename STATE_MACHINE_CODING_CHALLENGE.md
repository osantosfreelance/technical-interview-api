# Technical Interview: State Machine Framework

## Overview

Welcome to the ING Technical Interview coding challenge! In this exercise, you will work with a **State Machine Framework** designed to filter TV listings data based on configurable states.

Your task is to implement various filtering states that process listings data and return filtered results based on specific criteria.

## Project Structure

The state machine framework consists of the following components:

### Core Components

1. **Listing** (`com.ing.be.tia.statemachine.state.Listing`)
   - Represents a single TV listing with flexible field access
   - Provides methods to retrieve fields by key

2. **State Interface** (`com.ing.be.tia.statemachine.data.State`)
   ```java
   public interface State {
       List<Listing> process(List<Listing> input);
       String getName();
   }
   ```

3. **StateMachine** (`com.ing.be.tia.statemachine.StateMachine`)
   - Accepts a list of State objects
   - Processes listings by applying each state sequentially
   - Each state filters the output from the previous state

4. **ListingLoader** (`com.ing.be.tia.statemachine.service.ListingLoader`)
   - Utility to load listings from JSON files

## Sample Listing Data

Each listing contains the following fields (among others):

```json
{
    "number": "161",
    "channelNumber": 161,
    "stationID": 6837,
    "name": "BET Her",
    "callsign": "BETHer",
    "stationType": "Satellite",
    "listDateTime": "2024-04-16 02:30:00",
    "duration": 155,
    "showName": "Movie",
    "episodeTitle": "Under His Influence",
    "seasonNumber": 0,
    "rating": "PG-13",
    "captioned": true,
    "hd": false,
    "showType": "Movies, Thriller",
    "year": "2023",
    "cast": "Sydney Mitchell, Iman Shumpert, Michael Michele",
    "starRating": 4,
    "description": "A social media influencer falls in love..."
}
```

## How the State Machine Works

The state machine applies filters in sequence. For example:

```java
StateMachine stateMachine = new StateMachine(Arrays.asList(
    new GetMoviesState(),
    new GetViaSatelliteState()
));

List<Listing> filtered = stateMachine.process(listings);
```

This configuration will:
1. Filter all listings to get only movies (based on `showType` containing "movie")
2. Then filter those results to get only satellite broadcasts (based on `stationType` = "Satellite")

**Result**: All movie listings that are aired on satellite stations.

## Your Tasks

Implement the following State classes in the `com.ing.be.tia.statemachine.state` package. Each state should implement the `State` interface.

### Task 1: GetMoviesState
**File**: `GetMoviesState.java`

Filter listings where the `showType` field contains the word "movie" (case-insensitive).

**Example**:
- Input: 1000 listings
- Output: All listings where showType contains "Movies" or "Movie"

---

### Task 2: GetViaSatelliteState
**File**: `GetViaSatelliteState.java`

Filter listings where the `stationType` field equals "Satellite" (case-insensitive).

**Example**:
- Input: 500 listings
- Output: All listings broadcast via satellite

---

### Task 3: GetHDProgramsState
**File**: `GetHDProgramsState.java`

Filter listings where the `hd` field is `true`.

**Example**:
- Input: 500 listings
- Output: All high-definition programs

---

### Task 4: GetByYearRangeState
**File**: `GetByYearRangeState.java`

Filter listings where the `year` field falls within a specified range (inclusive).

**Constructor**: `GetByYearRangeState(int startYear, int endYear)`

**Example**:
```java
new GetByYearRangeState(2020, 2023)
```
- Input: 300 listings
- Output: All listings from years 2020-2023

---

### Task 5: GetByMinimumDurationState
**File**: `GetByMinimumDurationState.java`

Filter listings where the `duration` field (in minutes) is greater than or equal to a specified minimum.

**Constructor**: `GetByMinimumDurationState(int minDuration)`

**Example**:
```java
new GetByMinimumDurationState(120) // Movies 2+ hours
```
- Input: 200 listings
- Output: All listings with duration >= 120 minutes

---

## Advanced Tasks

### Task 6: Concurrency - ParallelStateMachine
**File**: `ParallelStateMachine.java`

Create an alternative implementation of StateMachine that processes large datasets using parallel streams for better performance.

**Requirements**:
- Similar to `StateMachine` but uses parallel processing
- Apply states sequentially but process listings within each state in parallel
- Ensure thread safety
- Compare performance with regular StateMachine

**Example**:
```java
ParallelStateMachine stateMachine = new ParallelStateMachine(Arrays.asList(
    new GetMoviesState(),
    new GetViaSatelliteState()
));

List<Listing> filtered = stateMachine.process(largeListingSet);
```

**Challenges to Consider**:
- Are the states thread-safe?
- What's the performance improvement with large datasets?
- When should you use parallel vs sequential processing?

---

### Task 7: Concurrency - ConcurrentMultiStateMachine
**File**: `ConcurrentMultiStateMachine.java`

Create a state machine that can apply multiple independent state chains concurrently and merge results.

**Requirements**:
- Accept multiple lists of states (different filtering pipelines)
- Execute each pipeline concurrently using `ExecutorService` or `CompletableFuture`
- Merge results from all pipelines (use union - combine all unique results)
- Handle exceptions from any pipeline gracefully

**Constructor**: `ConcurrentMultiStateMachine(List<List<State>> statePipelines)`

**Example**:
```java
// Pipeline 1: HD Movies
List<State> pipeline1 = Arrays.asList(new GetMoviesState(), new GetHDProgramsState());

// Pipeline 2: Satellite Shows with minimum duration
List<State> pipeline2 = Arrays.asList(
    new GetViaSatelliteState(), 
    new GetByMinimumDurationState(90)
);

ConcurrentMultiStateMachine multiMachine = new ConcurrentMultiStateMachine(
    Arrays.asList(pipeline1, pipeline2)
);

List<Listing> merged = multiMachine.process(listings);
```

**Challenges to Consider**:
- How do you merge results? (Use union - all unique listings from all pipelines)
- How do you handle if one pipeline fails?
- How do you ensure all pipelines complete before returning?
- Should you use thread pools? What size?
- How do you avoid duplicate listings in the merged result?

---

## Bonus Task: Error Handling

**Optional**: Enhance your State implementations and StateMachine classes with robust error handling:

**Suggestions**:
- Handle `null` values gracefully in listing fields
- Handle data type conversion errors (e.g., when `year` is not a valid integer)
- Add logging for debugging and monitoring (use SLF4J Logger)
- Implement a `SafeStateWrapper` that wraps any State and catches exceptions
- Add validation to ensure required fields exist before processing
- Implement retry logic for transient failures

---

## Testing Your Implementation

Refer to TechnicalnterviewApiApplication

**Example scenario to test**: Find HD movies from 2020-2023 on satellite channels

---

## Submission

Please provide:
1. All 5 implemented filtering State classes (Tasks 1-5)
2. Both advanced concurrency implementations (Tasks 6-7)
3. A test/demo class showing your states in action
4. Pull Request

## Questions?

If you have any questions about the requirements or need clarification on the data structure, please ask your interviewer.

Good luck! 🚀
