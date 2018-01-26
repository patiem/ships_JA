# BattleShips Game

This is a battleships game that allows two players to play one game on a 10 x 10 board.

## Getting Started

In order to launch the game, please download the cloneRepo.sh script.
Launch this script with folder name in which you want to have project.
When cloning is finished navigate yourself to ~/<yourFolderName>.

###If you want start only server on your computer:
1.Run script 0_launchServer.sh . 
2.If you want to change default graphical transcript output to file run script with argument 'file'. For graphical transcript is 'graphical'.

###If you want start only client on your computer:
1.Run script 0_launchClient.sh with server ip parameter (or 'localhost' for localhost). 
2.For change default language from English provide second parameter 'Polish'. Back to english provide "English" parameter after ip.


###If you want start server and one client on your computer:

1.If you want to change default parameters: server transcript - graphical and language - English, 
 run 1_setupClientAndServer.sh with two parameters: first output:'file'/'graphical, second language 'Polish'/'English'.

###If you want start server and two clients on your computer:

1.Go to "If you want start server and one client on your computer"
2.Then go to "If you want start only client on your computer"


## Running the tests

The tests will be run upon launching the cloneRepo.sh script.

## License

This project is licensed under the MIT License.

## How to play

For detailed instructions on how to play please see the Wiki section.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Travis](https://travis-ci.org/) - Continuous Integration

## Authors

* **Patrycja Mikulska - GUI** 
* **Emilia Ciastek - Backend** 
* **Bartosz Pieczara - Backend** 

