package com.qorder.qorderws.service;

import com.qorder.qorderws.WebServiceApplication;
import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.repository.IBusinessRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServiceApplication.class)
public class BusinessServiceTest {

    @InjectMocks
    private BusinessService businessService;

    @Mock
    private IBusinessRepository businessRepository;

    @Mock
    private IMapper<?,?> mapper;

    public BusinessServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        final Business persistedBusiness = new Business();
        persistedBusiness.setId(100L);
        persistedBusiness.setName("Mock Business");

        final Menu persistedMenu = new Menu();
        persistedMenu.setId(10L);
        persistedMenu.setCategoryList(new ArrayList<>());
        persistedMenu.setId(100L);
        persistedBusiness.setMenu(persistedMenu);

        final BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setMenuId(persistedMenu.getId());
        businessDTO.setName(persistedBusiness.getName());

        when(businessRepository.findOne(anyLong()))
                .thenReturn(persistedBusiness);

        when(businessRepository.save(any(Business.class)))
                .thenReturn(persistedBusiness);

    }

    @Test
    public void testCreateBusiness() {
        final Business business = new Business();
        business.setId(100L);
        when(mapper.map(any(BusinessDTO.class))
                .to(any(Business.class))
                .get())

                .thenReturn(business);

        BusinessDTO businessDto = new BusinessDTO();
        businessDto.setName("Fancy Business");
        long businessId = businessService.createBusiness(businessDto);
        assertEquals(100L, businessId);
    }

    @Test
    public void testFetchBusinessByID() {
        BusinessDTO dto = new BusinessDTO();
        dto.setName("Mock Business");
        when(mapper.map(any(Business.class)).to(any(BusinessDTO.class)).get())
                .thenReturn(dto);

        final BusinessDTO businessDTO = businessService.fetchBusinessByID(100);
        final Business business = businessRepository.findOne(100L);

        assertNotNull(businessDTO);
        assertEquals(businessDTO.getName(), business.getName());
    }
}