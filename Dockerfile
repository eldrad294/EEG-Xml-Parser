FROM openjdk:11

ENV STATISTIC=accurate_fwd_zone_pass
ENV XML_PATH=1-F9-2192085691-srml-8-2017-f919230-matchresults-1.xml

WORKDIR /usr/local/runme
COPY target/xml-football-parser-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/1-F9-2192085691-srml-8-2017-f919230-matchresults-1.xml 1-F9-2192085691-srml-8-2017-f919230-matchresults-1.xml

ENTRYPOINT ["java","-jar","app.jar", "${STATISTIC}", "${XML_PATH}"]