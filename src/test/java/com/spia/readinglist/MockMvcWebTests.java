package com.spia.readinglist;

import com.spia.readinglist.model.Book;
import com.spia.readinglist.model.Reader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ReadinglistApplication.class
)
@AutoConfigureMockMvc
public class MockMvcWebTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webContext;

    @Before
    public void setupMvc() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void homePage() throws Exception {
        mvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));


    }

    @Test
    public void postBook() throws Exception {
        mvc.perform(post("/readingList")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "test title")
                        .param("author", "test author")
                        .param("isbn", "12334566")
                        .param("description", "test description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/readingList"));

        var expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setReader("readingList");
        expectedBook.setTitle("test title");
        expectedBook.setAuthor("test author");
        expectedBook.setIsbn("12334566");
        expectedBook.setDescription("test description");

        mvc.perform(get("/readingList"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));

    }

    @Test
    public void unauthenticatedAccess() throws Exception {
        mvc.perform(get("/carlos"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    @Test
    @WithMockUser(
            username = "carlos",
            password = "carlos",
            roles = "READER"
    )
    public void authenticatedAccess() throws Exception {
        mvc.perform(get("/carlos"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"));
    }

    @Test
    @WithUserDetails("carlos")
    public void inContextAuthenticatedAccess() throws Exception {
        var expectedReader = new Reader();
        expectedReader.setPassword("carlos");
        expectedReader.setUserName("carlos");
        expectedReader.setFullName("carlos rojas");
        mvc.perform(get("/carlos"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attribute("reader",samePropertyValuesAs(expectedReader.getUsername())))
                .andExpect(model().attribute("books",hasSize(0)));
    }

}
