package com.example.incident;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
//@RunWith(SpringRunner.class)
class IncidentEntityApplicationTests {

//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	protected MockMvc mvc;
//
//	@Before
//	public void setup() {
//		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}

	@Test
	void testIncidentCreation() throws Exception {
//		String json = "{\"name\": \"test name 1\", \"description\": \"test description 1\"}";
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
//						.post("/incidents")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(json))
//						.andExpect(MockMvcResultMatchers.status().isOk())
//						.andDo(MockMvcResultHandlers.print())
//						.andReturn();
//		int statusCode = mvcResult.getResponse().getStatus();
//		Assertions.assertEquals(200, statusCode);
	}

}
