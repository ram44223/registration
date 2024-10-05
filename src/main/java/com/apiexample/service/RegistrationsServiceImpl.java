package com.apiexample.service;

import com.apiexample.entity.Registrations;
import com.apiexample.exception.ResourceNotFound;
import com.apiexample.payload.RegistrationsDto;
import com.apiexample.repository.RegistrationsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationsServiceImpl implements RegistrationsService{


    private RegistrationsRepository registrationsRepository;

    public RegistrationsServiceImpl(RegistrationsRepository registrationsRepository) {
        this.registrationsRepository = registrationsRepository;
    }


    @Override
    public RegistrationsDto createRegistrations(RegistrationsDto registrationsDto) {
       Registrations registrations= mapToEntity(registrationsDto);
       Registrations savedEntity=    registrationsRepository.save(registrations);
        RegistrationsDto dto=mapToDto(savedEntity);
        dto.setMessage("Regsitrations deleted");
       return dto;
    }


    @Override
    public void deleteRegistrationsById(long id) {
        registrationsRepository.deleteById(id);
    }

    @Override
    public RegistrationsDto updateRegistratins(long id, RegistrationsDto registrationsDto) {
      Optional<Registrations> opReg= registrationsRepository.findById(id);
      Registrations registrations =opReg.get();

      registrations.setName(registrationsDto.getName());
      registrations.setEmail(registrationsDto.getEmail());
      registrations.setMobile(registrationsDto.getMobile());
      Registrations savedEntity= registrationsRepository.save(registrations);
      RegistrationsDto dto= mapToDto(savedEntity);

        return dto ;
    }


    @Override
    public List<RegistrationsDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
       // List<Registrations> registrations = registrationsRepository.findAll();
        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sort.Direction.ASC,sortBy):Sort.by(Sort.Direction.DESC,sortBy);
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        Page<Registrations> all=registrationsRepository.findAll(pageable);
        List<Registrations> registrations=all.getContent();
        List<RegistrationsDto> registrationsDtos=   registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        System.out.println(all.getTotalPages());
        System.out.println(all.isLast());
        System.out.println(all.isFirst());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());

        return registrationsDtos;
    }

    @Override
    public RegistrationsDto getRegistrationsById(long id) {

        Registrations registrations = registrationsRepository.findById(id).orElseThrow(() -> new ResourceNotFound("no such data found for id " + id));

        RegistrationsDto registrationsDto= mapToDto(registrations);
        return registrationsDto;
    }


    Registrations mapToEntity(RegistrationsDto dto){
        Registrations entity= new Registrations();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;

    }

    RegistrationsDto mapToDto(Registrations registrations){
        RegistrationsDto dto= new RegistrationsDto();
        dto.setId(registrations.getId());
        dto.setName(registrations.getName());
        dto.setEmail(registrations.getEmail());
        dto.setMobile(registrations.getMobile());
        return dto;

    }
}
