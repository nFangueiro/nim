# Nim Game!

An HTTP Server running the NIM game with a single heap.

There are 3 simple endpoints.

- /start-game \n
Here you can create a game with different input parameters such as "number of matches" and "maximum take".

- /play-turn
Here you play your turn removing x number of matches from the pile. The computer then processes your play and if it can, it will play its turn returning the end result.

- /all-games
Just a simple select * from the database to check its state.

The computer is set to use a winning strategy, so beware!!


- Technicalities

We use Kotlin with SpringBoot to support this API.

We use Gradle(Kotlin) as a builder. You can use ".\gradlew build" and ".\gradlew bootRun" to respectively build and run the application.

We use a simple H2 database to store the plays in memory.

Have fun!!!
