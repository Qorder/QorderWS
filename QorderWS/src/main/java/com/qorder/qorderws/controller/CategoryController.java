package com.qorder.qorderws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qorder.qorderws.dto.DetailedCategoryDTO;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.mapper.CategoryInfoDTOtoCategoryMapper;
import com.qorder.qorderws.mapper.CategoryToDtoMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.service.ICategoryService;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessController.class);

	@Autowired
	private ICategoryService categoryService;

	@RequestMapping(value = "/business", params = "id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> crateCategory(@RequestParam Long id, @RequestBody CategoryDTO categoryDTO) throws BusinessDoesNotExistException {
		LOGGER.info("Request for category creation");
		categoryService.createCategory(id, new CategoryInfoDTOtoCategoryMapper().map(categoryDTO, new Category()));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/category", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DetailedCategoryDTO> getCategory(@RequestParam Long id) throws CategoryDoesNotExistException {
		LOGGER.info("Request for category with id equals "+id);
		Category fetchedCategory = categoryService.fetchCategoryByID(id);
		return new ResponseEntity<DetailedCategoryDTO>( new CategoryToDtoMapper().map(fetchedCategory, new DetailedCategoryDTO()) , HttpStatus.OK);
	}
	
	
	@ExceptionHandler({ CategoryDoesNotExistException.class, BusinessDoesNotExistException.class })
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
