#!/bin/bash
docker run -e LICENSE=accept -e MQ_QMGR_NAME=QM1 --name mqebs -ti -e LOG_FORMAT=json -e MQ_APP_PASSWORD=passw0rd -p 1414:1414 -p 9443:9443 jbcodeforce/mqticketservice