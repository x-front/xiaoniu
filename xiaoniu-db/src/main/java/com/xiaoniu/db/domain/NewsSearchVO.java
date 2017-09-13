/**
 * @author zxx
 * @time 2017年7月18日下午5:47:06
 */
package com.xiaoniu.db.domain;

import java.util.Date;

/**
 * @author zxx
 * @time 2017年7月18日
 *
 */
public class NewsSearchVO {
	private Integer id;
	private String title;
	private String summary;
	private Integer valid;
	private Integer isTop;
	private Date updateTime;
	private Integer type;
	private Integer clickTimes;
	private Integer lang;
	private String banner;

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getClickTimes() {
		return clickTimes;
	}
	public void setClickTimes(Integer clickTimes) {
		this.clickTimes = clickTimes;
	}
	/**
	 * @return the lang
	 */
	public Integer getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(Integer lang) {
		this.lang = lang;
	}
}
