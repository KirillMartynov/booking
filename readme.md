Booking service
=====================
- [Installation] (#Install)
- [Run] (#Run)
- [Urls] (#Urls)

# Install
First you need to install sbt and scala 2.12
After that clone repository

# Run
Go to the repository folder, open command promt and type:
sbt run

# Urls
- Register a movie
| POST http://localhost:8080/movies                                    |
| BODY {
"imdbId":"tt0111161",
"screenId":"screen3",
"availableSeats":10
} |

- Reserve a seat at the movie
| POST http://localhost:8080/movies/seat/reserve   |
| BODY {
"imdbId":"tt0111161",
"screenId":"screen3"} |

- Retrieve information about the movie
| POST http://localhost:8080/movies/information    |
| BODY {
"imdbId":"tt0111161",
"screenId":"screen3"} |
or
| GET http://localhost:8080/tt0111161/screen3 |