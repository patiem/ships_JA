#!/bin/bash
# Created by Pati Mikulska
# Shows some numbers for Tomek

echo "Numbers for Boss"

echo 'Number of tests:'
mvn test | grep elapsed | grep -oP 'run: \K[0-9]*' | paste -s -d+ - | bc

echo 'Commits on master:'
git rev-list HEAD --count

echo 'Number of interfaces:'
grep -roh 'public interface' --include=\*.java | wc -l

echo 'Number of java code lines:'
( find ./ -name '*.java' -print0 | xargs -0 cat ) | wc -l

echo 'Number of public APIs:'
grep -r 'public \|default ' --include=\*.java | grep -v "class\|enum\|interface\|test\|new" | grep '(.*).*{$' | wc -l

echo 'Number of private APIs:'
grep -r 'private ' --include=\*.java | grep -v "class\|enum\|interface\|test\|new" | grep '(.*).*{$' | wc -l

echo 'Number of package private APIs:'
grep -r '(.*).*{$' --include=\*.java | grep -v "class\|enum\|interface\|test\|new\|public\|private\|if\|else\|for\|while\|catch\|default\|switch" | wc -l

echo 'Number of packages:'
find -path '*/java/*' -type d | wc -l