package com.qorder.qorderws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Displays in raw format, a greed massage and some info about the host.
 *
 * @author Grigorios
 */
@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String formattedDate = LocalDateTime.now().format(dateFormat);
        Locale locale = Locale.getDefault();

        StringBuilder greedBuilder = new StringBuilder();
        greedBuilder.append("<center>")
                    .append("====================================================").append("</br>")
                    .append("   <h1>Welcome to QOrder Web Service!</h1>          ").append("</br>")
                    .append("====================================================").append("</br>")
                    .append("</center>")
                    .append("</br>")
                    .append("</br>");

        greedBuilder.append("The server locale is ").append(locale)
                    .append("</br>")
                    .append("The time on server is: ").append(formattedDate);

        return  greedBuilder.toString();
    }
}
