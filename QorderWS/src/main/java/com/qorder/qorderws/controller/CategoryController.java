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

import com.qorder.qorderws.dto.CategoryInfoDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.mapper.CategoryInfoDTOtoCategoryMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.service.ICategoryService;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessController.class);

	@Autowired
	private ICategoryService categoryService;

	@RequestMapping(value = "/", params = { "business=id" }, method = RequestMethod.PUT)
	ResponseEntity<Void> crateCategory(@RequestParam Long id, @RequestBody CategoryInfoDTO categoryDTO) throws BusinessDoesNotExistException {
		LOGGER.info("Request for category creation");
		categoryService.createCategory(id, new CategoryInfoDTOtoCategoryMapper().map(categoryDTO, new Category()));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(BusinessDoesNotExistException.class)
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

}
