#!/usr/bin/env bash

# This script launches the server

SC=`echo $PWD | grep scripts | wc -l`
if [[ $SC -ne 1 ]];
    then echo 'Please start script from scripts folder'
    exit 0
fi

./setupServerConfig.sh $1

set -e
cd ..
mvn clean install -q
echo "Server has been installed, server launch in progress"
java -jar ./server/target/server*.jar