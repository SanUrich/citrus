package com.citrus.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.apache.http.entity.ContentType;

//import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;

public class MyCheckXML extends TestNGCitrusTestRunner {
	
	@Autowired
	HttpClient connHTTP;
	
	@Test
	@CitrusTest(name = "validateMessageXML")
	public void testGetXml() {
		load("file:c:\\Users\\VTB\\eclipse-workspace\\CitrusWS\\FirstCitrusProject\\load.properties");
		
		http(httpActionBuilder -> httpActionBuilder
			.client(connHTTP)
			.send()
			//.get()
            .post("services/rest/simulator/goodbye")
            .contentType(ContentType.APPLICATION_XML.getMimeType())
		   //.header("Operation", "sayHello")
            .payload("<GoodBye xmlns=\"http://citrusframework.org/schemas/hello\">" +
                    "Say GoodBye!" +
                    "</GoodBye>")
            );
		
		http(httpActionBuilder -> httpActionBuilder
			    .client(connHTTP)
			    .receive()
			    .response(HttpStatus.OK)
			    .messageType(MessageType.XML)
			    .namespace("vtb", "http://www.vtb24.ru/ApplicationObjectLibrary/TWOSH/Providers/BankCardTWOSHProvA/V1")
			    .validate("//vtb:NewPINResponse/vtb:Messageid", "12345025")
			    .extractFromPayload("//vtb:NewPINResponse/vtb:KeyId", "keyid")
			    .payload(new ClassPathResource("templates/httpXMLResponse.xml"))
			    
				);
		echo ("${keyid}");
	}
}
