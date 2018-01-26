#!/usr/bin/env bash

# This script launches the server

SC=`echo $PWD | grep scripts | wc -l`
if [[ $SC -ne 1 ]];
    then echo 'Please start script from scripts folder'
    exit 0
fi

output='graphical'

if [ $# -eq 1 ] ; then
    output=$1
fi

cd ..
cd utils/src/main/resources

echo "IP= localhost" > config.properties
echo "portNumber= 1234" >> config.properties
echo "languageVersion= English" >> config.properties
echo "outputChannel= ${output}" >> config.properties


set -e
cd ../../../..
mvn clean install -q
echo "Server has been installed, server launch in progress"
java -jar ./server/target/server*.jar