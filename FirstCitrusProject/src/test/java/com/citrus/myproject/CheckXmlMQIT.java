package com.citrus.myproject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.model.testcase.core.VariablesModel.Variable;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;


@Test
public class CheckXmlMQIT extends AbstractTestNGCitrusTest {
	
	/* @CitrusXmlTest( name = "checkMQ" ) public void checkMQ() {} */
	
	@CitrusXmlTest( name = "PurgeQueue" )
	@BeforeTest 
	public void PurgeQueue() { log.info("Purged message"); }
	  
	@CitrusXmlTest( name = "${service}" )
	public void NewPIN() {
		} 
	  
		 @CitrusXmlTest( name = "ChangePIN" ) public void ChangePIN() {} 
	  
		 @CitrusXmlTest( name = "CreatePINBlock" ) public void CreatePINBlock() {} 
	 
	 

}