package com.apiexample.service;

import com.apiexample.payload.RegistrationsDto;

import java.util.List;

public interface RegistrationsService {

    public RegistrationsDto createRegistrations(RegistrationsDto registrationsDto);

    void deleteRegistrationsById(long id);


    RegistrationsDto updateRegistratins(long id, RegistrationsDto registrationsDto);

    List<RegistrationsDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir);

    RegistrationsDto getRegistrationsById(long id);
}
