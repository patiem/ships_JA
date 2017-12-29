#!/bin/bash

# This script launches the server and both of the clients
# The script automatically adjusts to the downloaded jar file version

set -e
cd ..
mvn clean install -q
echo "Server has been intalled, server launch in progress"
cd scripts
sh launchServer.sh
echo "Server started, launching the 1st client"
sh launchClient.sh
echo "Launching the 2nd client"
sh launchClient.sh
echo "Second client launched"