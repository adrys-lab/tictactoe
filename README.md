

Dependencies:
-----------------------
- Maven 3
- Java 8


Description:
-----------------------

- Maven project.
- Tic tac toe game to VS console.
- Outputs&Inputs by UI Java Swing.
- Impossible win the console with current implementation, max result could be DRAW !


How to package & run:
-----------------------

``mvn package``
- java -jar <home-folder>/target/tictactoe-1.0.0-SNAPSHOT.jar


How to test:
-----------------------

- ``mvn test``
- with IDE


Explanation of solution:
-----------------------

- with more time i would like to fully test.
- with more time, i would add Two more screens:

      1- For allow play Human VS Human
      
      2- Fro Human VS Console, allow choose difficulty level, which would end-up into:
      
            2.1 - Easy --> a Random algorithm to decide Console position
            
            2.2 - Medium --> a MaxiMin algorithm.
            
            2.3 - Hard --> Current implementation with Alpha Beta Prunning
