# Quizmaker

Project for Grundlagen der Programmierung from:
* Arzberger Raphael
* Bordei Sergiu
* Davidowicz Laura Nurit
* Fischer Carmen
* Gallmetzer Nina

## Aim of the project
This project should be a program that lets the user play a game inspired by "Who want's to be a millionaire". 
It was made for the subject Grundlagen der Programmierung at FH Campus Wien.

## Technologies
Language: JAVA
Questions can be added to the "Questionnaire.json" in the following format:
{
    "question": "Question",
    "answer0": "answer0",
    "answer1": "answer1",
    "answer2": "answer2",
    "answer3": "answer3",
    "difficulty": //integer,
    "answer": //integer,
    "hint": "A Hint"
  }

## How to play
1. Execute the App via Gradle (Tasks > application > run).
2. Start the Game by pressing the play game button.
3. Enter your name, choose how many questions you want to play and press Start. 
5. Use jokers if you need them - three are available (delete two wrong answers, skip a question, show a hint). Each can be used once.
6. You get points for correct answers, but you also lose points for wrong answers!
7. Your final score will be written to the high score list (if you make it to top 20).