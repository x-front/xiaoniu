package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_join_us")
public class CmpyJoinUs extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String position;

    @Column(name = "serial_number")
    private Integer serialNumber;

    private Integer valid;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String address;

    /**
     *  1技术类 2市场类 3投资类 4风控类 5销售类 6职能类
     */
    private Integer type;

    /**
     * 招聘数量
     */
    private String count;

    /**
     * 1热门招聘,0非热门招聘
     */
    private Integer hot;

    private String summary;

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
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
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
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取 1技术类 2市场类 3投资类 4风控类 5销售类 6职能类
     *
     * @return type -  1技术类 2市场类 3投资类 4风控类 5销售类 6职能类
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置 1技术类 2市场类 3投资类 4风控类 5销售类 6职能类
     *
     * @param type  1技术类 2市场类 3投资类 4风控类 5销售类 6职能类
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取招聘数量
     *
     * @return count - 招聘数量
     */
    public String getCount() {
        return count;
    }

    /**
     * 设置招聘数量
     *
     * @param count 招聘数量
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * 获取1热门招聘,0非热门招聘
     *
     * @return hot - 1热门招聘,0非热门招聘
     */
    public Integer getHot() {
        return hot;
    }

    /**
     * 设置1热门招聘,0非热门招聘
     *
     * @param hot 1热门招聘,0非热门招聘
     */
    public void setHot(Integer hot) {
        this.hot = hot;
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