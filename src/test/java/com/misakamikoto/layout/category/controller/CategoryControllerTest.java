package com.misakamikoto.layout.category.controller;

import com.misakamikoto.layout.category.service.CategoryService;
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

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Category controller test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
		"file:src/main/webapp/WEB-INF/config/mybatis/mybatis-context.xml"})
@WebAppConfiguration
public class CategoryControllerTest {
	/**
	 * The Category service.
	 */
	@Mock
	CategoryService categoryService;

	@InjectMocks
	private CategoryController categoryController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	/**
	 * Sets up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}


	/**
	 * Test get category list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetCategoryList() throws Exception {
		mockMvc.perform(get("/category/list")).andExpect(status().isOk());
		verifyNoMoreInteractions(categoryService);
	}
}
