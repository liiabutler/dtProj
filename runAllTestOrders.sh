#!/bin/bash 

export DT_TOOLS=/home/user/dependent-tests-impact/experiments/impact-tools/*

export AM_SER_DIR=/home/user/LiiaStuff/ambari/ambari-server

export LIBS=$AM_SER_DIR/target/dependency/*

export TESTS=$AM_SER_DIR/target/test-classes

export CLASSES=$AM_SER_DIR/target/classes

export ORDER_FILES=/home/user/LiiaStuff/ambari-fixing/orders/*

for ORDER_NAME in $ORDER_FILES
do
	java -cp $DT_TOOLS:$LIBS:$TESTS:$CLASSES: edu.washington.cs.dt.main.ImpactMain -skipMissingTests -inputTests "$ORDER_NAME" > 1stFix/$(basename $ORDER_NAME)-results.txt

done
