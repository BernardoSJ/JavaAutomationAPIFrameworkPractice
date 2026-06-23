package com.api.listeners;

import com.api.utility.ExtentReportsUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public void onStart(ITestContext context){
        logger.info("Test Suite Started!!!");
        ExtentReportsUtility.setupSparkReporter("report.html");
    }

    public void onTestStart(ITestResult result) {
        logger.info("Started!! " + result.getMethod().getMethodName());
        ExtentReportsUtility.createExtentTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("Passed!! " + result.getMethod().getMethodName());
        logger.info("Description!! " + result.getMethod().getDescription());
        ExtentReportsUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " PASSED");
    }

    public void onTestFailure(ITestResult result) {
        logger.info("Failed!! " + result.getMethod().getMethodName());
        ExtentReportsUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " FAILED");
        ExtentReportsUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
        ExtentReportsUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " SKIPPED");
    }

    public void onTestSkipped(ITestResult result) {
        logger.error("Skipped!! " + result.getMethod().getMethodName());
        ExtentReportsUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " SKIPPED");
    }

    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed!!!");
        ExtentReportsUtility.flushReport();
    }

}
