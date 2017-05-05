package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_media")
public class CmpyMedia extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ext_media")
    private String extMedia;

    @Column(name = "ext_cover")
    private String extCover;

    /**
     * 1小牛内刊，2小牛报告
     */
    private Integer type;

    @Column(name = "serial_number")
    private Integer serialNumber;

    private Integer valid;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String introdution;

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
     * @return ext_media
     */
    public String getExtMedia() {
        return extMedia;
    }

    /**
     * @param extMedia
     */
    public void setExtMedia(String extMedia) {
        this.extMedia = extMedia;
    }

    /**
     * @return ext_cover
     */
    public String getExtCover() {
        return extCover;
    }

    /**
     * @param extCover
     */
    public void setExtCover(String extCover) {
        this.extCover = extCover;
    }

    /**
     * 获取1小牛内刊，2小牛报告
     *
     * @return type - 1小牛内刊，2小牛报告
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1小牛内刊，2小牛报告
     *
     * @param type 1小牛内刊，2小牛报告
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @return introdution
     */
    public String getIntrodution() {
        return introdution;
    }

    /**
     * @param introdution
     */
    public void setIntrodution(String introdution) {
        this.introdution = introdution;
    }
}