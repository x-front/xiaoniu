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
     * 1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化
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
     * 获取1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化
     *
     * @return type - 1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化
     *
     * @param type 1.美好家庭，2.美好体育，3美好公益，4.美好教育，5最新动态，6媒体报道，7小牛思想，8小牛声音,9牛人文化
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