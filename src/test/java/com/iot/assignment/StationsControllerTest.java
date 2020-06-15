package com.iot.assignment;

import com.iot.assignment.model.weather.display.RepositoryForecasts;
import com.iot.assignment.model.weather.display.RepositoryObservations;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(StationsController.class)
@RunWith(SpringRunner.class)
public class StationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryObservations repositoryObservations;
    @MockBean
    private RepositoryForecasts repositoryForecasts;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new StationsController(repositoryObservations, repositoryForecasts)).build();
    }

    @Test
    public void getAllStationsOK() throws Exception {
        mockMvc.perform(getReqStations()).andExpect(status().isOk());
    }

    @Test
    public void getAllStationsUTF8() throws Exception {
        mockMvc.perform(getReqStations().accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    private MockHttpServletRequestBuilder getReqStations() {
        return get("/api/stations");
    }

    @Test
    public void getAllStations404() throws Exception {
        mockMvc.perform(get("/doesnotexist")).andExpect(status().isNotFound());
    }

    @Test
    public void getAllStationsBodyNotNullWhenMocked() throws Exception {
        MvcResult result = mockMvc.perform(getReqStations().accept(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        String content = response.getContentAsString();
        log.info("getAllStationsBody content is " + content);
        assertNotNull(content);
    }
}