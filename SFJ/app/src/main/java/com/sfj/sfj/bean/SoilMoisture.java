package com.sfj.sfj.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class SoilMoisture implements Serializable {


	private Map<String,Map<String, TempAndHum>> sites = new HashMap<String,Map<String,TempAndHum>>();
	
	public Map<String, Map<String, TempAndHum>> getSites() {
		return sites;
	}
	public void setSites(Map<String, Map<String, TempAndHum>> sites) {
		this.sites = sites;
	}
	
	//内部类
	public static class TempAndHum{
		private Double temperature;
		private Double humidity;
		private int siteNum;
		private int layerNum;
		
		public Double getTemperature() {
			return temperature;
		}
		public void setTemperature(Double temperature) {
			this.temperature = temperature;
		}
		public Double getHumidity() {
			return humidity;
		}
		public void setHumidity(Double humidity) {
			this.humidity = humidity;
		}
		public int getSiteNum() {
			return siteNum;
		}
		public void setSiteNum(int siteNum) {
			this.siteNum = siteNum;
		}
		public int getLayerNum() {
			return layerNum;
		}
		public void setLayerNum(int layerNum) {
			this.layerNum = layerNum;
		}
	}
	

	public List parseList(Map hash){

		Set en = hash.entrySet();
		List list = new ArrayList<>(en);

		Collections.sort(list, new Comparator<Map.Entry<String,Map<String, TempAndHum>>>() {

			@Override
			public int compare(Map.Entry<String,Map<String, TempAndHum>> o1, Map.Entry<String,Map<String, TempAndHum>> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}

		});
		return list;
	}

}


