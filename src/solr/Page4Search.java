package solr;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class Page4Search implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7634496640346412164L;

	/**
	 * 应用id。线上应用指apppageid(app_page中的id)，专区应用指id(thirdparty_app_page中的id)。
	 */
	private int id;
	/**
	 * 应用名字
	 */
	private String name;
	/**
	 * 应用关键字
	 */
	private String keyword;
	/**
	 * 应用所属类型:如果线上应用，则为该AppPage的所有AppPageSupport对应的os连接形成的字符串
	 *             如果专区应用，则为该ThirdpartyAppPage的os
	 * 供应用中心搜索时按os过滤用
	 */
	private String category;
	/**
	 * 应用介绍
	 */
	private String introduction;
	/**
	 * 应用图标url
	 */
	private String iconUrl;
	/**
	 * 应用的packageName
	 */
	private String packageName;
	/**
	 * 操作系统。专区表中的id可定位到唯一应用，线上应用需要os+appPageId才能定位到。
	 */
	private int os ;
	/**
	 * 应用来源。如果是线上应用，=99，如果专区应用，为专区的source_id
	 */
	private int sourceId;
	
	/**
	 * 入库时间，即进入solr的时间
	 */
	private Date updateTime;
	
	

	public int getSourceId() {
		return sourceId;
	}
	@Field
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	@Field
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getName() {
		return name;
	}
	@Field
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}
	@Field
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	@Field
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIntroduction() {
		return introduction;
	}
	@Field
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIconUrl() {
		return iconUrl;
	}
	@Field
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getPackageName() {
		return packageName;
	}
	@Field
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getOs() {
		return os;
	}
	@Field
	public void setOs(int os) {
		this.os = os;
	}
	
	
	public String getKeyword() {
		return keyword;
	}
	@Field
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
