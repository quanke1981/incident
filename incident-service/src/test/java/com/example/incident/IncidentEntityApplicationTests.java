package com.example.incident;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class IncidentEntityApplicationTests {

	private final static int NUM_THREADS = 20;
	private final static int NUM_OBJECT_SIZE_PER_REQUEST = 500;
	private final static String INCIDENT_REQEUST_ITEM_JSON
			= "{\"title\":\"%1$s\",\"description\":\"%2$s\",\"status\":\"%3$s\"}";

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Order(1)
	void testIncidents() throws Exception {
		// no records at beginning
		mvc.perform(MockMvcRequestBuilders.get("/incidents/1"))
				.andExpect(MockMvcResultMatchers.status().is(404));

		// create a record
		String requestJson = "[" + String.format(INCIDENT_REQEUST_ITEM_JSON, "test title 1", "test description 1", "Open") + "]";
		String expectedJson = "{\"id\":1,\"title\":\"test title 1\",\"description\":\"test description 1\",\"status\":\"Open\"}";
		 mvc.perform(MockMvcRequestBuilders
						.post("/incidents")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().json("[" + expectedJson + "]"));

		// search again after creation and the created record is expected to return.
		mvc.perform(MockMvcRequestBuilders.get("/incidents/1"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(content().json(expectedJson));

		// update the record and updated record is expected to return.
		requestJson = String.format(INCIDENT_REQEUST_ITEM_JSON, "test title 2","test description 2","Closed");
		expectedJson = "{\"id\":1,\"title\":\"test title 2\",\"description\":\"test description 2\",\"status\":\"Closed\"}";
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

	@Test
	@Order(2)
	public void performanceTest() throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
		List<Future<Integer>> futures = new ArrayList<>();
		for (int i = 0; i < NUM_THREADS; i++) {
			futures.add(executorService.submit(() -> {
                try {
                    return mvc.perform(MockMvcRequestBuilders
                                    .post("/incidents")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonArrayForIncidents(NUM_OBJECT_SIZE_PER_REQUEST)))
							.andReturn()
							.getResponse()
							.getStatus();
                } catch (Exception e) {
                    return 500;
                }
            }));
		}

		for (Future<Integer> future : futures) {
			Assertions.assertEquals(200, future.get(), "Expected 200 status code from API response");
		}

		executorService.shutdown();
	}

	private String jsonForIncident(Random random) {
		return String.format(INCIDENT_REQEUST_ITEM_JSON, "test title " + random.nextInt(), "test description " + random.nextInt(), "Open");
	}

	private String jsonArrayForIncidents(int size) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(jsonForIncident(random));
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}






}
