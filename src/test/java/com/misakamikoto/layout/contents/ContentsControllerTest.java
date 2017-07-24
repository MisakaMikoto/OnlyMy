package com.misakamikoto.layout.contents;

import com.misakamikoto.layout.contents.controller.ContentsController;
import com.misakamikoto.layout.contents.service.ContentsService;
import com.misakamikoto.layout.contents.service.YoutubeUploadService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Contents controller test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
		"file:src/main/webapp/WEB-INF/config/mybatis/mybatis-context.xml"})
@WebAppConfiguration
public class ContentsControllerTest {
	/**
	 * The Contents service.
	 */
	@Mock
	ContentsService contentsService;

	/**
	 * The Youtube upload service.
	 */
	@Mock
	YoutubeUploadService youtubeUploadService;

	@InjectMocks
	private ContentsController contentsController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	/**
	 * Sets up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(contentsController).build();
	}

	/**
	 * Test get contents list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetContentsList() throws Exception {
		String testCategoryCode = "a";
		when(mockMvc.perform(get("/contents/list/{categoryCode}", testCategoryCode))
				.andExpect(status().isOk()));

		verify(contentsService, times(1)).getContentsList(testCategoryCode);
		verifyNoMoreInteractions(contentsService);
	}

	/**
	 * Test get newest content.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetNewestContent() throws Exception {
		when(mockMvc.perform(get("/contents/newest"))
				.andExpect(status().isOk()));

		verify(contentsService, times(1)).getNewestContent();
		verifyNoMoreInteractions(contentsService);
	}

	/**
	 * Test get content.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetContent() throws Exception {
		int testContentId = 1;
		when(mockMvc.perform(get("/contents/{contentId}", testContentId))
				.andExpect(status().isOk()));

		verify(contentsService, times(1)).getContent(testContentId);
		verifyNoMoreInteractions(contentsService);
	}

	/**
	 * Add content.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAddContent() throws Exception {
		String testCategoryCode = "testCetagoryCode";
		String testTitle = "testTitle";
		String testDescription = "testDescription";
		String testVideoId = "testVideoId";

		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.param("categoryCode", testCategoryCode)
				.param("title", testTitle)
				.param("description", testDescription)
				.param("videoId", testVideoId))
				.andExpect(status().isOk());

		verify(contentsService, times(1)).addContent(testCategoryCode, testTitle, testDescription, testVideoId);
		verifyNoMoreInteractions(contentsService);
	}

	/**
	 * Test youtube upload.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testYoutubeUpload() throws Exception {
		MockMultipartFile testFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		String testCategoryCode = "testCategoryCode";
		String testTitle = "testTitle";
		String testDescription = "testDescription";

		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.file(testFile)
				.param("categoryCode", testCategoryCode)
				.param("title", testTitle)
				.param("description", testDescription))
				.andExpect(status().isOk());

		verify(youtubeUploadService, times(1)).upload(testFile, testCategoryCode, testTitle, testDescription);
		verifyNoMoreInteractions(youtubeUploadService);
	}
}
