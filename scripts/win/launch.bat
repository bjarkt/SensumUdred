@echo off

pushd out\yee
java -classpath jfoenix-8.0.1.jar;postgresql-42.2.2.jar;aspectj/aspectjrt.jar; Main -verbose
popd