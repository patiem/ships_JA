#!/usr/bin/env bash


# This script launches the client

SC=`echo $PWD | grep scripts | wc -l`
if [[ $SC -ne 1 ]];
    then echo 'Please start script from scripts folder'
    exit 0
fi

if [[ $# -ne 1 ]] ; then
    echo 'Provide server Ip or localhost'
    exit 0
fi

./setupServerIp.sh $1

set -e
cd ..
mvn clean install -q
echo "Launching the client"
java -jar ./client/target/client*.jar