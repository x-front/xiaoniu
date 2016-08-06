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
     * 1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力
     */
    private Integer type;

    private Integer valid;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力
     *
     * @return type - 1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力
     *
     * @param type 1小牛普惠，2小牛在线，3小牛投资，4小牛新财富，5小牛分期，6牛鼎丰科技,7核心竞争力
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