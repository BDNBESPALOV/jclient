@echo off
echo Clear cache
if "%USERPROFILE%1" == "1" goto win95
goto winnt
:win95
deltree /y "%windir%\Application Data\Order\cache\*.*"
goto exit
:winnt
del "%USERPROFILE%\Application Data\Order\cache\*.*" /s /q
:exit
