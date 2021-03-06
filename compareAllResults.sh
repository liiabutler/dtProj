#!/bin/bash 

export DT_TOOLS=/home/user/dependent-tests-impact/experiments/impact-tools/*

export AM_SER_DIR=/home/user/LiiaStuff/ambari/ambari-server

export LIBS=$AM_SER_DIR/target/dependency/*

export TESTS=$AM_SER_DIR/target/test-classes

export CLASSES=$AM_SER_DIR/target/classes

export ORDER_FILES=/home/user/LiiaStuff/$1/*

export ORIGINAL_ORDER=/home/user/LiiaStuff/$1/ambari-server-orig-order-results.txt 

#mkdir $1-CR
export CR_DR=$1-CR 


for ORDER_NAME in $ORDER_FILES
do
	java -cp $DT_TOOLS:$LIBS:$TESTS:$CLASSES: edu.washington.cs.dt.impact.tools.CrossReferencer -origOrder $ORIGINAL_ORDER -testOrder "$ORDER_NAME" > $CR_DR/$(basename $ORDER_NAME)-CR.txt
#	echo $ORDER_NAME

done
