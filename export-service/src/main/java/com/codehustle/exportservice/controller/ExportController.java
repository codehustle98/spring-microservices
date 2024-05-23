package com.codehustle.exportservice.controller;

import com.codehustle.exportservice.model.User;
import com.codehustle.exportservice.service.UserService;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(value = "/export")
public class ExportController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<StreamingResponseBody> exportUsers(){
        List<User> users = userService.getUsers();
        StreamingResponseBody stream = outputStream -> {
            try(Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)){
                try{
                    new StatefulBeanToCsvBuilder<User>(writer)
                            .build().write(users);

                }catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e){
                    e.printStackTrace();
                }
            }
        };
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", "user_list.csv"))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .body(stream);
    }
}
