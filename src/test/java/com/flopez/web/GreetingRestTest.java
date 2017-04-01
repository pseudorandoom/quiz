package com.flopez.web;

import com.flopez.service.MessageCreator;
import com.flopez.service.impl.MessageCreatorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests {@link GreetingRest}
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GreetingRestTest {
    public static final String MOCKED_NEW_MESSAGE = "New Message!";
    private MockMvc mockMvc;

    @InjectMocks
    private GreetingRest greetingRest;

    @Spy
    private MessageCreator messageCreator = Mockito.spy(MessageCreatorImpl.class);

    @Before
    public void setup() {
        Mockito.when(messageCreator.getDefaultMessage()).thenReturn(MOCKED_NEW_MESSAGE);
        mockMvc = MockMvcBuilders.standaloneSetup(greetingRest).build();
    }

    @Test
    public void returnsPathIDAsJsonIDAndMockedFixedMessageAsJsonMessage() throws Exception {
        mockMvc.perform(get("/v1/messages/asdf"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("asdf")))
                .andExpect(jsonPath("$.message", is(MOCKED_NEW_MESSAGE)));
        Mockito.verify(messageCreator).getMessageById(Matchers.anyString());
    }
}