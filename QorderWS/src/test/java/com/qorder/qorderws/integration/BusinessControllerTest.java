package com.qorder.qorderws.integration;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.qorder.qorderws.controller.BusinessController;
import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.service.IBusinessService;
import static org.mockito.Mockito.*;

public class BusinessControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	BusinessController businessController;
	
	@Mock
	IBusinessService businessService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(businessController).build();
	}
	
	@Test
	public void businessCreatedAndPathReturned() {
		
		//Mockito.when(businessService.createBusiness(any((BusinessDTO.class)))
		
	}
	

}
