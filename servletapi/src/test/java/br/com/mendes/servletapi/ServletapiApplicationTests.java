package br.com.mendes.servletapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServletapiApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void test() {
		User user = new User(null, "user", "123456", "user@email.com");

		User postResponse = testRestTemplate.postForObject("/users", user, User.class);
		assertNotNull(postResponse.getId());
		assertEquals(user.getUsername(), postResponse.getUsername());
		assertEquals(user.getEmail(), postResponse.getEmail());
		assertEquals(user.getPassword(), postResponse.getPassword());

		User[] getResponses = testRestTemplate.getForObject("/users", User[].class);
		User getResponse = getResponses[0];
		assertNotNull(getResponse.getId());
		assertEquals(user.getUsername(), getResponse.getUsername());
		assertEquals(user.getEmail(), getResponse.getEmail());
		assertEquals(user.getPassword(), getResponse.getPassword());
	}
}
