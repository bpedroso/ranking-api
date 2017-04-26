# Ranking API

Service that keep ranking in-memory

## Development environment
  - OS: Fedora 25
  - Java IDE: Eclipse Neon (4.6.0)
  - java version "1.8.0_102"
  - Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
  - Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)
  - JAX-RS (JSR 311 & JSR 339)

## Test Description

### Real Time Ranking of Users playing a specific game
##### Requirements:
Clients submit user scores when they achieve important milestones in the game
Clients can submit absolute scores or relative scores, for example: {user: 123, score:250} or {user: 123, score: "+10"} or {user: 123, score: "-20"}
Any client can request the ranking at anytime, using one of the following requests:
  - Absolute ranking: Top100, Top200, Top500
  - Relative ranking: At100/3, meaning 3 users around position 100th of the ranking that is positions 97th, 98th, 99th, 100th, 101th, 102th, 103th

-------------------

# Solution

### Service description
Load a jetty listening on port 8080.
I have two approaches, one flow with the update rules and other one with list rules.

Endpoints Sample - Update:
- http:/localhost:8080/ranking/update/{userId}?score=500
- http:/localhost:8080/ranking/update/{userId}?score=+10
- http:/localhost:8080/ranking/update/{userId}?score=-5

Endpoints Sample - List:
- http:/localhost:8080/ranking/list/absolute/Top100
- http:/localhost:8080/ranking/list/relative/{position}/{around}
- http:/localhost:8080/ranking/list/relative/At100/3

