#!/bin/bash
# Created by Pati Mikulska
# Shows some numbers for Tomek

echo "Numbers for Tomek \n"

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