#!/bin/bash

if [[ $# -ne 2 ]] ; then
    echo 'Provide server Ip or localhost and port'
    exit 0
fi

cd ..
cd utils/src/main/resources

echo "IP= $1" > config.properties
echo "portNumber= $2" >> config.properties