package org.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reports {
	public static void generateJVMReport(String jsonFile) {
		//report location
		File reportdirectory= new File(System.getProperty("user.dir") +"\\target");
		//configuration
		Configuration configuration= new Configuration(reportdirectory, "OMRBranchwebAutomation");
		//set classifications
		configuration.addClassifications("platformName", "windows");
		configuration.addClassifications("platformVersion", "11");
		configuration.addClassifications("author", "ajith");
		configuration.addClassifications("environment", "QA");
		configuration.addClassifications("sprintNumber", "34");
		//create object for reportbuilder
		List<String> jsonFiles= new ArrayList<>();
		jsonFiles.add(jsonFile);
		ReportBuilder builder= new ReportBuilder(jsonFiles, configuration);
		builder.generateReports();
		

	}

}
