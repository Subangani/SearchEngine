package com.search.demo.util;

import com.search.demo.reader.SearchEngine;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CommonUtil {

	public static void printFileContent(String fileName) {
		ClassLoader classLoader = SearchEngine.class.getClassLoader();

		try (BufferedReader br = new BufferedReader(new FileReader(classLoader.getResource(fileName).getFile()))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	public static void printSearchResult(String key, String value) {
		System.out.printf("%-30.30s  %-30.30s%n", key, value);
	}
}
