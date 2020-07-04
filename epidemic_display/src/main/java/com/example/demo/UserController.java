package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="/user")
public class UserController {
	//新建内部类
	public class concreteData{
		private String name;
		private Integer value;
		concreteData(String name,Integer value){
			this.name=name;
			this.value=value;
		}
		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name=name;
		}
		public Integer getValue() {
			return this.value;
		}
		public void setValue(Integer value) {
			this.value=value;//这里曾进行修改 5.23
		}
		@Override
		public String toString() {
			return "ConcreteData{"+
					"name="+name+
					",value="+value+
					'}';
		}
	}
	//新建内部类
	//用于折线图
	public class ChinaDataGroup{
		private ArrayList<Integer> dateId;
		private ArrayList<Integer> confirmedIncr;
		private ArrayList<Integer> suspectedCountIncr;
		private ArrayList<Integer> deadIncr;
		private ArrayList<Integer> confirmedCount;
		private ArrayList<Integer> suspectedCount;
		private ArrayList<Integer> deadCount;
		ChinaDataGroup(ArrayList<Integer> dateId,ArrayList<Integer> confirmedIncr,ArrayList<Integer> suspectedCountIncr,ArrayList<Integer> deadIncr,ArrayList<Integer> confirmedCount,ArrayList<Integer> suspectedCount,ArrayList<Integer> deadCount){
			this.dateId=dateId;
			this.confirmedIncr=confirmedIncr;
			this.suspectedCountIncr=suspectedCountIncr;
			this.deadIncr=deadIncr;
			this.confirmedCount=confirmedCount;
			this.suspectedCount=suspectedCount;
			this.deadCount=deadCount;
		}
		public ArrayList<Integer> getDateId() {
			return dateId;
		}
		public void setDateId(ArrayList<Integer> dateId) {
			this.dateId = dateId;
		}
		public ArrayList<Integer> getConfirmedIncr() {
			return confirmedIncr;
		}
		public void setConfirmedIncr(ArrayList<Integer> confirmedIncr) {
			this.confirmedIncr = confirmedIncr;
		}
		public ArrayList<Integer> getSuspectedCountIncr() {
			return suspectedCountIncr;
		}
		public void setSuspectedCountIncr(ArrayList<Integer> suspectedCountIncr) {
			this.suspectedCountIncr = suspectedCountIncr;
		}
		public ArrayList<Integer> getDeadIncr() {
			return deadIncr;
		}
		public void setDeadIncr(ArrayList<Integer> deadIncr) {
			this.deadIncr = deadIncr;
		}
		public ArrayList<Integer> getConfirmedCount() {
			return confirmedCount;
		}
		public void setConfirmedCount(ArrayList<Integer> confirmedCount) {
			this.confirmedCount = confirmedCount;
		}
		public ArrayList<Integer> getSuspectedCount() {
			return suspectedCount;
		}
		public void setSuspectedCount(ArrayList<Integer> suspectedCount) {
			this.suspectedCount = suspectedCount;
		}
		public ArrayList<Integer> getDeadCount() {
			return deadCount;
		}
		public void setDeadCount(ArrayList<Integer> deadCount) {
			this.deadCount = deadCount;
		}
		@Override
		public String toString() {
			return "ChinaDataGroup{"+
					"dateId="+dateId+
					",confirmedIncr='"+confirmedIncr+'\''+
					",suspectedCountIncr='"+suspectedCountIncr+'\''+
					",deadIncr='"+deadIncr+'\''+
					",confirmedCount='"+confirmedCount+'\''+
					",suspectedCount='"+suspectedCount+'\''+
					",deadCount="+deadCount+
					'}';
		}
	}
	//新建内部类
	//用于柱状图
	public class barDataGroup{
		private ArrayList<Integer> confirmedCountList;//累计确诊
		private ArrayList<Integer> curConfirmedCountList;//累计疑似
		private ArrayList<Integer> deadCount;//累计死亡
		private ArrayList<String> shortNameList;//简称
		barDataGroup(ArrayList<Integer>confirmedCountList,ArrayList<Integer>curConfirmedCountList,ArrayList<Integer>deadCount,ArrayList<String>shortNameList){
			this.confirmedCountList=confirmedCountList;
			this.curConfirmedCountList=curConfirmedCountList;
			this.deadCount=deadCount;
			this.shortNameList=shortNameList;
		}
		public ArrayList<Integer> getConfirmedCountList() {
			return confirmedCountList;
		}
		public void setConfirmedCountList(ArrayList<Integer> confirmedCountList) {
			this.confirmedCountList = confirmedCountList;
		}
		public ArrayList<Integer> getCurConfirmedCountList() {
			return curConfirmedCountList;
		}
		public void setCurConfirmedCountList(ArrayList<Integer> curConfirmedCountList) {
			this.curConfirmedCountList = curConfirmedCountList;
		}
		public ArrayList<Integer> getDeadCount() {
			return deadCount;
		}
		public void setDeadCount(ArrayList<Integer> deadCount) {
			this.deadCount = deadCount;
		}
		public ArrayList<String> getShortNameList() {
			return shortNameList;
		}
		public void setShortNameList(ArrayList<String> shortNameList) {
			this.shortNameList = shortNameList;
		}
		@Override
		public String toString() {
			return "barDataGroup{"+
					"confirmedCountList="+confirmedCountList+
					",curConfirmedCountList='"+curConfirmedCountList+'\''+
					",deadCount='"+deadCount+'\''+
					",shortNameList="+shortNameList+
					'}';
		}
	}
	
	@Autowired
	private UserServiceImpl userService;
	//用户操作
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaData")
	@ResponseBody
	public List<ChinaData> findAllChinaData() {
	    return userService.findAllChinaData();
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllWorldData")
	@ResponseBody
	public List<WorldData> findAllWolrdData() {
	    return userService.findAllWorldData();
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllTripData")
	@ResponseBody
	public List<TripData> findAllTripData() {
	    return userService.findAllTripData();
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllConsDateChinaData{dateId}", method = RequestMethod.GET)
	@ResponseBody
	public List<ChinaData> findAllConsDateChinaData(Integer dateId) {
	    return userService.findAllConsDateChinaData(dateId);
	}
	//将获取到的具体的某一天的中国数据整理为集合
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllConDateData{dateId}", method = RequestMethod.GET)
	@ResponseBody
	public List<concreteData> findAllConDateData(Integer dateId) {
	    List<ChinaData>tempData=userService.findAllConsDateChinaData(dateId);
	    List<concreteData> result = new ArrayList<concreteData>();
	    for(int i=0;i<tempData.size();i++) {
	    	concreteData conTemp=new concreteData(tempData.get(i).getProvinceShortName(),tempData.get(i).getConfirmedCount());
	    	result.add(conTemp);
	    }
	    return result;
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllConDateWorldData{dateId}", method = RequestMethod.GET)
	@ResponseBody
	public List<concreteData> findAllConDateWorldData(Integer dateId) {
	    List<WorldData>tempData=userService.findAllConsDateWorldData(dateId);
	    List<concreteData> result = new ArrayList<concreteData>();
	    for(int i=0;i<tempData.size();i++) {
	    	concreteData conTemp=new concreteData(tempData.get(i).getCountryFullName(),tempData.get(i).getConfirmedCount());
	    	result.add(conTemp);
	    }
	    return result;
	}
	//抽取中国新增疑似人数，用于饼图
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllIncrChinaData{dateId}", method = RequestMethod.GET)
	@ResponseBody
	public List<concreteData> findAllIncrChinaData(Integer dateId) {
	    WorldData tempData;
	    //加一个判断，判断返回的是否为空值，防止出现500错误
	    if(userService.findAllIncrChinaData(dateId)==null) {
	    	tempData=new WorldData(0,0,0,0,0,0,0,dateId,0,0,0,0,"","","","");
	    }else {
	    	tempData=userService.findAllIncrChinaData(dateId);
	    }
	    List<concreteData> result = new ArrayList<concreteData>();
	    concreteData temp1=new concreteData("新增确诊", tempData.getConfirmedIncr());
	    concreteData temp2=new concreteData("新增疑似",tempData.getSuspectedCountIncr());
	    concreteData temp3=new concreteData("新增死亡",tempData.getDeadIncr());
	    result.add(temp1);
	    result.add(temp2);
	    result.add(temp3);
	    return result;
	}
	//抽取外国新增疑似人数，用于饼图
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllIncrWorldData{dateId}", method = RequestMethod.GET)
	@ResponseBody
	public List<concreteData> findAllIncrWorldData(Integer dateId) {
	    WorldTotalData tempData;
	    //加一个判断，判断返回的值是否为空，防止出现500错误
	    if(userService.findAllIncrWorldData(dateId)==null) {
	    	tempData=new WorldTotalData(0,0,0,0,0,0,0,dateId,0,0,0,0);
	    }else {
	    	tempData=userService.findAllIncrWorldData(dateId);
	    }
	    List<concreteData> result = new ArrayList<concreteData>();
	    concreteData temp1=new concreteData("新增确诊", tempData.getConfirmedIncr());
	    concreteData temp2=new concreteData("新增疑似",tempData.getSuspectedCountIncr());
	    concreteData temp3=new concreteData("新增死亡",tempData.getDeadIncr());
	    result.add(temp1);
	    result.add(temp2);
	    result.add(temp3);
	    return result;
	}
	
	//抽取各省份数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllProvinceData{ProvinceName}", method = RequestMethod.GET)
	@ResponseBody
	public List<ChinaData> findAllProvinceData(String ProvinceName) {
	    return userService.findAllProvinceData(ProvinceName);
	}
	//收取各省份数据集合
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllProvinceDataGroup{ProvinceName}", method = RequestMethod.GET)
	@ResponseBody
	public ChinaDataGroup findAllProvinceDataGroup(String ProvinceName) {
	    List<ChinaData>tempData= userService.findAllProvinceData(ProvinceName);
	    ArrayList<Integer>dateData=new ArrayList<Integer>();
	    ArrayList<Integer>confirmedIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>suspectedCountIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>deadIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>confirmedData=new ArrayList<Integer>();
	    ArrayList<Integer>suspectedData=new ArrayList<Integer>();
	    ArrayList<Integer>deadData=new ArrayList<Integer>();
	    for(int i=0;i<tempData.size();i++) {
	    	Integer dateInt=tempData.get(i).getDateId();
	    	dateData.add(dateInt);
	    	Integer confirmedIncrInt=tempData.get(i).getConfirmedIncr();
	    	confirmedIncrData.add(confirmedIncrInt);
	    	Integer suspectedCountIncrInt=tempData.get(i).getSuspectedCountIncr();
	    	suspectedCountIncrData.add(suspectedCountIncrInt);
	    	Integer deadIncrInt=tempData.get(i).getDeadIncr();
	    	deadIncrData.add(deadIncrInt);
	    	Integer confirmedInt=tempData.get(i).getConfirmedCount();
	    	confirmedData.add(confirmedInt);
	    	Integer suspectedInt=tempData.get(i).getSuspectedCount();
	    	suspectedData.add(suspectedInt);
	    	Integer deadInt=tempData.get(i).getDeadCount();
	    	deadData.add(deadInt);
	    }
	    ChinaDataGroup result=new ChinaDataGroup(dateData,confirmedIncrData,suspectedCountIncrData,deadIncrData,confirmedData,suspectedData,deadData);
	    return result;
	}
	//抽取各省份数据集，用于柱状图
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllProvinceBarDataGroup{dateId}", method = RequestMethod.GET)
	@ResponseBody
	public barDataGroup findAllProvinceBarDataGroup(Integer dateId) {
	    List<ChinaData>tempData= userService.findAllConsDateChinaData(dateId);
	    ArrayList<Integer>confirmedCountList=new ArrayList<Integer>();
	    ArrayList<Integer>curConfirmedCountList=new ArrayList<Integer>();
	    ArrayList<Integer>deadCount=new ArrayList<Integer>();
	    ArrayList<String>shortNameList=new ArrayList<String>();
	    for(int i=0;i<tempData.size();i++) {
	    	Integer confirmedInt=tempData.get(i).getConfirmedCount();
	    	confirmedCountList.add(confirmedInt);
	    	Integer curConfirmedInt=tempData.get(i).getCurrentConfirmedCount();
	    	curConfirmedCountList.add(curConfirmedInt);
	    	Integer deadInt=tempData.get(i).getDeadCount();
	    	deadCount.add(deadInt);
	    	String shortName=tempData.get(i).getProvinceShortName();
	    	shortNameList.add(shortName);
	    }
	    barDataGroup result=new barDataGroup(confirmedCountList,curConfirmedCountList,deadCount,shortNameList);
	    return result;
	}
	//抽取各国家数据集，用于柱状图
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllCountryBarDataGroup{dateId}", method = RequestMethod.GET)
	@ResponseBody
	public barDataGroup findAllCountryBarDataGroup(Integer dateId) {
		List<WorldData>tempData=userService.findAllConsDateWorldData(dateId);
		ArrayList<Integer>confirmedCountList=new ArrayList<Integer>();
		ArrayList<Integer>curConfirmedCountList=new ArrayList<Integer>();
		ArrayList<Integer>deadCount=new ArrayList<Integer>();
		ArrayList<String>shortNameList=new ArrayList<String>();
		for(int i=0;i<tempData.size();i++) {
			Integer confirmedInt=tempData.get(i).getConfirmedCount();
			confirmedCountList.add(confirmedInt);
			Integer curConfirmedInt=tempData.get(i).getCurrentConfirmedCount();
			curConfirmedCountList.add(curConfirmedInt);
			Integer deadInt=tempData.get(i).getDeadCount();
			deadCount.add(deadInt);
			String shortName=tempData.get(i).getCountryName();
			shortNameList.add(shortName);
		}
		barDataGroup result=new barDataGroup(confirmedCountList,curConfirmedCountList,deadCount,shortNameList);
		return result;
	}
	//抽取各国家数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllCountryData{CountryFullName}", method = RequestMethod.GET)
	@ResponseBody
	public List<WorldData> findAllCountryData(String CountryFullName) {
	    return userService.findAllCountryData(CountryFullName);
	}
	//收取各国家数据集合
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllCountryDataGroup{CountryFullName}", method = RequestMethod.GET)
	@ResponseBody
	public ChinaDataGroup findAllCountryDataGroup(String CountryFullName) {
	    List<WorldData>tempData= userService.findAllCountryData(CountryFullName);
	    ArrayList<Integer>dateData=new ArrayList<Integer>();
	    ArrayList<Integer>confirmedIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>suspectedCountIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>deadIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>confirmedData=new ArrayList<Integer>();
	    ArrayList<Integer>suspectedData=new ArrayList<Integer>();
	    ArrayList<Integer>deadData=new ArrayList<Integer>();
	    for(int i=0;i<tempData.size();i++) {
	    	Integer dateInt=tempData.get(i).getDateId();
	    	dateData.add(dateInt);
	    	Integer confirmedIncrInt=tempData.get(i).getConfirmedIncr();
	    	confirmedIncrData.add(confirmedIncrInt);
	    	Integer suspectedCountIncrInt=tempData.get(i).getSuspectedCountIncr();
	    	suspectedCountIncrData.add(suspectedCountIncrInt);
	    	Integer deadIncrInt=tempData.get(i).getDeadIncr();
	    	deadIncrData.add(deadIncrInt);
	    	Integer confirmedInt=tempData.get(i).getConfirmedCount();
	    	confirmedData.add(confirmedInt);
	    	Integer suspectedInt=tempData.get(i).getSuspectedCount();
	    	suspectedData.add(suspectedInt);
	    	Integer deadInt=tempData.get(i).getDeadCount();
	    	deadData.add(deadInt);
	    }
	    ChinaDataGroup result=new ChinaDataGroup(dateData,confirmedIncrData,suspectedCountIncrData,deadIncrData,confirmedData,suspectedData,deadData);
	    return result;
	}
	
	//抽取世界总体数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllWorldTotalData", method = RequestMethod.GET)
	@ResponseBody
	public List<WorldTotalData> findAllWorldTotalData() {
	    return userService.findAllWorldTotalData();
	}
	//抽取具体分类世界总体数据集合，类似于抽取中国数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllWorldDataGroup", method = RequestMethod.GET)
	@ResponseBody
	public ChinaDataGroup findAllWorldDataGroup() {
		
	    List<WorldTotalData>tempData= userService.findAllWorldTotalData();
	    ArrayList<Integer>dateData=new ArrayList<Integer>();
	    ArrayList<Integer>confirmedIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>suspectedCountIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>deadIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>confirmedData=new ArrayList<Integer>();
	    ArrayList<Integer>suspectedData=new ArrayList<Integer>();
	    ArrayList<Integer>deadData=new ArrayList<Integer>();
	    for(int i=0;i<tempData.size();i++) {
	    	Integer dateInt=tempData.get(i).getDateId();
	    	dateData.add(dateInt);
	    	Integer confirmedIncrInt=tempData.get(i).getConfirmedIncr();
	    	confirmedIncrData.add(confirmedIncrInt);
	    	Integer suspectedCountIncrInt=tempData.get(i).getSuspectedCountIncr();
	    	suspectedCountIncrData.add(suspectedCountIncrInt);
	    	Integer deadIncrInt=tempData.get(i).getDeadIncr();
	    	deadIncrData.add(deadIncrInt);
	    	Integer confirmedInt=tempData.get(i).getConfirmedCount();
	    	confirmedData.add(confirmedInt);
	    	Integer suspectedInt=tempData.get(i).getSuspectedCount();
	    	suspectedData.add(suspectedInt);
	    	Integer deadInt=tempData.get(i).getDeadCount();
	    	deadData.add(deadInt);
	    }
	    ChinaDataGroup result=new ChinaDataGroup(dateData,confirmedIncrData,suspectedCountIncrData,deadIncrData,confirmedData,suspectedData,deadData);
	    return result;
	}
	//从世界数据中抽取中国数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaDataFromWorld", method = RequestMethod.GET)
	@ResponseBody
	public List<WorldData> findAllChinaDataFromWorld() {
	    return userService.findAllChinaDataFromWorld();
	}
	//测试收取所有符合条件类型的数据，一个一个嵌套获取太累了
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaDataGroup", method = RequestMethod.GET)
	@ResponseBody
	public ChinaDataGroup findAllChinaDataGroup() {
		
	    List<WorldData>tempData= userService.findAllChinaDataFromWorld();
	    ArrayList<Integer>dateData=new ArrayList<Integer>();
	    ArrayList<Integer>confirmedIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>suspectedCountIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>deadIncrData=new ArrayList<Integer>();
	    ArrayList<Integer>confirmedData=new ArrayList<Integer>();
	    ArrayList<Integer>suspectedData=new ArrayList<Integer>();
	    ArrayList<Integer>deadData=new ArrayList<Integer>();
	    for(int i=0;i<tempData.size();i++) {
	    	Integer dateInt=tempData.get(i).getDateId();
	    	dateData.add(dateInt);
	    	Integer confirmedIncrInt=tempData.get(i).getConfirmedIncr();
	    	confirmedIncrData.add(confirmedIncrInt);
	    	Integer suspectedCountIncrInt=tempData.get(i).getSuspectedCountIncr();
	    	suspectedCountIncrData.add(suspectedCountIncrInt);
	    	Integer deadIncrInt=tempData.get(i).getDeadIncr();
	    	deadIncrData.add(deadIncrInt);
	    	Integer confirmedInt=tempData.get(i).getConfirmedCount();
	    	confirmedData.add(confirmedInt);
	    	Integer suspectedInt=tempData.get(i).getSuspectedCount();
	    	suspectedData.add(suspectedInt);
	    	Integer deadInt=tempData.get(i).getDeadCount();
	    	deadData.add(deadInt);
	    }
	    ChinaDataGroup result=new ChinaDataGroup(dateData,confirmedIncrData,suspectedCountIncrData,deadIncrData,confirmedData,suspectedData,deadData);
	    return result;
	}
	//从中国数据中抽取时间
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaDateData", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findAllChinaDateData() {
		List<WorldData>tempData=userService.findAllChinaDataFromWorld();
		List<Integer>result=new ArrayList<Integer>();
		for(int i=0;i<tempData.size();i++) {
	    	Integer timeDate=tempData.get(i).getDateId();
	    	result.add(timeDate);
	    }
	    return result;
	}
	//从中国数据中抽取其他各种类型的数据
	//抽取新增确诊数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaConfirmedIncr", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findAllChinaConfirmedIncr() {
		List<WorldData>tempData=userService.findAllChinaDataFromWorld();
		List<Integer>result=new ArrayList<Integer>();
		for(int i=0;i<tempData.size();i++) {
	    	Integer timeDate=tempData.get(i).getConfirmedIncr();
	    	result.add(timeDate);
	    }
	    return result;
	}
	//抽取新增疑似数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaSuspectedCountIncr", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findAllChinaSuspectedCountIncr() {
		List<WorldData>tempData=userService.findAllChinaDataFromWorld();
		List<Integer>result=new ArrayList<Integer>();
		for(int i=0;i<tempData.size();i++) {
	    	Integer timeDate=tempData.get(i).getSuspectedCountIncr();
	    	result.add(timeDate);
	    }
	    return result;
	}
	//抽取新增死亡数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaDeadIncr", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findAllChinaDeadIncr() {
		List<WorldData>tempData=userService.findAllChinaDataFromWorld();
		List<Integer>result=new ArrayList<Integer>();
		for(int i=0;i<tempData.size();i++) {
	    	Integer timeDate=tempData.get(i).getDeadIncr();
	    	result.add(timeDate);
	    }
	    return result;
	}
	//抽取累计确诊数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaConfirmedCount", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findAllChinaConfirmedCount() {
		List<WorldData>tempData=userService.findAllChinaDataFromWorld();
		List<Integer>result=new ArrayList<Integer>();
		for(int i=0;i<tempData.size();i++) {
	    	Integer timeDate=tempData.get(i).getConfirmedCount();
	    	result.add(timeDate);
	    }
	    return result;
	}
	//抽取现有疑似数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaSuspectedCount", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findAllChinaSuspectedCount() {
		List<WorldData>tempData=userService.findAllChinaDataFromWorld();
		List<Integer>result=new ArrayList<Integer>();
		for(int i=0;i<tempData.size();i++) {
	    	Integer timeDate=tempData.get(i).getSuspectedCount();
	    	result.add(timeDate);
	    }
	    return result;
	}
	//抽取累计死亡数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllChinaDeadCount", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findAllChinaDeadCount() {
		List<WorldData>tempData=userService.findAllChinaDataFromWorld();
		List<Integer>result=new ArrayList<Integer>();
		for(int i=0;i<tempData.size();i++) {
	    	Integer timeDate=tempData.get(i).getDeadCount();
	    	result.add(timeDate);
	    }
	    return result;
	}
	//定义所有省会
	String chinaCity[]= {"北京","天津","上海","重庆","石家庄","太原","西安","济南","郑州","沈阳","长春","哈尔滨","南京","杭州","合肥","南昌","福州","武汉","长沙","成都","贵阳","昆明","广州","海口","兰州","西宁","台北","呼和浩特","乌鲁木齐","拉萨","南宁","银川","香港","澳门"};
	//需要构造两项
	//一项是各节点
	//构造内部类
	public class nodeGraph{
		private String name;
		private String category;
		private Integer value;
		private Integer symbolSize;
		nodeGraph(String name,String category,Integer value,Integer symbolSize){
			this.name=name;
			this.category=category;
			this.value=value;
			this.symbolSize=symbolSize;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}
		public Integer getSymbolSize() {
			return symbolSize;
		}
		public void setSymbolSize(Integer symbolSize) {
			this.symbolSize = symbolSize;
		}
		@Override
		public String toString() {
			return "{"+
					"name:"+name+
					",category:'"+category+'\''+
					",value:'"+value+'\''+
					",symbolSize:'"+symbolSize+'\''+
					",label:{normal:{show:true}}"+
					'}';
		}
	}
	//用于关系图
	//查询每个省对应的出度
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllOutNum{t_pos_start}", method = RequestMethod.GET)
	@ResponseBody
	public Integer findAllOutNum(String t_pos_start) {
	    return userService.findAllOutNum(t_pos_start);
	}
	//查询每个省所出的患者数findAllConfirmedOutNum
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllConfirmedOutNum{t_pos_start}", method = RequestMethod.GET)
	@ResponseBody
	public Integer findAllConfirmedOutNum(String t_pos_start) {
	    return userService.findAllConfirmedOutNum(t_pos_start);
	}
	//组合成返回数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllRelInPro", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<nodeGraph> findAllRelInPro(){
		ArrayList<nodeGraph>result=new ArrayList<nodeGraph>();
		for(int i=0;i<34;i++) {
			int value=userService.findAllConfirmedOutNum(chinaCity[i]);
			int symbolSize=userService.findAllOutNum(chinaCity[i]);
			nodeGraph temp=new nodeGraph(chinaCity[i],chinaCity[i],value,symbolSize);
			result.add(temp);
		}
		return result;
	}
	//另一项是节点之间的链接
	public class nodeLink{
		private String source;
		private String target;
		nodeLink(String source,String target){
			this.source=source;
			this.target=target;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getTarget() {
			return target;
		}
		public void setTarget(String target) {
			this.target = target;
		}
		@Override
		public String toString() {
			return "{"+
					"source="+source+
					",target="+target+
					'}';
		}
	}
	//组合节点数据
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findAllNodeLink", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<nodeLink>findAllNodeLink(){
		ArrayList<nodeLink>result=new ArrayList<nodeLink>();
		List<TripData>tempData=userService.findAllDealedTripData();
		for(int i=0;i<tempData.size();i++) {
	    	String t_pos_start=tempData.get(i).getT_pos_start();
	    	String t_pos_end=tempData.get(i).getT_pos_end();
	    	nodeLink temp=new nodeLink(t_pos_start,t_pos_end);
	    	result.add(temp);
	    }
		return result;
	}
}
