package com.eeg.xmlfootballparser;
import com.eeg.xmlfootballparser.utils.StatisticsService;
import com.eeg.xmlfootballparser.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class XmlFootballParserApplication{

	@Autowired
	private XmlParser xmlParser;

	public static void main(String[] args) {
		SpringApplication.run(XmlFootballParserApplication.class, args);
	}

	@Bean(initMethod="statisticReporter")
	StatisticsService getStatisticsReported() {
		return new StatisticsService(xmlParser);
	}
}
