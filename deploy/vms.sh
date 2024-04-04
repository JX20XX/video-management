#!/bin/bash
APP_NAME=video-management.jar

usage() {
  echo "usage: sh vms.sh [start|stop|restart|status]"
}

is_exist(){
  pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
  if [ -z "${pid}" ]; then
   return 1
  else
   return 0
  fi
}

start(){
  is_exist
  if [ $? -eq "0" ]; then
   echo "${APP_NAME} is running! pid=${pid} ."
  else
   nohup java -server -Xms1024m -Xmx1024m -jar $APP_NAME > /dev/null 2>&1 &
   echo "${APP_NAME} start successfully! please check the log to ensure that it runs normally."
   status
  fi
}

stop(){
  is_exist
  if [ $? -eq "0" ]; then
   echo ">>> api PID = ${pid} begin kill ${pid} <<<"
   kill $pid
   sleep 2
   is_exist
   if [ $? -eq "0" ]; then
    echo ">>> api PID =${pid} begin kill -9 ${pid} <<<"
    kill -9 $pid
    sleep
   fi
   echo "${pid} process has been killed and the program stops running"
  else
   echo "${APP_NAME} Not running."
  fi
}

status(){
  is_exist
  if [ $? -eq "0" ]; then
   echo "${APP_NAME} is running. PID is ${pid}"
  else
   echo "${APP_NAME} is not running."
  fi
}

restart(){
  stop
  start
}

case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
   usage
   ;;
esac
