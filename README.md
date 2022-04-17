# XML-FOOTBALL-PARSER

A Java (Spring-Boot) xml parsing utility

## Building the application

* mvn package

## Running the application

* java -jar .\target\xml-football-parser-0.0.1-SNAPSHOT.jar --statistic=accurate_fwd_zone_pass --xml-path=C:/Projects/xml-football-parser/src/main/resources/1-F9-2192085691-srml-8-2017-f919230-matchresults-1.xml app.jar

## Developer's Comments

### Framework: 
Springboot

### Libraries Used:
* Lombok (For Java boilerplate code reduction)
* Java DOM API (For XML parsing)

### Project Structure:
* __src/main__
  * __enums__ - Any project enums / constants which are based off the provided XML file.
  * __exception__ - Any custom written exception for this project.
  * __models__ - All Java POJOs used for this project.
    * __reporting__ - These POJOs are specifically used for reporting purposes only, and only serve to enrich the base java model classes.
  * __utils__ - Main logic, which contains both the XML parsing module, and the statistics reporting logic.
* __src/tests__
  * __XmlParserTests__ - Contains integration tests for the parsing module.
  * __StatististicsTests__ - Contains integration tests for the file statistics, comparing the aggregator logic of the project with those provided on a team level within the XML file.

### Docker Support:
Run the following commands:
* docker build -t xml-football-parser .
* docker run -e STATISTIC=accurate_fwd_zone_pasas -e XML_PATH=1-F9-2192085691-srml-8-2017-f919230-matchresults-1.xml -it xml-football-parser