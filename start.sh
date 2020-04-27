#!/bin/sh

java -jar jclinet.jar <&- 2>./daemon_app_error.log & echo $! > jClient.pid
echo 'client started !!!'
