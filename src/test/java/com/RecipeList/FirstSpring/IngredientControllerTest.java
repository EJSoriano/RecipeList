package com.RecipeList.FirstSpring;

import com.RecipeList.FirstSpring.exception.ErrorResponse;
import com.RecipeList.FirstSpring.exception.GlobalExceptionHandler;
import com.RecipeList.FirstSpring.exception.ItemDoesNotExistException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientController ingredientController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void createIngredient() throws Exception {

        Ingredient requestIngredient = new Ingredient();
        requestIngredient.setName("test");

        Ingredient responseIngredient = new Ingredient();
        responseIngredient.setName("test");
        responseIngredient.setId(1);

        Mockito.doReturn(responseIngredient).when(ingredientRepository).save(Mockito.any());

        mockMvc.perform(post("/ingredients")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(requestIngredient)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(responseIngredient)));

        Mockito.verify(ingredientRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    public void updateIngredientDoesNotExist() throws Exception {

        Ingredient requestIngredient = new Ingredient();
        requestIngredient.setName("banana");

        Mockito.doReturn(Optional.empty()).when(ingredientRepository).findById(Mockito.any());

        ErrorResponse response = new ErrorResponse("The Ingredient with id 1 does not exist.");

        mockMvc.perform(put("/ingredients/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestIngredient)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));

    }

}
