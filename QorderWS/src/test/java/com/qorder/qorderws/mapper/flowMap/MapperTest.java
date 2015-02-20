package com.qorder.qorderws.mapper.flowMap;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.mapper.PropertyMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.menu.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MapperTest {

    private IMapper<?,?> mapper;

    public MapperTest() {

    }

    @Before
    public void setUp() throws Exception {
        mapper = new PropertyMapper<>();
    }

    @After
    public void tearDown() throws Exception {
        mapper = null;
    }

    @Test
    public void suceessfulObjectMappingtest() {
        Business business = new Business();
        business.setId(10L);
        business.setName("Coffee Friends");
        business.setMenu(new Menu());

        BusinessDTO businessDTO = mapper.map(business)
                .to(new BusinessDTO())
                .get();

        assertEquals(business.getName(), businessDTO.getName());
    }


}