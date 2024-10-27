package com.example.incident;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class IncidentEntityApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void testIncidents() throws Exception {
		// no records at beginning
		mvc.perform(MockMvcRequestBuilders.get("/incidents/1"))
				.andExpect(MockMvcResultMatchers.status().is(404));

		// create a record
		String requestJson = "{\"name\":\"test name 1\",\"description\":\"test description 1\",\"status\":\"CREATED\"}";
		String expectedJson = "{\"id\":1,\"name\":\"test name 1\",\"description\":\"test description 1\",\"status\":\"CREATED\"}";
		mvc.perform(MockMvcRequestBuilders
						.post("/incidents")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().json(expectedJson));

		// search again after creation and the created record is expected to return.
		mvc.perform(MockMvcRequestBuilders.get("/incidents/1"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(content().json(expectedJson));

		// update the record and updated record is expected to return.
		requestJson = "{\"name\":\"test name 2\",\"description\":\"test description 2\",\"status\":\"COMPLETED\"}";
		expectedJson = "{\"id\":1,\"name\":\"test name 2\",\"description\":\"test description 2\",\"status\":\"COMPLETED\"}";
		mvc.perform(MockMvcRequestBuilders
				.put("/incidents/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().json(expectedJson));

		// search again after update, and the updated record is expected to return
		mvc.perform(MockMvcRequestBuilders.get("/incidents/1"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(content().json(expectedJson));

		// remove the record
		mvc.perform(MockMvcRequestBuilders.delete("/incidents/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// search again after deletion, and 404 status is expected.
		mvc.perform(MockMvcRequestBuilders.get("/incidents/1"))
				.andExpect(MockMvcResultMatchers.status().is(404));

	}




}
