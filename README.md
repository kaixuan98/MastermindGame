# Mastermind
A console-based Mastermind game built with Java.

## Inspiration
Mastermind was one of my favourite code-breaking games growing up.
I enjoyed its simple setup and could keep me entertained for hours. I am building my own version is a fun way to revisit the game while practicing Java.

## About
Mastermind is a code-breaking game where the player tries to guess the 4-color secret code within a limited number of attempts.

Each guess would have a feedback:
- Black = correct colour and correct position
- White = correct colour and wrong position

## Features 
- Normal and Hard mode
- Randomly Generated secret codes
- Feedback for each guess
- Limited number of attempts
- Input validation

## How to run
### Prereq
- Jave 17 or later
- Git
### Run the game 
1. Clone the repo
    ```bash
    git clone https://github.com/kaixuan98/MastermindGame
    cd MastermindGame
    ```
    
2. Compile the project.   
    ```bash
    javac -d out src/**/*.java
    ```
    
3. Run the game.   
    ```bash
    java -cp out Main
    ```
If you are using any IDE like Eclipse or Intellij, you can just open the project in your IDE and run it.

## How to play (make sure you run the program)
1. Enter your name
2. Select a game mode
3. Enter a 4-colour guess using comma-separated colors.
