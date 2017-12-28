#!/bin/bash

# This script launches the server and both of the clients
# The script automatically adjusts to the downloaded jar file version
set -e
mvn clean install -q
echo "Server has been intalled, server launch in progress"
java -jar ./server/target/server*.jar &
echo "Server started, launching the 1st client"
java -jar ./client/target/client*.jar &
echo "Launching the 2nd client"
java -jar ./client/target/client*.jar &