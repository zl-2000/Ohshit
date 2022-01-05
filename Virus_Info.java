package cn.itcast.crawer;
import com.google.gson.JsonObject;
//{"id":12696598,"createTime":1640690776000,"modifyTime":1640690776000,"tags":"","countryType":2,"continents":"欧洲","provinceId":"10","provinceName":"波黑","provinceShortName":"","cityName":"","currentConfirmedCount":82295,"confirmedCount":287808,"confirmedCountRank":85,"suspectedCount":0,"curedCount":192218,"deadCount":13295,"deadCountRank":53,"deadRate":"4.61","deadRateRank":11,"comment":"","sort":0,"operator":"zhongziyu","locationId":965003,"countryShortCode":"BIH","countryFullName":"Bosnia and Herzegovina","statisticsData":"https://file1.dxycdn.com/2020/0315/908/3402160538577857266-135.json","incrVo":{"currentConfirmedIncr":506,"confirmedIncr":532,"curedIncr":0,"deadIncr":26},"showRank":true,"yesterdayConfirmedCount":2147383647,"yesterdayLocalConfirmedCount":2147383647,"yesterdayOtherConfirmedCount":2147383647,"highDanger":"","midDanger":"","highInDesc":"","lowInDesc":"","outDesc":""},
public class Virus_Info {
    private String continents;
    private String contientsId;
    private String countryName;
    private String countryId;
    private String countryFullName;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    @Override
    public String toString(){
        JsonObject json=new JsonObject();
        json.addProperty("continents",continents);
        json.addProperty("contientsId",contientsId);
        json.addProperty("countryName",countryName);
        json.addProperty("countryId",countryId);
        json.addProperty("countryFullName",countryFullName);
        json.addProperty("currentConfirmedCount",currentConfirmedCount);
        json.addProperty("confirmedCount",confirmedCount);
        json.addProperty("suspectedCount",suspectedCount);
        json.addProperty("curedCount",curedCount);
        json.addProperty("deadCount",deadCount);
        return json.toString();
    }

    public String getContinents() {
        return continents;
    }

    public void setContinents(String continents) {
        this.continents = continents;
    }

    public String getContientsId() {
        return contientsId;
    }

    public void setContientsId(String contientsId) {
        this.contientsId = contientsId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryFullName() {
        return countryFullName;
    }

    public void setCountryFullName(String countryFullName) {
        this.countryFullName = countryFullName;
    }

    public int getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public int getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(int suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public int getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(int curedCount) {
        this.curedCount = curedCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }
}
