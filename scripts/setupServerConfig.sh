#!/bin/bash

command1='English'
command2='out'

if [ $# -eq 0 ] ; then
    echo 'Please provide the server Ip or localhost. Optionally you can provide the language (English or Polish) as well as the output channel(err or out)'
    exit 0
fi

if [ $# -eq 2 ] ; then
    command1=$2
fi

if [ $# -eq 3 ] ; then
    command1=$2
    command2=$3
fi

cd ..
cd utils/src/main/resources

echo "IP= $1" > config.properties
echo "portNumber= 1234" >> config.properties
echo "languageVersion= ${command1}" >> config.properties
echo "outputChannel= ${command2}" >> config.properties