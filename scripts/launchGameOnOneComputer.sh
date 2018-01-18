#!/bin/bash

# This script launches the server and both of the clients
# The script automatically adjusts to the downloaded jar file version

SC=`echo $PWD | grep scripts | wc -l`
if [[ $SC -ne 1 ]];
    then echo 'Please start script from scripts folder'
    exit 0
fi
set -e
cd ..
mvn clean install -q

echo "Server started, launching the 1st client"
java -jar ./client/target/client*.jar localhost &
echo "Launching the 2nd client"
java -jar ./client/target/client*.jar localhost &
echo "Second client launched"

echo "Server has been installed, server launch in progress"
java -jar ./server/target/server*.jar