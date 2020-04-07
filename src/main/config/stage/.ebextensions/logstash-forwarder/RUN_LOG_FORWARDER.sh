#!/bin/bash
echo "Starting logstash forwarder"
nohup /sbin/service logstash-forwarder start > /dev/null 2>&1 &
echo "forwarder service started"
echo 0
