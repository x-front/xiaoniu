package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_index_news")
public class CmpyIndexNews extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer valid;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String title;

    @Column(name = "serial_number")
    private Integer serialNumber;

    /**
     * 语言：0中文，1英文
     */
    private Integer lang;

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
}