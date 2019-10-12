FROM ibmcom/mq:latest
COPY GenerateEvents.jar /data/GenerateEvents.jar
COPY startApp.sh /data/startApp.sh
COPY 20-config.mqsc /etc/mqm/
RUN echo app:passw0rd | chpasswd
RUN chmod a+x /data/startApp.sh
ENTRYPOINT ["/data/startApp.sh"]