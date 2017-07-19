package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_desc")
public class CmpyDesc extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String banner;

    private String name;

    @Column(name = "sub_name")
    private String subName;

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

    private String summary;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sub_name
     */
    public String getSubName() {
        return subName;
    }

    /**
     * @param subName
     */
    public void setSubName(String subName) {
        this.subName = subName;
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
}