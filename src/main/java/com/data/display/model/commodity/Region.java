package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Region extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * regionid
	 */
	private Integer region_id;
	/**
	 * 名称
	 */
	private String local_name;
	/**
	 *父级id 
	 */
	private Integer region_path;
	/**
	 * 所有父级
	 */
	private String p_region_id;
	
	private List<Region> regionList;
	
	private List<Map<String,Object>> allData;
	
	
	public List<Map<String, Object>> getAllData() {
		return allData;
	}
	public void setAllData(List<Map<String, Object>> allData) {
		this.allData = allData;
	}
	public List<Region> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRegion_id() {
		return region_id;
	}
	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}
	public String getLocal_name() {
		return local_name;
	}
	public void setLocal_name(String local_name) {
		this.local_name = local_name;
	}
	public Integer getRegion_path() {
		return region_path;
	}
	public void setRegion_path(Integer region_path) {
		this.region_path = region_path;
	}
	public String getP_region_id() {
		return p_region_id;
	}
	public void setP_region_id(String p_region_id) {
		this.p_region_id = p_region_id;
	}
	
}
