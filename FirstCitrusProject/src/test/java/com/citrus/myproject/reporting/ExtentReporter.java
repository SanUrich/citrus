package com.citrus.myproject.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.consol.citrus.TestCase;
import com.consol.citrus.TestCaseMetaInfo;
import com.consol.citrus.report.AbstractTestReporter;
import com.consol.citrus.report.TestListener;
import com.consol.citrus.report.TestReporter;
import com.consol.citrus.report.TestResults;
import org.springframework.beans.factory.InitializingBean;

public class ExtentReporter extends AbstractTestReporter implements TestReporter, TestListener, InitializingBean {

    private ExtentReports extentReports;

    @Override
    public void onTestSuccess(final TestCase test) {
        final ExtentTest extentTest = extentReports.createTest(test.getName());
        extentTest.pass(getTestDetails(test.getMetaInfo()));
    }

    @Override
    public void onTestSkipped(final TestCase test) {
        final ExtentTest extentTest = extentReports.createTest(test.getName());
        extentTest.skip(getTestDetails(test.getMetaInfo()));
    }

    @Override
    public void onTestFailure(final TestCase test, final Throwable cause) {
        final ExtentTest extentTest = extentReports.createTest(test.getName());
        extentTest.fail(cause);
    }

    @Override
    public void onTestStart(TestCase test) {
        // do nothing
    }

    @Override
    public void onTestFinish(TestCase test) {
        // do nothing
    }

    public void generate(TestResults results) {
        extentReports.flush();
    }

    @Override
    public void afterPropertiesSet() {
        initializeExtentReports();
    }

    /**
     * Initialize reports.
     */
    private void initializeExtentReports() {
        final ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter("target/citrus-reports/extent-reports.html");
        extentHtmlReporter.config().setDocumentTitle("ExtentReports - Created by Citrus TestListener");
        extentHtmlReporter.config().setReportName("ExtentReports - Created by Citrus TestListener");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setReportUsesManualConfiguration(true);
    }

    /**
     * Get test details from meta info.
     * @param metaInfo
     * @return
     */
    private String getTestDetails(final TestCaseMetaInfo metaInfo) {
        return String.format("details: author:%s, creationDate:%s, status:%s", metaInfo.getAuthor(), metaInfo.getCreationDate(), metaInfo.getStatus());
    }

	@Override
	public void generateTestResults() {
		extentReports.flush();
		
	}


}
