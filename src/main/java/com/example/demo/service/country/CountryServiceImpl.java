package com.example.demo.service.country;

import com.example.demo.model.Country;
import com.example.demo.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CountryServiceImpl implements ICountryService{
    @Autowired
    private ICountryRepository iCountryRepository;


    @Override
    public Iterable<Country> findAll() {
        return iCountryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return iCountryRepository.findById(id);
    }

    @Override
    public Country save(Country country) {
        return iCountryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        iCountryRepository.deleteById(id);
    }
}
