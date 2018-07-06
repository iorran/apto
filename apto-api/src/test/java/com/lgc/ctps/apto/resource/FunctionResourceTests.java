package com.lgc.ctps.apto.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgc.ctps.apto.domain.Function;
import com.lgc.ctps.apto.service.FunctionService;

@RunWith(SpringRunner.class)
@WebMvcTest(FunctionResource.class)
@AutoConfigureMockMvc
@OverrideAutoConfiguration(enabled = true)
public class FunctionResourceTests {
	private static final Long ID = 1L;
	private static final boolean PETROBRAS_POST = false;
	private static final String NAME = "Função Teste";
	private static final Function FUNCTION_REQUEST = Function.builder().name(NAME).petrobrasPost(PETROBRAS_POST).build();
	private static final Function FUNCTION_RESPONSE = Function.builder().id(ID).name(NAME).petrobrasPost(PETROBRAS_POST).build();

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FunctionService functionService;

	private JacksonTester<Function> jacksonTester;

	@Before
	public void setUp() {
		ObjectMapper objectMappper = new ObjectMapper();
		JacksonTester.initFields(this, objectMappper);
	}

	@Test
	public void shouldCreateAFunction() throws Exception {
		when(this.functionService.save(FUNCTION_REQUEST)).thenReturn(FUNCTION_RESPONSE);

		mockMvc.perform(post("/functions/")
			.contentType(contentType)
			.content(jacksonTester.write(FUNCTION_REQUEST).getJson()))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(ID.intValue())))
			.andExpect(jsonPath("$.name", is(NAME)))
			.andExpect(jsonPath("$.petrobrasPost", is(PETROBRAS_POST)));
	}

	@Test
	public void shouldReturnAFunction() throws Exception {
		when(this.functionService.findById(ID)).thenReturn(Optional.of(FUNCTION_RESPONSE));

		mockMvc.perform(get("/functions").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(ID.intValue())))
			.andExpect(jsonPath("$.name", is(NAME)))
			.andExpect(jsonPath("$.petrobrasPost", is(PETROBRAS_POST)));
	}
}
