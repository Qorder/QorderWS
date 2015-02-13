package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.BusinessDTO;

import java.util.Collection;

public interface IBusinessService {

    long createBusiness(BusinessDTO businessDTO);

    BusinessDTO fetchBusinessByID(long businessId);

    Collection<BusinessDTO> fetchBusinessesByUser(long userId);

}
