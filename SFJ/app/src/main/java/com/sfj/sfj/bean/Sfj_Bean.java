package com.sfj.sfj.bean;

import java.util.List;

public class Sfj_Bean {


    /**
     * fertilizers : [{"fertilizerId":3,"fertilizerName":"bb","addr":"asd","coordinate":null,"state":null,"isOnline":null,"desc":null,"prodDate":null,"dtuCode":"74657374","zoneId":null,"userId":6},{"fertilizerId":4,"fertilizerName":"cc","addr":"asd","coordinate":null,"state":null,"isOnline":null,"desc":null,"prodDate":null,"dtuCode":"","zoneId":null,"userId":6},{"fertilizerId":5,"fertilizerName":"dd","addr":null,"coordinate":null,"state":null,"isOnline":null,"desc":null,"prodDate":null,"dtuCode":null,"zoneId":null,"userId":6}]
     * timeData : {"programNo":0,"valve1":0,"valve2":2,"valve3":0,"totalIrrigation":0,"soilHumidity1":20,"soilHumidity2":0,"soilHumidity3":0,"soilHumidity4":0,"mainFlow":5.8,"liquidLevel":0,"ph":0,"ec":2466,"rateFlow":16.2,"time":1526961355450,"fertilizerId":null,"dtuCode":"74657374"}
     * fertilizerId : 3
     */

    private TimeDataBean timeData;
    private int fertilizerId;
    private int isOnline;
    private String fertilizerName;
    private List<FertilizersBean> fertilizers;

    public TimeDataBean getTimeData() {
        return timeData;
    }

    public void setTimeData(TimeDataBean timeData) {
        this.timeData = timeData;
    }

    public int getFertilizerId() {
        return fertilizerId;
    }

    public void setFertilizerId(int fertilizerId) {
        this.fertilizerId = fertilizerId;
    }

    public List<FertilizersBean> getFertilizers() {
        return fertilizers;
    }

    public void setFertilizers(List<FertilizersBean> fertilizers) {
        this.fertilizers = fertilizers;
    }

    public String getFertilizerName() {
        return fertilizerName;
    }

    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public static class TimeDataBean {
        /**
         * programNo : 0
         * valve1 : 0
         * valve2 : 2
         * valve3 : 0
         * totalIrrigation : 0
         * soilHumidity1 : 20
         * soilHumidity2 : 0
         * soilHumidity3 : 0
         * soilHumidity4 : 0
         * mainFlow : 5.8
         * liquidLevel : 0
         * ph : 0
         * ec : 2466
         * rateFlow : 16.2
         * time : 1526961355450
         * fertilizerId : null
         * dtuCode : 74657374
         * pipePressure :123
         */

        private int programNo;
        private int valve1;
        private int valve2;
        private int valve3;
        private int totalIrrigation;
        private int soilHumidity1;
        private int soilHumidity2;
        private int soilHumidity3;
        private int soilHumidity4;
        private double mainFlow;
        private int liquidLevel;
        private int ph;
        private int ec;
        private double rateFlow;
        private long time;
        private Object fertilizerId;
        private String dtuCode;
        private String pipePressure;

        public String getPipePressure() {
            return pipePressure;
        }

        public void setPipePressure(String pipePressure) {
            this.pipePressure = pipePressure;
        }

        public int getProgramNo() {
            return programNo;
        }

        public void setProgramNo(int programNo) {
            this.programNo = programNo;
        }

        public int getValve1() {
            return valve1;
        }

        public void setValve1(int valve1) {
            this.valve1 = valve1;
        }

        public int getValve2() {
            return valve2;
        }

        public void setValve2(int valve2) {
            this.valve2 = valve2;
        }

        public int getValve3() {
            return valve3;
        }

        public void setValve3(int valve3) {
            this.valve3 = valve3;
        }

        public int getTotalIrrigation() {
            return totalIrrigation;
        }

        public void setTotalIrrigation(int totalIrrigation) {
            this.totalIrrigation = totalIrrigation;
        }

        public int getSoilHumidity1() {
            return soilHumidity1;
        }

        public void setSoilHumidity1(int soilHumidity1) {
            this.soilHumidity1 = soilHumidity1;
        }

        public int getSoilHumidity2() {
            return soilHumidity2;
        }

        public void setSoilHumidity2(int soilHumidity2) {
            this.soilHumidity2 = soilHumidity2;
        }

        public int getSoilHumidity3() {
            return soilHumidity3;
        }

        public void setSoilHumidity3(int soilHumidity3) {
            this.soilHumidity3 = soilHumidity3;
        }

        public int getSoilHumidity4() {
            return soilHumidity4;
        }

        public void setSoilHumidity4(int soilHumidity4) {
            this.soilHumidity4 = soilHumidity4;
        }

        public double getMainFlow() {
            return mainFlow;
        }

        public void setMainFlow(double mainFlow) {
            this.mainFlow = mainFlow;
        }

        public int getLiquidLevel() {
            return liquidLevel;
        }

        public void setLiquidLevel(int liquidLevel) {
            this.liquidLevel = liquidLevel;
        }

        public int getPh() {
            return ph;
        }

        public void setPh(int ph) {
            this.ph = ph;
        }

        public int getEc() {
            return ec;
        }

        public void setEc(int ec) {
            this.ec = ec;
        }

        public double getRateFlow() {
            return rateFlow;
        }

        public void setRateFlow(double rateFlow) {
            this.rateFlow = rateFlow;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Object getFertilizerId() {
            return fertilizerId;
        }

        public void setFertilizerId(Object fertilizerId) {
            this.fertilizerId = fertilizerId;
        }

        public String getDtuCode() {
            return dtuCode;
        }

        public void setDtuCode(String dtuCode) {
            this.dtuCode = dtuCode;
        }
    }

    public static class FertilizersBean {
        /**
         * fertilizerId : 3
         * fertilizerName : bb
         * addr : asd
         * coordinate : null
         * state : null
         * isOnline : null
         * desc : null
         * prodDate : null
         * dtuCode : 74657374
         * zoneId : null
         * userId : 6
         */

        private int fertilizerId;
        private String fertilizerName;
        private String addr;
        private Object coordinate;
        private Object state;
        private int isOnline;
        private Object desc;
        private Object prodDate;
        private String dtuCode;
        private Object zoneId;
        private int userId;

        public int getFertilizerId() {
            return fertilizerId;
        }

        public void setFertilizerId(int fertilizerId) {
            this.fertilizerId = fertilizerId;
        }

        public String getFertilizerName() {
            return fertilizerName;
        }

        public void setFertilizerName(String fertilizerName) {
            this.fertilizerName = fertilizerName;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public Object getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(Object coordinate) {
            this.coordinate = coordinate;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public Object getDesc() {
            return desc;
        }

        public void setDesc(Object desc) {
            this.desc = desc;
        }

        public Object getProdDate() {
            return prodDate;
        }

        public void setProdDate(Object prodDate) {
            this.prodDate = prodDate;
        }

        public String getDtuCode() {
            return dtuCode;
        }

        public void setDtuCode(String dtuCode) {
            this.dtuCode = dtuCode;
        }

        public Object getZoneId() {
            return zoneId;
        }

        public void setZoneId(Object zoneId) {
            this.zoneId = zoneId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
