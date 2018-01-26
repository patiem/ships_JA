# BattleShips Game

This is a battleships game that allows two players to play one game on a 10 x 10 board.

## Getting Started

In order to launch the game, please download the cloneRepo.sh script.
Launch this script providing as argument the folder name where you wish the project to be placed.
Once cloning is finished, navigate to the provided folder.
All the remaining scripts are supposed to be launched while in the '/scripts' folder of the cloned project.

### If you wish to start only the server:
1. Run script 0_launchServer.sh . 
2. If you want to have the transcript saved in a file, run the above script with the argument 'file'. 
For the graphical transcript option  the argument is 'graphical'.

### If you wish to only start the client:
1. Run script 0_launchClient.sh with the server IP address as the first argument (or 'localhost' for localhost). 
2. In order to change the language (default is English) provide 'Polish' as the second argument.

### If you want to start the server together with one of the clients:

1. Run 1_setupClientAndServer.sh script to launch the server and one client on the same machine.
If you wish to change the default arguments run the aforementioned script with the following arguments: 'file' or 'graphical' and 'Polish' or 'English'.
For example: *./1_setupClientAndServer.sh file English*

### If you want to start the server alongside both of the clients on the same machine:

1. Refer to *"If you want start server and one client on your computer"*
2. Refer go to *"If you want start only client on your computer"*


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

