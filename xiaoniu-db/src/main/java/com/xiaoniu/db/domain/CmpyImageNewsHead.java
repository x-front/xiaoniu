package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_image_news_head")
public class CmpyImageNewsHead extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "show_time")
    private Long showTime;

    @Column(name = "img_url_1")
    private String imgUrl1;

    @Column(name = "img_url_2")
    private String imgUrl2;

    @Column(name = "img_url_3")
    private String imgUrl3;

    private Integer valid;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * @return show_time
     */
    public Long getShowTime() {
        return showTime;
    }

    /**
     * @param showTime
     */
    public void setShowTime(Long showTime) {
        this.showTime = showTime;
    }

    /**
     * @return img_url_1
     */
    public String getImgUrl1() {
        return imgUrl1;
    }

    /**
     * @param imgUrl1
     */
    public void setImgUrl1(String imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    /**
     * @return img_url_2
     */
    public String getImgUrl2() {
        return imgUrl2;
    }

    /**
     * @param imgUrl2
     */
    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    /**
     * @return img_url_3
     */
    public String getImgUrl3() {
        return imgUrl3;
    }

    /**
     * @param imgUrl3
     */
    public void setImgUrl3(String imgUrl3) {
        this.imgUrl3 = imgUrl3;
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