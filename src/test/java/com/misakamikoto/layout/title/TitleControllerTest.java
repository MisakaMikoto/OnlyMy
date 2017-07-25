package com.misakamikoto.layout.title;

import com.misakamikoto.layout.title.controller.TitleController;
import com.misakamikoto.layout.title.service.TitleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Title controller test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
		"file:src/main/webapp/WEB-INF/config/mybatis/mybatis-context.xml"})
@WebAppConfiguration
public class TitleControllerTest {
	/**
	 * The Title service.
	 */
	@Mock
	TitleService titleService;

	@InjectMocks
	private TitleController titleController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	/**
	 * Sets up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(titleController).build();
	}


	/**
	 * Test get name.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetName() throws Exception {
		mockMvc.perform(get("/title")).andExpect(status().isOk());

		verify(titleService, times(1)).getName();
		verifyNoMoreInteractions(titleService);

		assertTrue(titleService.getName() != null);
	}
}
