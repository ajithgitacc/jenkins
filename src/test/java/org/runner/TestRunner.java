package org.runner;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.baseclass1.BaseClass;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.report.Reports;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "org.stepdefinition",features ="src\\test\\resources\\Features",plugin = {"pretty","json:target\\index.json"})
public class TestRunner extends BaseClass {
	@AfterClass
	public static void afterClass() throws FileNotFoundException, IOException {
		Reports.generateJVMReport(getProjectPath() +"\\target\\index.json");
	}
}
