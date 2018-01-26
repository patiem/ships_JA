#!/bin/bash

language='English'

SC=`echo $PWD | grep scripts | wc -l`
if [ $SC -ne 1 ];
    then echo 'Please start script from scripts folder'
    exit 0
fi

if [ $# -eq 0 ] ; then
    echo 'Please provide the server Ip or localhost. Optionally you can provide the language (English or Polish)'
    exit 0
fi

if [ $# -eq 2 ] ; then
    language=$2
fi

cd ..
cd utils/src/main/resources

echo "IP= $1" > config.properties
echo "portNumber= 1234" >> config.properties
echo "languageVersion= ${language}" >> config.properties
echo "outputChannel= graphical" >> config.properties