package com.apiexample.controller;

import com.apiexample.entity.Registrations;
import com.apiexample.payload.RegistrationsDto;
import com.apiexample.service.RegistrationsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/registrations")
public class RegistrationsController {

    private RegistrationsService registrationsService;

    public RegistrationsController(RegistrationsService registrationsService) {
        this.registrationsService = registrationsService;
    }

    //http://localhost:8080/api/v1/registrations

    @PostMapping
    public ResponseEntity<?> addRegistrations(
            @Valid @RequestBody RegistrationsDto registrationsDto,
             BindingResult result
    ){

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }

         RegistrationsDto regDto= registrationsService.createRegistrations(registrationsDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }
   // http://localhost:8080/api/v1/registrations?id=
    @DeleteMapping
    public ResponseEntity<String> deleteRegistrationsById(@RequestParam long id){
        registrationsService.deleteRegistrationsById(id);
        return new ResponseEntity<>("Registrations deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateRegistrations(
            @RequestParam long id,
            @Valid @RequestBody RegistrationsDto registrationsDto,
            BindingResult result

    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }

        RegistrationsDto dto =registrationsService.updateRegistratins(id,registrationsDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/registrations?pageNo=0&pageSize=5&sortBy=email&sortDir=asc
    @GetMapping
    public ResponseEntity<List<RegistrationsDto>> getAllRegistrations(
            @RequestParam(name="pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name="pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue="name", required=false) String sortBy,
            @RequestParam(name="sortDir",defaultValue="asc", required=false) String sortDir
    ) {
        List<RegistrationsDto> dtos = registrationsService.getAllRegistrations(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @GetMapping("/byid")
    public ResponseEntity<RegistrationsDto> getResgistrationsById(
            @RequestParam long id
    ){

        RegistrationsDto dto=registrationsService.getRegistrationsById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
