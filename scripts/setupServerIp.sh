#!/bin/bash

if [[ $# -ne 1 ]] ; then
    echo 'Provide server Ip or localhost'
    exit 0
fi

cd ..
cd utils/src/main/resources

echo "IP= $1" > config.properties
echo "portNumber= 1234" >> config.properties
echo "languageVersion=English" >> config.properties