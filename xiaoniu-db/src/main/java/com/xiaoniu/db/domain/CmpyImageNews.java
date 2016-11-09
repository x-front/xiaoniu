package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_image_news")
public class CmpyImageNews extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "news_id")
    private Integer newsId;

    private String image;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private Integer valid;

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
     * @return news_id
     */
    public Integer getNewsId() {
        return newsId;
    }

    /**
     * @param newsId
     */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    /**
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
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