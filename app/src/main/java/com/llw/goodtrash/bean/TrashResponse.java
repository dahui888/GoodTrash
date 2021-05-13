package com.llw.goodtrash.bean;

import java.util.List;

/**
 * 垃圾分类返回数据
 *
 * @author i小灰
 * @date 2021/3/30 14:51
 */
public class TrashResponse {

    /**
     * code : 200
     * msg : success
     * newslist : [{"name":"羽毛球","type":3,"aipre":0,"explain":"干垃圾即其它垃圾，指除可回收物、有害垃圾、厨余垃圾（湿垃圾）以外的其它生活废弃物。","contain":"常见包括砖瓦陶瓷、渣土、卫生间废纸、猫砂、污损塑料、毛发、硬壳、一次性制品、灰土、瓷器碎片等难以回收的废弃物","tip":"尽量沥干水分；难以辨识类别的生活垃圾都可以投入干垃圾容器内"},{"name":"羽毛球拍","type":0,"aipre":0,"explain":"可回收垃圾是指适宜回收、可循环利用的生活废弃物。","contain":"常见包括各类废金属、玻璃瓶、易拉罐、饮料瓶、塑料玩具、书本、报纸、广告单、纸板箱、衣服、床上用品、电子产品等","tip":"轻投轻放；清洁干燥，避免污染，费纸尽量平整；立体包装物请清空内容物，清洁后压扁投放；有尖锐边角的、应包裹后投放"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * name : 羽毛球
         * type : 3
         * aipre : 0
         * explain : 干垃圾即其它垃圾，指除可回收物、有害垃圾、厨余垃圾（湿垃圾）以外的其它生活废弃物。
         * contain : 常见包括砖瓦陶瓷、渣土、卫生间废纸、猫砂、污损塑料、毛发、硬壳、一次性制品、灰土、瓷器碎片等难以回收的废弃物
         * tip : 尽量沥干水分；难以辨识类别的生活垃圾都可以投入干垃圾容器内
         */

        private String name;
        private int type;
        private int aipre;
        private String explain;
        private String contain;
        private String tip;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAipre() {
            return aipre;
        }

        public void setAipre(int aipre) {
            this.aipre = aipre;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getContain() {
            return contain;
        }

        public void setContain(String contain) {
            this.contain = contain;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }
}
