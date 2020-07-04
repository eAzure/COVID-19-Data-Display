package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Override
	public List<ChinaData> findAllChinaData() {
		// TODO Auto-generated method stub
		return userMapper.selectAllChinaData();
	}
	@Override
	public List<ChinaData> findAllConsDateChinaData(Integer dateId) {
		// TODO Auto-generated method stub
		return userMapper.selectConsDateChinaData(dateId);
	}
	@Override
	public List<WorldData> findAllConsDateWorldData(Integer dateId) {
		// TODO Auto-generated method stub
		return userMapper.selectConsDateWorldData(dateId);
	}
	@Override
	public List<WorldData> findAllWorldData() {
		// TODO Auto-generated method stub
		return userMapper.selectAllWorldData();
	}
	@Override
	public List<TripData> findAllTripData() {
		// TODO Auto-generated method stub
		return userMapper.selectAllTripData();
	}
	@Override
	public List<WorldData> findAllChinaDataFromWorld() {
		// TODO Auto-generated method stub
		return userMapper.selectChinaDataFromWorld();
	}
	@Override
	public List<ChinaData> findAllProvinceData(String ProvinceName) {
		// TODO Auto-generated method stub
		return userMapper.selectProvinceData(ProvinceName);
	}
	@Override
	public List<WorldTotalData> findAllWorldTotalData() {
		// TODO Auto-generated method stub
		return userMapper.selectAllWorldTotalData();
	}
	@Override
	public List<WorldData> findAllCountryData(String CountryFullName) {
		// TODO Auto-generated method stub
		return userMapper.selectCountryData(CountryFullName);
	}
	@Override
	public WorldData findAllIncrChinaData(Integer dateId) {
		// TODO Auto-generated method stub
		return userMapper.selectIncrChinaData(dateId);
	}
	@Override
	public WorldTotalData findAllIncrWorldData(Integer dateId) {
		// TODO Auto-generated method stub
		return userMapper.selectIncrWorldData(dateId);
	}
	@Override
	public Integer findAllOutNum(String t_pos_start) {
		// TODO Auto-generated method stub
		return userMapper.selectTheOutNumOfProvince(t_pos_start);
	}
	@Override
	public Integer findAllConfirmedOutNum(String t_pos_start) {
		// TODO Auto-generated method stub
		return userMapper.selectAllConfirmedOutNum(t_pos_start);
	}
	@Override
	public List<TripData> findAllDealedTripData() {
		// TODO Auto-generated method stub
		return userMapper.selectAllDealedTripData();
	}

}
