package com.epsilon.lx.service.assort;

import com.epsilon.lx.entities.Country;

import java.util.List;

/**
 *
 * 影视产地服务接口
 */
public interface ICountryService {

    /**
     * 获取所有国家
     * @return
     */
    List<Country> getListAll();

    /**
     * 通过id获取特定国家
     * @return
     */
    Country getCountryById(Long id);

    /**
     * 增加新得国家
     * @param country
     * @return
     */
    Country addCountry(Country country);

    /**
     * 批量增加新得国家
     * @param videoCountry
     * @return
     */
    List<Country> addCountryAll(List<Country> videoCountry);

}
