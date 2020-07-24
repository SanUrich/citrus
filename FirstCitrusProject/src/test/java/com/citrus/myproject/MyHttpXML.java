package com.citrus.myproject;

import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * This is a sample Citrus integration test for loading XML syntax test case.
 *
 * @author Citrus
 */
@Test
public class MyHttpXML extends AbstractTestNGCitrusTest {

	/*
	 * @CitrusXmlTest(name = "checkXML") public void checkXML() {}
	 */
	
	@CitrusXmlTest( name = "checkMQ" ) public void checkMQ() {}

}