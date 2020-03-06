package com.epsilon.lx.service.assort.impl;

import com.epsilon.lx.entities.Country;
import com.epsilon.lx.entities.CountryExample;
import com.epsilon.lx.mapper.CountryMapper;
import com.epsilon.lx.service.assort.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ICountryServiceImpl implements ICountryService {

    @Autowired
    private CountryMapper countryMapper;

    /**
     * 获取所有国家
     *
     * @return
     */
    @Override
    public List<Country> getListAll() {
        return countryMapper.selectByExample(new CountryExample());
    }

    /**
     * 通过id获取特定国家
     * @param id
     * @return
     */
    @Override
    public Country getCountryById(Long id) {
        return countryMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加新得国家
     *
     * @param country
     * @return
     */
    @Override
    @Transactional
    public Country addCountry(Country country) {
        CountryExample example = new CountryExample();
        example.createCriteria().andAbbreviationEqualTo(country.getAbbreviation());
        List<Country> countryList = countryMapper.selectByExample(example);
        if (!countryList.isEmpty())
            return countryList.get(0);
        else {
            countryMapper.insertSelective(country);
            return country;
        }
    }

    /**
     * 批量增加新得国家
     *
     * @param videoCountries
     * @return
     */
    @Override
    @Transactional
    public List<Country> addCountryAll(List<Country> videoCountries) {
        List<Country> countryList = new ArrayList<>(videoCountries.size());
        CountryExample example = new CountryExample();
        for (Country category :
                videoCountries) {
            example.createCriteria().andAbbreviationEqualTo(category.getAbbreviation());
            List<Country> countries = countryMapper.selectByExample(example);
            if (!countries.isEmpty())
                countryList.add(countries.get(0));
            else {
                countryMapper.insertSelective(category);
                countryList.add(category);
            }
        }
        return countryList;

    }
}
