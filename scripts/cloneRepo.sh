#!/bin/bash



set -e
echo so it begins....
DIR=$1
cd ~
mkdir $DIR
echo Dir parameter
git clone https://github.com/patiem/ships_JA $DIR
echo repo cloned

cd $DIR
mvn clean install
echo 'tests and install'
mvn checkstyle:checkstyle

echo checkstyle report generated
ls
# mvn site
mvn site site:stage
firefox $PWD/target/site/index.html


# show estimates + reports
scripts/reports.sh




