package com.example.demo;

import java.util.List;

public interface UserMapper {
	List<ChinaData>selectAllChinaData();
	List<WorldData>selectAllWorldData();
	List<TripData>selectAllTripData();
	List<ChinaData>selectConsDateChinaData(Integer dateId);
	List<WorldData>selectConsDateWorldData(Integer dateId);
	List<WorldData>selectChinaDataFromWorld();//从世界数据中抽取中国数据
	List<ChinaData>selectProvinceData(String ProvinceName);//选择各省份数据
	List<WorldTotalData>selectAllWorldTotalData();//抽取世界总体数据
	List<WorldData>selectCountryData(String CountryFullName);//获取到各国家数据
	WorldData selectIncrChinaData(Integer dateId);//抽取中国疑似数据用于饼图
	WorldTotalData selectIncrWorldData(Integer dateId);//抽取外国新增数据用于饼图
	Integer selectTheOutNumOfProvince(String t_pos_start);//查询每个省对应的出度用于关系图
	Integer selectAllConfirmedOutNum(String t_pos_start);//查询每个省所出的患者数
	List<TripData>selectAllDealedTripData();//获取所有处理后的外出数据
}
