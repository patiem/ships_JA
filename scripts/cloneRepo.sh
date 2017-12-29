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
echo 'Number of tests:' 
grep -roh @Test . | wc -w

echo 'Commits on master:'
git log --oneline | wc -l

echo 'Number of interfaces:'
grep -roh 'public interface' --include=\*.java | wc -l

echo 'Number of java code lines:'
( find ./ -name '*.java' -print0 | xargs -0 cat ) | wc -l

echo 'Number of public APIs:'
grep -r 'public \|default ' --include=\*.java | grep -v "class\|enum\|interface\|test\|new" | grep '(.*).*{$' | wc -l

echo 'Number of private APIs:'
grep -r 'private ' --include=\*.java | grep -v "class\|enum\|interface\|test\|new" | grep '(.*).*{$' | wc -l

echo 'Number of package private APIs:'
grep -r '(.*).*{$' --include=\*.java | grep -v "class\|enum\|interface\|test\|new\|public\|private\|if\|else\|for\|while\|catch\|default\|switch"

echo 'Number of packages:'
find -path '*/java/*' -type d | wc -l




