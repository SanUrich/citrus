package com.citrus.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.apache.http.entity.ContentType;

//import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;

public class MyHttpRest extends TestNGCitrusTestRunner {
	
	@Autowired
	HttpClient connRest;
	
	@Test
	@CitrusTest(name = "checkSizeMessageJSON")
	public void testGetJsonHTTPSize() {
		
    	http(httpActionBuilder -> httpActionBuilder
    		.client(connRest)
			.send()
	//		.post()
	//		.payload(new ClassPathResource("templates/httpRequest.json")));
			.get()
			.accept(ContentType.APPLICATION_JSON.getMimeType()));

		http(httpActionBuilder -> httpActionBuilder
		    .client(connRest)
		    .receive()
		    .response(HttpStatus.OK)
		    .messageType(MessageType.JSON)
		    .validate("$.result", hasSize(2))
		    );
		    
	}
	
	@Test
	@CitrusTest(name = "validateMessageJSON")
	public void testGetJsonHTTPScheme() {
		load("file:c:\\Users\\VTB\\eclipse-workspace\\CitrusWS\\FirstCitrusProject\\load.properties");
		
		http(httpActionBuilder -> httpActionBuilder
			.client(connRest)
			.send()
			.get()
			.accept(ContentType.APPLICATION_JSON.getMimeType()));
		
		http(httpActionBuilder -> httpActionBuilder
			    .client(connRest)
			    .receive()
			    .response(HttpStatus.OK)
			    .messageType(MessageType.JSON)
			    //.validate("$.result[?(@.id == '${MesId1}')].status", "OK")
			    .jsonPath("$.result[?(@.id == '${MesId1}')].status", "OK")
			    .payload(new ClassPathResource("templates/httpResponse.json"))
			    .header("X-Content-Type-Options", "nosniff")
			    
				);
	}
}
