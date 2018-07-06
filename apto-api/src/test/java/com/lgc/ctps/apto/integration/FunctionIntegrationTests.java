package com.lgc.ctps.apto.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgc.ctps.apto.domain.Function;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@OverrideAutoConfiguration(enabled = true)
public class FunctionIntegrationTests {
	private static final Long ID = 1L;
	private static final boolean PETROBRAS_POST = false;
	private static final String NAME = "Função Teste";
	private static final Function FUNCTION_REQUEST = Function.builder().name(NAME).petrobrasPost(PETROBRAS_POST).build();
	private static final Function FUNCTION_RESPONSE = Function.builder().id(ID).name(NAME).petrobrasPost(PETROBRAS_POST).build();

    @Autowired
    private TestRestTemplate restTemplate;

	@Before
	public void setUp() {
		ObjectMapper objectMappper = new ObjectMapper();
		JacksonTester.initFields(this, objectMappper);
	}

	@Test
	public void shouldCreateAFunction() throws Exception {
        ResponseEntity<Function> responseEntity =
            restTemplate.postForEntity("/functions", FUNCTION_REQUEST, Function.class);
        Function function = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(NAME, function.getName());
	}
}
