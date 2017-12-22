#!/bin/bash

set -e
echo so it begins....
DIR=$1
mkdir $DIR
echo Dir parameter
git clone https://github.com/patiem/ships_JA $DIR
echo repo cloned

cd $DIR
mvn clean install
echo 'tests and install'
mvn checkstyle:checkstyle

echo chestyle report generated
ls
# mvn site
mvn site site:stage
firefox $PWD/target/site/index.html


# show estimates + raporty
echo 'Number of tests:' 
grep -roh @Test . | wc -w

echo 'Commits on master:'
git log --pretty=format:'' | wc -l

echo 'Number of interfaces:'
grep -roh 'public interface' . | wc -l

echo 'Number of java code lines:'
( find ./ -name '*.java' -print0 | xargs -0 cat ) | wc -l

echo 'Number of public IPs:'
find ./ -name '*.java' -print0 | xargs --null grep "public " | wc -l

echo 'Number of packages:'
find -path '*/java/*' -type d | wc -l




