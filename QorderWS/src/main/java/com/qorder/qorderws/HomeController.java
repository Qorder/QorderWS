package com.qorder.qorderws;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("\n\t\t ===========================================================\n"
				     +"\t\t ||                                                       ||\n"
				     +"\t\t ||            Welcome to Qorder Web Service!             ||\n"
				     +"\t\t ||                                                       ||\n"
				     +"\t\t ===========================================================\n"
				     +"The server locale is {}.", locale);

		DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		String formattedDate = LocalDateTime.now().format(dateFormat);

		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

}
