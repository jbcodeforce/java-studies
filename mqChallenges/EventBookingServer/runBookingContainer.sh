#!/bin/bash
docker run -e LICENSE=accept -e MQ_QMGR_NAME=QM1 -e LOG_FORMAT=json -p 1414:1414 -p 9443:9443 jbcodeforce/mqbooking