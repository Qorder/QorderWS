package com.qorder.qorderws.service;

import com.qorder.qorderws.WebServiceApplication;
import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.mapper.BusinessToBusinessDTOMapper;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.repository.IBusinessRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServiceApplication.class)
public class BusinessServiceTest {

    @InjectMocks
    private BusinessService businessService;

    @Mock
    private IBusinessRepository businessRepository;

    public BusinessServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        final Business persistedBusiness = new Business();
        persistedBusiness.setId(100L);
        persistedBusiness.setName("Mock Business");
        final Menu persistedMenu = new Menu();
        persistedMenu.setCategoryList(new ArrayList<>());
        persistedMenu.setId(100L);
        persistedBusiness.setMenu(persistedMenu);

        when(businessRepository.findOne(persistedBusiness.getId()))
                .thenReturn(persistedBusiness);

        when(businessRepository.save(any(Business.class)))
                .thenReturn(persistedBusiness);
    }

    @Test
    public void testCreateBusiness() throws Exception {
        BusinessDTO business = new BusinessDTO();
        business.setName("Fancy Business");
        long businessId = businessService.createBusiness(business);
        assertEquals(100L, businessId);
    }

    @Test
    public void testFetchBusinessByID() throws Exception {
        BusinessDTO businessDTO = businessService.fetchBusinessByID(100);
        assertNotNull(businessDTO);
    }
}