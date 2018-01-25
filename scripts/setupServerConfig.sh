#!/bin/bash

command1='English'
command2='graphical'

if [ $# -eq 1 ] ; then
    command2=$1
fi

cd ..
cd utils/src/main/resources

echo "IP= localhost" > config.properties
echo "portNumber= 1234" >> config.properties
echo "languageVersion= ${command1}" >> config.properties
echo "outputChannel= ${command2}" >> config.properties