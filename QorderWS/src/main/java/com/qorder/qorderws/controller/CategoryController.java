package com.qorder.qorderws.controller;

import java.io.IOException;

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

import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.dto.category.DetailedCategoryDTO;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.MenuDoesNotExistException;
import com.qorder.qorderws.service.ICategoryService;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CategoryController.class);

	@Autowired
	private ICategoryService categoryService;

	@RequestMapping(value = "/menu", params = "id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> crateCategory(@RequestParam Long id, @RequestBody CategoryDTO categoryDTO) throws MenuDoesNotExistException {
		LOGGER.info("Request for category creation with business id equals :" + id);
		categoryService.createCategory(id, categoryDTO);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/category", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DetailedCategoryDTO> getCategory(@RequestParam Long id) throws CategoryDoesNotExistException {
		LOGGER.info("Request for category with id equals "+id);
		DetailedCategoryDTO detailedCategory = categoryService.fetchCategoryByID(id);
		return new ResponseEntity<DetailedCategoryDTO>( detailedCategory, HttpStatus.OK);
	}
	
	
	@ExceptionHandler({ CategoryDoesNotExistException.class, MenuDoesNotExistException.class})
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ IOException.class })
	ResponseEntity<String> sendIOException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
