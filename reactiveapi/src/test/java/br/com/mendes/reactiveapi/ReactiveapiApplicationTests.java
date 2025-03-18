package br.com.mendes.reactiveapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReactiveapiApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void test() {
		User user = new User(null, "user", "123456", "user@email.com");

		webTestClient.post().uri("/users").bodyValue(user)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(User.class)
				.value(postResponse -> {
					assertNotNull(postResponse.getId());
					assertEquals(user.getUsername(), postResponse.getUsername());
					assertEquals(user.getEmail(), postResponse.getEmail());
					assertEquals(user.getPassword(), postResponse.getPassword());
				});

		webTestClient.get().uri("/users")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBodyList(User.class)
				.value(getResponses -> {
					User getResponse = getResponses.get(0);
					assertNotNull(getResponse.getId());
					assertEquals(user.getUsername(), getResponse.getUsername());
					assertEquals(user.getEmail(), getResponse.getEmail());
					assertEquals(user.getPassword(), getResponse.getPassword());
				});
	}
}
