package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SpringMath.class)
public class SpringMathTests {
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void testingMathEndpoint() throws Exception{
		this.mvc.perform(get("/math/pi").accept(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andExpect(content().string("3.141592653589793"));
	}

	@Test
	public void testingMathCalculateEndpoint() throws Exception {
		this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
				.andExpect(status().isOk());

	}
	@Test
	public void testingMathSumEndpoint() throws Exception {
		this.mvc.perform(post("/math/sum?n=7&n=9"))
				.andExpect(status().isOk());
	}
	@Test
	public void testingMathVolumeEndpointGet() throws Exception {
		this.mvc.perform(get("/math/volume/3/4/5"))
				.andExpect(status().isOk());
	}

	@Test
	public void testingMathAreaEndpoint() throws Exception {
		String area = String.valueOf(new Random().nextInt());

		MockHttpServletRequestBuilder request1 = post("/math/area")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("width", area)
				.param("type", area)
				.param("radius", area)
				.param("height", area);

		this.mvc.perform(request1).andExpect(status().isOk());
	}

}
