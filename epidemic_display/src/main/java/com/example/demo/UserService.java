package com.example.demo;

import java.util.List;


public interface UserService {
	List<ChinaData>findAllChinaData();
	List<WorldData>findAllWorldData();
	List<TripData>findAllTripData();
	List<ChinaData>findAllConsDateChinaData(Integer dateId);
	List<WorldData>findAllConsDateWorldData(Integer dateId);
	List<WorldData>findAllChinaDataFromWorld();//从世界数据中抽取中国数据
	List<ChinaData>findAllProvinceData(String ProvinceName);//选取各省份数据
	List<WorldTotalData>findAllWorldTotalData();//抽取世界总体数据
	List<WorldData>findAllCountryData(String CountryFullName);//获取到各个国家的数据
	WorldData findAllIncrChinaData(Integer dateId);//抽取中国新增数据用于饼图
	WorldTotalData findAllIncrWorldData(Integer dateId);//抽取世界新增数据用于饼图
	Integer findAllOutNum(String t_pos_start);//获取各省出度
	Integer findAllConfirmedOutNum(String t_pos_start);//获取各省外出同程的患者数
	List<TripData>findAllDealedTripData();//获取所有处理后的同程数据
}
