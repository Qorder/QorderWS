package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.model.product.Product;
import com.qorder.qorderws.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    private final IMapper<?,?> mapper;

    @Autowired
    public ProductService(IProductRepository productRepository, IMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public DetailedProductDTO fetchProductById(long productId) {
        Product product = productRepository.findOne(productId);
        return Objects.nonNull(product)
                ? mapper.map(product)
                        .to(new DetailedProductDTO())
                        .get()
                : new DetailedProductDTO();
    }

}
