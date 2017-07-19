package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cmpy_page_introduction")
public class CmpyPageIntroduction extends BaseVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 宣传视频
     */
    @Column(name = "ext_media")
    private String extMedia;

    /**
     * 视音频的封面
     */
    @Column(name = "ext_cover")
    private String extCover;

    /**
     * 类型，1我们是谁，2核心竞争力，3高管团队，4经营原则，5资质荣誉，6牛人文化,7小牛普惠，8小牛在线，9小牛新财富，10小牛分期，11小牛投资，12牛鼎丰科技，13小牛思想声音，14小牛思想，15小牛声音，16最新动态，17媒体报道，18美好家庭，19美好体育，20美好公益，21美好教育，22加入小牛,23首页我们是谁，24首页我们做什么，25首页美好家庭，26首页美好体育，27首页美好公益，28首页美好教育，29首页小牛思想声音,30小牛内刊,31社会招聘，32校园招聘
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
     * 获取宣传视频
     *
     * @return ext_media - 宣传视频
     */
    public String getExtMedia() {
        return extMedia;
    }

    /**
     * 设置宣传视频
     *
     * @param extMedia 宣传视频
     */
    public void setExtMedia(String extMedia) {
        this.extMedia = extMedia;
    }

    /**
     * 获取视音频的封面
     *
     * @return ext_cover - 视音频的封面
     */
    public String getExtCover() {
        return extCover;
    }

    /**
     * 设置视音频的封面
     *
     * @param extCover 视音频的封面
     */
    public void setExtCover(String extCover) {
        this.extCover = extCover;
    }

    /**
     * 获取类型，1我们是谁，2核心竞争力，3高管团队，4经营原则，5资质荣誉，6牛人文化,7小牛普惠，8小牛在线，9小牛新财富，10小牛分期，11小牛投资，12牛鼎丰科技，13小牛思想声音，14小牛思想，15小牛声音，16最新动态，17媒体报道，18美好家庭，19美好体育，20美好公益，21美好教育，22加入小牛,23首页我们是谁，24首页我们做什么，25首页美好家庭，26首页美好体育，27首页美好公益，28首页美好教育，29首页小牛思想声音,30小牛内刊,31社会招聘，32校园招聘
     *
     * @return type - 类型，1我们是谁，2核心竞争力，3高管团队，4经营原则，5资质荣誉，6牛人文化,7小牛普惠，8小牛在线，9小牛新财富，10小牛分期，11小牛投资，12牛鼎丰科技，13小牛思想声音，14小牛思想，15小牛声音，16最新动态，17媒体报道，18美好家庭，19美好体育，20美好公益，21美好教育，22加入小牛,23首页我们是谁，24首页我们做什么，25首页美好家庭，26首页美好体育，27首页美好公益，28首页美好教育，29首页小牛思想声音,30小牛内刊,31社会招聘，32校园招聘
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型，1我们是谁，2核心竞争力，3高管团队，4经营原则，5资质荣誉，6牛人文化,7小牛普惠，8小牛在线，9小牛新财富，10小牛分期，11小牛投资，12牛鼎丰科技，13小牛思想声音，14小牛思想，15小牛声音，16最新动态，17媒体报道，18美好家庭，19美好体育，20美好公益，21美好教育，22加入小牛,23首页我们是谁，24首页我们做什么，25首页美好家庭，26首页美好体育，27首页美好公益，28首页美好教育，29首页小牛思想声音,30小牛内刊,31社会招聘，32校园招聘
     *
     * @param type 类型，1我们是谁，2核心竞争力，3高管团队，4经营原则，5资质荣誉，6牛人文化,7小牛普惠，8小牛在线，9小牛新财富，10小牛分期，11小牛投资，12牛鼎丰科技，13小牛思想声音，14小牛思想，15小牛声音，16最新动态，17媒体报道，18美好家庭，19美好体育，20美好公益，21美好教育，22加入小牛,23首页我们是谁，24首页我们做什么，25首页美好家庭，26首页美好体育，27首页美好公益，28首页美好教育，29首页小牛思想声音,30小牛内刊,31社会招聘，32校园招聘
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