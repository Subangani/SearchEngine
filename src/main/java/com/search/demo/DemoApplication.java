package com.search.demo;

import com.search.demo.reader.SearchEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		SearchEngine searchEngine = new SearchEngine();
		searchEngine.search();
	}
}
