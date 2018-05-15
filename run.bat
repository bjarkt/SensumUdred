@echo off

for %%a in (.) do set currentfolder=%%~na

if NOT "%currentfolder%" == "SensumUdred" (
    echo ERROR: Must be in root of project, SensumUdred directory
    GOTO:EOF
)

echo Compiler...
call scripts/win/compile.bat %*

echo Starter program...
call scripts/win/launch.bat