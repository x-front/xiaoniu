package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_content")
public class CmpyContent extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力,8经营原则,9牛人家族，10薪酬福利，11培训发展，12小牛价值观,13校招行程
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
     * 终端：0pc端、1手机端
     */
    private Integer terminal;

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
     * 获取1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力,8经营原则,9牛人家族，10薪酬福利，11培训发展，12小牛价值观,13校招行程
     *
     * @return type - 1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力,8经营原则,9牛人家族，10薪酬福利，11培训发展，12小牛价值观,13校招行程
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力,8经营原则,9牛人家族，10薪酬福利，11培训发展，12小牛价值观,13校招行程
     *
     * @param type 1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力,8经营原则,9牛人家族，10薪酬福利，11培训发展，12小牛价值观,13校招行程
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
     * 获取终端：0pc端、1手机端
     *
     * @return terminal - 终端：0pc端、1手机端
     */
    public Integer getTerminal() {
        return terminal;
    }

    /**
     * 设置终端：0pc端、1手机端
     *
     * @param terminal 终端：0pc端、1手机端
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
}