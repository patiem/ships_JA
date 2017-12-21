#!/bin/bash

set -e
echo so it begins....
DIR=$1
echo Dir parameter
git clone https://github.com/patiem/ships_JA $DIR
echo repo cloned

cd $DIR
mvn clean install
echo tests and install
mvn checkstyle:checkstyle
echo chestyle report generated
ls
firefox $PWD/target/site/index.html
# mvn site

# show estimates + raporty
