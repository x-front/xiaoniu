package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_news")
public class CmpyNews extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String source;

    private String banner;

    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化,10小牛思想声音，11小牛新闻，12招聘动态，13社会招聘，14校招QA,15小牛看世界
     */
    private Integer type;

    private Integer valid;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "click_times")
    private Integer clickTimes;

    private String summary;

    private String title;

    @Column(name = "serial_number")
    private Integer serialNumber;

    /**
     * 语言：0中文，1英文
     */
    private Integer lang;

    /**
     * 是否置顶：0否，1是置顶
     */
    @Column(name = "is_top")
    private Integer isTop;

    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return banner
     */
    public String getBanner() {
        return banner;
    }

    /**
     * @param banner
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * @return publish_time
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * @param publishTime
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化,10小牛思想声音，11小牛新闻，12招聘动态，13社会招聘，14校招QA,15小牛看世界
     *
     * @return type - 1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化,10小牛思想声音，11小牛新闻，12招聘动态，13社会招聘，14校招QA,15小牛看世界
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化,10小牛思想声音，11小牛新闻，12招聘动态，13社会招聘，14校招QA,15小牛看世界
     *
     * @param type 1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化,10小牛思想声音，11小牛新闻，12招聘动态，13社会招聘，14校招QA,15小牛看世界
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return valid
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * @param valid
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return click_times
     */
    public Integer getClickTimes() {
        return clickTimes;
    }

    /**
     * @param clickTimes
     */
    public void setClickTimes(Integer clickTimes) {
        this.clickTimes = clickTimes;
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return serial_number
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取语言：0中文，1英文
     *
     * @return lang - 语言：0中文，1英文
     */
    public Integer getLang() {
        return lang;
    }

    /**
     * 设置语言：0中文，1英文
     *
     * @param lang 语言：0中文，1英文
     */
    public void setLang(Integer lang) {
        this.lang = lang;
    }

    /**
     * 获取是否置顶：0否，1是置顶
     *
     * @return is_top - 是否置顶：0否，1是置顶
     */
    public Integer getIsTop() {
        return isTop;
    }

    /**
     * 设置是否置顶：0否，1是置顶
     *
     * @param isTop 是否置顶：0否，1是置顶
     */
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}