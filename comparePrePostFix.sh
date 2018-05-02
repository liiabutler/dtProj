#!/bin/bash 

export DT_TOOLS=/home/user/dependent-tests-impact/experiments/impact-tools/*

export AM_SER_DIR=/home/user/LiiaStuff/ambari/ambari-server

export LIBS=$AM_SER_DIR/target/dependency/*

export TESTS=$AM_SER_DIR/target/test-classes

export CLASSES=$AM_SER_DIR/target/classes

export PRE_FIX=/home/user/LiiaStuff/beforeFixes-CR

export POST_FIX=/home/user/LiiaStuff/1stFix-CR


java ComparePrePostFix -prefix $PRE_FIX -postfix $POST_FIX > 1stFix-CRresults.txt

