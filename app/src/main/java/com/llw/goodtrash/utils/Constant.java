package com.llw.goodtrash.utils;

/**
 * 全局常量
 *
 * @author i小灰
 * @date 2021/3/30 15:14
 */
public class Constant {

    /**
     * 垃圾分类的key  请替换为自己的
     */
    public static final String KEY = "783da68c7ea7e10fcd259db651cc550b";

    /**
     * 请求成功码
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 鉴权Token
     */
    public static final String TOKEN = "accessToken";
    /**
     * 获取Token的时间
     */
    public static final String GET_TOKEN_TIME = "getTokenTime";
    /**
     * Token有效期
     */
    public static final String TOKEN_VALID_PERIOD = "tokenValidPeriod";

    /**
     * 百度鉴权认证参数值
     */
    public static final String GRANT_TYPE = "client_credentials";

    /**
     * 百度图像识别 APP ID  GoodTrash
     */
    public static final String APP_ID = "23943795";

    /**
     * 百度图像识别 APP Key  GoodTrash
     */
    public static final String API_KEY = "PAUCX7vSAd4ZBwv897GAfhEQ";

    /**
     * 百度图像识别 APP Secret  GoodTrash
     */
    public static final String API_SECRET = "YGEAfRbxKUtdt0LdFubvyiRiLMbHc7pK";


    /**
     * App首次启动
     */
    public static final String APP_FIRST_START = "appFirstStart";
    /**
     * 今日启动APP的时间
     */
    public static final String START_UP_APP_TIME = "startAppTime";


    /**
     * 默认新闻数据  无网络时加载
     */
    public static final String LOCAL_NEWS_DATA ="{\n" +
            "  \"code\": 200,\n" +
            "  \"msg\": \"success\",\n" +
            "  \"newslist\": [\n" +
            "    {\n" +
            "      \"id\": \"a448c7cc172e1156b35fb266f96c49cf\",\n" +
            "      \"ctime\": \"2021-04-17 08:44\",\n" +
            "      \"title\": \"21项行政执法权将下放基层 垃圾不分类等行为将由街道乡镇直接处罚\",\n" +
            "      \"description\": \"来源：北京日报　　原标题：21项行政执法权将下放基层垃圾不分类等行为将由街道乡镇直接处罚　　今后，餐饮单位向消费者主动提供一次性用品、单位和个人未按规定分类\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd20210417s/233/w676h357/20210417/e0e5-knvsnuf8545017.jpg\",\n" +
            "      \"url\": \"http://news.sina.com.cn/c/2021-04-17/doc-ikmyaawc0158409.shtml\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"854fd9faf85e0ebeb588aca038cf5893\",\n" +
            "      \"ctime\": \"2021-04-17 06:42\",\n" +
            "      \"title\": \"践行分类理念 上虞成立生活垃圾分类“百人宣讲团”\",\n" +
            "      \"description\": \"4月16日下午，一支由100名宣讲师组成，来自各乡镇街道、部门单位、社区（行政村）、大学人员组成的上虞区生活垃圾分类讲师团正式成立。讲师团将通过宣传环保理念、普\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd20123/40/w480h360/20210416/e35f-knvsnuf8043448.jpg\",\n" +
            "      \"url\": \"http://k.sina.com.cn/article_7505202169_1bf584bf902000uczm.html\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"dde5b1a220c6957e8c60ca9f55ec8403\",\n" +
            "      \"ctime\": \"2021-04-17 07:17\",\n" +
            "      \"title\": \"垃圾不分类由街道乡镇直接处罚！北京21项行政执法权将下放基层\",\n" +
            "      \"description\": \"　　原标题：垃圾不分类由街道乡镇直接处罚！北京21项行政执法权将下放基层　　今后，餐饮单位向消费者主动提供一次性用品、单位和个人未按规定分类投放生活垃圾、在\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd2021417s/602/w1280h922/20210417/0d27-knvsnuf8495071.jpg\",\n" +
            "      \"url\": \"http://news.sina.com.cn/c/2021-04-17/doc-ikmxzfmk7288291.shtml\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"3034adf78105d4a3ca53f1d5f14c53ef\",\n" +
            "      \"ctime\": \"2021-04-17 07:36\",\n" +
            "      \"title\": \"老旧小区垃圾分类咋推进？南京玄武区“公交式”流动收集提供新思路\",\n" +
            "      \"description\": \"中国江苏网讯（记者聂龙妃）在南京市玄武区梅园新村街道的兰园19号楼旁的围墙上，有一张垃圾分类流动运输车时间表：每天早中晚3次收运，每个点位停靠5分钟，流运路线\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd20210417s/233/w676h357/20210417/e0e5-knvsnuf8545017.jpg\",\n" +
            "      \"url\": \"http://k.sina.com.cn/article_5675440730_152485a5a0200134ph.html\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"80f5a70563073544de441634f763af70\",\n" +
            "      \"ctime\": \"2021-04-16 23:12\",\n" +
            "      \"title\": \"海口美兰：“小手拉大手”开展垃圾分类宣传\",\n" +
            "      \"description\": \"为引导辖区青少年学生践行垃圾分类，增强环保意识。4月15日，海口美兰区在三江镇开展新时代文明实践垃圾分类主题宣传活动，让垃圾分类以“小手拉大手”的形式走进千家万\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd20123/40/w480h360/20210416/e35f-knvsnuf8043448.jpg\",\n" +
            "      \"url\": \"http://k.sina.com.cn/article_7517400647_1c0126e47059010dn9.html\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"c50d760bc5fe5e09144d02914a87f009\",\n" +
            "      \"ctime\": \"2021-04-16 20:41\",\n" +
            "      \"title\": \"评选十佳社区、招募形象大使 济南将举办系列垃圾分类宣传推广活动\",\n" +
            "      \"description\": \"大众网·海报新闻记者张稳通讯员蒋霞济南报道《济南市生活垃圾减量与分类管理条例》将于5月1日正式实施，记者从济南市城管局获悉，为贯彻济南市人大常委会贯彻\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd2021416s/320/w640h480/20210416/bd3f-knvsnuf7747977.png\",\n" +
            "      \"url\": \"http://k.sina.com.cn/article_2620088113_9c2b5f310200137ds.html\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"a5fb207c5c46a0ac10e5630201264b53\",\n" +
            "      \"ctime\": \"2021-04-16 20:41\",\n" +
            "      \"title\": \"济南垃圾分类新进展：每300户左右设置1处集中分类投放点\",\n" +
            "      \"description\": \"大众网·海报新闻记者张稳济南报道4月16日上午，济南市召开2021年第一季度城市管理综合考评讲评暨城市品质提升百日攻坚行动推进会。会议部署了垃圾分类有关工\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd2021416s/450/w750h500/20210416/d2c4-knvsnuf7747865.jpg\",\n" +
            "      \"url\": \"http://k.sina.com.cn/article_2620088113_9c2b5f310200137dr.html\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"a8291cec82765336a1ca075f23c96fc7\",\n" +
            "      \"ctime\": \"2021-04-16 20:47\",\n" +
            "      \"title\": \"老旧小区空间小垃圾分类亭房难落地，南京玄武这个小区试点“公交化收运”\",\n" +
            "      \"description\": \"扬子晚报网4月16日讯（通讯员诸海路实习生娄渊泽记者张可）南京玄武区梅园街道的兰园片区属于典型的老旧小区，里面有18栋居民楼、500户居民。由于空间有\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd20210416s/300/w720h380/20210416/7b74-knvsnuf7769851.jpg\",\n" +
            "      \"url\": \"http://k.sina.com.cn/article_1653603955_628ffe73020012poo.html\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"d8b40fa53a7f4dedeff9abbbd0e71754\",\n" +
            "      \"ctime\": \"2021-04-16 21:00\",\n" +
            "      \"title\": \"垃圾分类举手之劳 循环利用变废为宝（图）\",\n" +
            "      \"description\": \"　　大江网/大江新闻客户端讯通讯员刘美菊报道：为深入推进生活垃圾分类工作，倡导绿色生态、健康环保的生活方式，增强社区居民对垃圾分类知识的了解，积极参与到垃圾分\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd2021416s/74/w500h374/20210416/35bb-knvsnuf7798675.png\",\n" +
            "      \"url\": \"http://k.sina.com.cn/article_1767961804_6960f4cc02000swdh.html\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"b239d2bfc3265df7985dfd583b19fa3e\",\n" +
            "      \"ctime\": \"2021-04-16 21:01\",\n" +
            "      \"title\": \"南京玄武区开展垃圾流动收运试点  老旧小区垃圾分类问题化难为易\",\n" +
            "      \"description\": \"老旧的院落，狭窄的街巷，这便是玄武区梅园新村街道兰园社区的样貌。作为拥有500多户居民的小区，兰园社区每天都会产生三、四十桶生活垃圾。近两年，垃圾分类作为南京市\",\n" +
            "      \"source\": \"垃圾分类新闻\",\n" +
            "      \"picUrl\": \"http://n.sinaimg.cn/sinakd2021416s/170/w554h416/20210416/7134-knvsnuf7837985.png\",\n" +
            "      \"url\": \"http://k.sina.com.cn/article_5675440730_152485a5a0200134gq.html\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";

}
