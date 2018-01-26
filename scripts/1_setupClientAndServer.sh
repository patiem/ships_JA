#!/bin/bash

language='English'
output='graphical'

SC=`echo $PWD | grep scripts | wc -l`
if [ $SC -ne 1 ];
    then echo 'Please start script from scripts folder'
    exit 0
fi

if [ $# -eq 0 ] ; then
    echo 'Please provide the language 'English' or 'Polish' as a first parameter and optionally as a second parameter output 'graphical'/'file''
    exit 0
fi

language=$1

if [ $# -eq 2 ] ; then
    output=$2
fi

cd ..
cd utils/src/main/resources

echo "IP= localhost" > config.properties
echo "portNumber= 1234" >> config.properties
echo "languageVersion= ${language}" >> config.properties
echo "outputChannel= ${output}" >> config.properties

set -e
cd ../../../..
mvn clean package -q

java -jar ./client/target/client*.jar &
java -jar ./server/target/server*.jar
