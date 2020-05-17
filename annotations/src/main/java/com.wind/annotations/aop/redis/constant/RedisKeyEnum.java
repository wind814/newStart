package com.wind.annotations.aop.redis.constant;

/**
 * @version V1.0
 * @{DESCRIPTION} : 数据redis key 管理枚举类
 * @auther zhangzq-a
 * @create 2017/11/23
 **/
public enum RedisKeyEnum {


    /**用户相关缓存 redis 索引 8*/

    USER_SESSION(10,"usersession:{0}",60 * 60 * 8,"用户登录信息"),
    USER_LOGIN_PIC_CODE(10,"user_login_piccode:{0}",60 * 5,"用户登录图片验证码"),


    /**
     *  redis 库索引为11
     */
    INDEX_ADVERT_LIST(11,"gcw:index:advert:first:{0}:{1}",60*60*5,"广材网广告位列表，参数：广告位类型，地区"),
    INDEX_CATEGORY_ADVERT(11,"gcw:index:category:advert:{0}:{1}",60*60*5,"广材网类别精品推荐广告，参数：地区，分类"),
    INDEX_CATEGORY_ADVERT_BC(11,"gcw:index:category:advert:{0}:{1}",60*60*5,"广材网类别精品推荐广告，参数：地区，分类"),
    INDEX_CATEGORY_LIST(11,"gcw:index:category:list:{0}",60*60*5,"广材网类别列表,参数：地区"),
    EXTERNAL_LINK_LIST(11,"gcw:index:externallink:list:{0}",60*60*24,"广材网外链列表,参数：外链类型"),
    INDEX_DICTIONARY_LIST(11,"gcw:index:dictionary:list:{0}",60*60*5,"广材网外链列表,参数：类型编码"),
    ALL_CITIES(11,"gcw:index:allcities",0,"所有的市"),
    ALL_PROVINCE_CITIES(11,"gcw:index:all:province:cities",0,"所有的省市"),
    INDEX_SUPPLIER_RECOMMEND_LIST(11,"gcw:index:supplier:recommend:list",60*5,"首页--认证供应商推荐"),
    INDEX_DICTIONARY_TYPE_TO_NAME(11,"gcw:index:category:type:to:name:{0}",60*60*72,"字典code对应的字典名称"),


    COOPERATIVE_COST_STATION_INDEXLIST(12,"cooperativeCostStationIndexList",60*60*24,"合作造价站首页列表"),
    COOPERATIVE_COST_STATION_ALLLIST(12,"cooperativeCostStationAllList:{0}",60*60*24,"合作造价站所有列表,参数：页数"),


    ARTIFICIAL_INQUIRY_LATEST(12,"latestQuotedPrices",60*60*24,"广材网首页人工询价列表"),
    ARTIFICIAL_INQUIRY_COUNT(12,"solveArtificialInquiryCountKey",60*60*24,"广材网首页人工询价条数"),
    INFO_PRICE_LATEST(12,"infoprice_period",60*60*24,"广材网首页信息价列表"),
    MATERIAL_OF_CITY_CATELOG(12,"g_price_trend_latest_id_name_{0}{1}",60*60*24,"广材网首页价格行情材料分类"),
    PRICE_TREND_DATA(12,"subPriceTrendDataKey",60*60*24,"广材网首页价格行情"),

    INFOPRICE_MAJOR(12,"infoprice_major",60*60*24,"信息价专业"),
    INFOPRICE_TOTAL(12,"infoprice_total:{0}",60*60*24,"信息价统计，参数：年月日"),


    /**广财通数据缓存区域*/
    GCT_RPINFO_RECOMMEND(13,"gct:rpinfo:recommend:{0}",60*60*4,"广财通招募采购信息推荐"),
    GCT_RPINFO_STAT_MONTH(13,"gct:rpinfo:stat_month:{0}",60*60*24,"广财通招募采购信息当月统计"),
    GCT_RPINFO_STAT_USER(13,"gct:rpinfo:stat_user:{0}",60*60*1,"广财通招募采购信息当前用户统计"),
    GCT_RPINFO_NEWCOMPANY(13,"gct:rpinfo:newcompany:{0}",60*60*1,"最新加入的采购商和供应商"),

    SUPPLIER_INDEX_PROJECT_CATEGORY_LIST(14,"supplierIndexProjectCategory",60*5,"供应商首页--项目信息--项目类别"),
    SUPPLIER_INDEX_PROJECT_STAGE_LIST(14,"supplierIndexProjectStage",60*5,"供应商首页--项目信息--项目阶段"),
    SUPPLIER_INDEX_PROJECT_FUNCTION_TYPE_LIST(14,"gcw:index:project:functiontype",60*5,"供应商首页--项目信息--项目只能类型"),
    SUPPLIER_INDEX_PRECISION_BUSINESS_OPPORTUNITY_LIST(14,"supplierIndexPrecisionBusinessOpportunity",60*5,"供应商首页--项目信息--精准商机"),
    SUPPLIER_INDEX_LATEST_PROJECT_LIST(14,"supplierIndexLatestProject",60*5,"供应商首页--项目信息--项目阶段--最新项目"),
    SUPPLIER_INDEX_LATEST_PURCHASE_LIST(14,"supplierIndexLatestPurchase",60*5,"供应商首页--项目信息--项目阶段--最新采购"),

    INDEX_PROJECT_DICT_LIST(14,"gcw:index:project:dict:list:{0}",60*5,"供应商首页--项目信息--字典,参数：类型"),
    INDEX_SUGGEST_ACTIVITY_LIST(14,"gcw:index:suggest:activity:list",60*10,"获取供需洽谈会推荐接口"),








    /*****合格供应商 15******/
    INDEX_QUALIFIED_CATEGORY_LIST(15,"gcw:index:category:qualified:list:{0}",0,"合格供应商材料分类列表,参数：日期"),
    INDEX_QUALIFIED_CATEGORY_LIST_GLOBALID(15,"gcw:index:category:qualified:list:{0}:{1}",0,"合格供应商材料分类列表,参数：日期,globalId"),
    QUALIFIED_COMPANY_CATETORY_TOTAL(15,"gcw:index:qualified:company:catetory:total:{0}:{1}",60*60*36,"合格供应商类别数量,参数：日期，前台二级类别id"),
    COMPANY_UPDATE_MONTHLY_TOTAL(15,"gcw:index:company:update:monthly:total:{0}",60*60*800,"每月更新报价的供应商数量,参数：月份"),
    PRICE_UPDATE_MONTHLY_TOTAL(15,"gcw:index:price:update:monthly:total:{0}",60*60*800,"每月更新报价的数量,参数：月份"),
    CATEGORY_UPDATE_MONTHLY_TOTAL(15,"gcw:index:category:update:monthly:total:{0}",60*60*800,"每月更新报价的类别排名前12,参数：月份"),
    CATEGORY_UPDATE_MONTHLY_TOTAL_BACK(15,"gcw:index:category:update:monthly:total:back:{0}",60*60*800,"每月更新报价的类别数量,参数：月份"),
    QUALIFIED_PURCHASER_STORAGE_CATEGORY_TOTAL(15,"gcw:index:qualified:purchaser:storage:category:total",60*60*800,"合格供应商采购加入的供应商类别"),
    QUALIFIED_SUPPLIER_STORAGE_PURCHASER_TOTAL(15,"gcw:index:qualified:supplier:storage:purchaser:total",60*60*800,"供应商入库到采购商库的次数"),
    INDEX_SUGGEST_CONFORMITY_SUPPLIER_LIST(15,"gcw:index:suggest:conformity:supplier:list",60*60,"获取合格供应商推荐接口"),






    WEIXIN_LOGIN_TOKEN(0,"gxqth:{0}",60 * 12,"微信登录校验"),
    WEIXIN_SCAN_CODE_LOGIN_SUCCESS(0,"gcw.scan.code.login.success:{0}",60 * 10,"微信扫码登录成功"),


    /**日志,copy自广材网行为日志记录*/
    LOG_EVENT_TRACKING(2,"selectEventTracking",60*60,"日志"),
    ;





    private int dbIndex;//redis 库索引

    private String key;//redis key

    private String explain;//key 说明

    private int seconds;

    private RedisKeyEnum(int dbIndex, String key, int seconds, String explain){
        this.dbIndex = dbIndex;
        this.key = key;
        this.explain = explain;
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

}
