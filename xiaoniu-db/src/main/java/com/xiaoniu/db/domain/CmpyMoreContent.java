package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_more_content")
public class CmpyMoreContent extends BaseVO implements Serializable {
    /**
     * 1-10是经营原则，50-100是核心竞争力
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String banner;

    @Column(name = "banner_desc")
    private String bannerDesc;

    /**
     * 1核心竞争力,2经营原则
     */
    private Integer type;

    private Integer valid;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 语言：0中文，1英文
     */
    private Integer lang;

    /**
     * 终端：0PC端，1手机端
     */
    private Integer terminal;

    private String content;

    private String more;

    private static final long serialVersionUID = 1L;

    /**
     * 获取1-10是经营原则，50-100是核心竞争力
     *
     * @return id - 1-10是经营原则，50-100是核心竞争力
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置1-10是经营原则，50-100是核心竞争力
     *
     * @param id 1-10是经营原则，50-100是核心竞争力
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
     * @return banner_desc
     */
    public String getBannerDesc() {
        return bannerDesc;
    }

    /**
     * @param bannerDesc
     */
    public void setBannerDesc(String bannerDesc) {
        this.bannerDesc = bannerDesc;
    }

    /**
     * 获取1核心竞争力,2经营原则
     *
     * @return type - 1核心竞争力,2经营原则
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1核心竞争力,2经营原则
     *
     * @param type 1核心竞争力,2经营原则
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
     * 获取终端：0PC端，1手机端
     *
     * @return terminal - 终端：0PC端，1手机端
     */
    public Integer getTerminal() {
        return terminal;
    }

    /**
     * 设置终端：0PC端，1手机端
     *
     * @param terminal 终端：0PC端，1手机端
     */
    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
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

    /**
     * @return more
     */
    public String getMore() {
        return more;
    }

    /**
     * @param more
     */
    public void setMore(String more) {
        this.more = more;
    }
}