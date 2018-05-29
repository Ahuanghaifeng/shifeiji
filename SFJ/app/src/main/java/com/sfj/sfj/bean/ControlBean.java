package com.sfj.sfj.bean;

import java.util.List;

public class ControlBean {


    /**
     * fertilizers : [{"fertilizerId":3,"fertilizerName":"bb","addr":"asd","coordinate":null,"state":null,"isOnline":1,"desc":null,"prodDate":null,"dtuCode":"74657374","zoneId":null,"userId":6},{"fertilizerId":4,"fertilizerName":"cc","addr":"asd","coordinate":null,"state":null,"isOnline":0,"desc":null,"prodDate":null,"dtuCode":"","zoneId":null,"userId":6},{"fertilizerId":5,"fertilizerName":"dd","addr":null,"coordinate":null,"state":null,"isOnline":0,"desc":null,"prodDate":null,"dtuCode":"","zoneId":null,"userId":6}]
     * controlData : {"userId":null,"fertilizerId":null,"dtuCode":"74657374","time":1527474575489,"start":null,"end":null,"irrigWaterPump":0,"inWaterPump":0,"inWaterValve1":0,"inWaterValve2":0,"inFertValve1":0,"inFertValve2":0,"inFertValve3":0,"inFertValve4":0,"irrigValve1":0,"irrigValve2":0,"irrigValve3":0,"irrigValve4":0,"irrigValve5":0,"irrigValve6":0,"irrigValve7":0,"irrigValve8":0,"irrigValve9":0,"irrigValve10":0,"irrigValve11":0,"irrigValve12":0,"irrigValve13":0,"irrigValve14":0,"irrigValve15":0,"irrigValve16":0}
     * fertilizerId : 3
     */

    private ControlDataBean controlData;
    private int fertilizerId;
    private String fertilizerName;
    private List<FertilizersBean> fertilizers;

    public ControlDataBean getControlData() {
        return controlData;
    }

    public void setControlData(ControlDataBean controlData) {
        this.controlData = controlData;
    }

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

    public List<FertilizersBean> getFertilizers() {
        return fertilizers;
    }

    public void setFertilizers(List<FertilizersBean> fertilizers) {
        this.fertilizers = fertilizers;
    }

    public static class ControlDataBean {
        /**
         * userId : null
         * fertilizerId : null
         * dtuCode : 74657374
         * time : 1527474575489
         * start : null
         * end : null
         * irrigWaterPump : 0
         * inWaterPump : 0
         * inWaterValve1 : 0
         * inWaterValve2 : 0
         * inFertValve1 : 0
         * inFertValve2 : 0
         * inFertValve3 : 0
         * inFertValve4 : 0
         * irrigValve1 : 0
         * irrigValve2 : 0
         * irrigValve3 : 0
         * irrigValve4 : 0
         * irrigValve5 : 0
         * irrigValve6 : 0
         * irrigValve7 : 0
         * irrigValve8 : 0
         * irrigValve9 : 0
         * irrigValve10 : 0
         * irrigValve11 : 0
         * irrigValve12 : 0
         * irrigValve13 : 0
         * irrigValve14 : 0
         * irrigValve15 : 0
         * irrigValve16 : 0
         */

        private Object userId;
        private Object fertilizerId;
        private String dtuCode;
        private long time;
        private Object start;
        private Object end;
        private int irrigWaterPump;
        private int inWaterPump;
        private int inWaterValve1;
        private int inWaterValve2;
        private int inFertValve1;
        private int inFertValve2;
        private int inFertValve3;
        private int inFertValve4;
        private int irrigValve1;
        private int irrigValve2;
        private int irrigValve3;
        private int irrigValve4;
        private int irrigValve5;
        private int irrigValve6;
        private int irrigValve7;
        private int irrigValve8;
        private int irrigValve9;
        private int irrigValve10;
        private int irrigValve11;
        private int irrigValve12;
        private int irrigValve13;
        private int irrigValve14;
        private int irrigValve15;
        private int irrigValve16;

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
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

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Object getStart() {
            return start;
        }

        public void setStart(Object start) {
            this.start = start;
        }

        public Object getEnd() {
            return end;
        }

        public void setEnd(Object end) {
            this.end = end;
        }

        public int getIrrigWaterPump() {
            return irrigWaterPump;
        }

        public void setIrrigWaterPump(int irrigWaterPump) {
            this.irrigWaterPump = irrigWaterPump;
        }

        public int getInWaterPump() {
            return inWaterPump;
        }

        public void setInWaterPump(int inWaterPump) {
            this.inWaterPump = inWaterPump;
        }

        public int getInWaterValve1() {
            return inWaterValve1;
        }

        public void setInWaterValve1(int inWaterValve1) {
            this.inWaterValve1 = inWaterValve1;
        }

        public int getInWaterValve2() {
            return inWaterValve2;
        }

        public void setInWaterValve2(int inWaterValve2) {
            this.inWaterValve2 = inWaterValve2;
        }

        public int getInFertValve1() {
            return inFertValve1;
        }

        public void setInFertValve1(int inFertValve1) {
            this.inFertValve1 = inFertValve1;
        }

        public int getInFertValve2() {
            return inFertValve2;
        }

        public void setInFertValve2(int inFertValve2) {
            this.inFertValve2 = inFertValve2;
        }

        public int getInFertValve3() {
            return inFertValve3;
        }

        public void setInFertValve3(int inFertValve3) {
            this.inFertValve3 = inFertValve3;
        }

        public int getInFertValve4() {
            return inFertValve4;
        }

        public void setInFertValve4(int inFertValve4) {
            this.inFertValve4 = inFertValve4;
        }

        public int getIrrigValve1() {
            return irrigValve1;
        }

        public void setIrrigValve1(int irrigValve1) {
            this.irrigValve1 = irrigValve1;
        }

        public int getIrrigValve2() {
            return irrigValve2;
        }

        public void setIrrigValve2(int irrigValve2) {
            this.irrigValve2 = irrigValve2;
        }

        public int getIrrigValve3() {
            return irrigValve3;
        }

        public void setIrrigValve3(int irrigValve3) {
            this.irrigValve3 = irrigValve3;
        }

        public int getIrrigValve4() {
            return irrigValve4;
        }

        public void setIrrigValve4(int irrigValve4) {
            this.irrigValve4 = irrigValve4;
        }

        public int getIrrigValve5() {
            return irrigValve5;
        }

        public void setIrrigValve5(int irrigValve5) {
            this.irrigValve5 = irrigValve5;
        }

        public int getIrrigValve6() {
            return irrigValve6;
        }

        public void setIrrigValve6(int irrigValve6) {
            this.irrigValve6 = irrigValve6;
        }

        public int getIrrigValve7() {
            return irrigValve7;
        }

        public void setIrrigValve7(int irrigValve7) {
            this.irrigValve7 = irrigValve7;
        }

        public int getIrrigValve8() {
            return irrigValve8;
        }

        public void setIrrigValve8(int irrigValve8) {
            this.irrigValve8 = irrigValve8;
        }

        public int getIrrigValve9() {
            return irrigValve9;
        }

        public void setIrrigValve9(int irrigValve9) {
            this.irrigValve9 = irrigValve9;
        }

        public int getIrrigValve10() {
            return irrigValve10;
        }

        public void setIrrigValve10(int irrigValve10) {
            this.irrigValve10 = irrigValve10;
        }

        public int getIrrigValve11() {
            return irrigValve11;
        }

        public void setIrrigValve11(int irrigValve11) {
            this.irrigValve11 = irrigValve11;
        }

        public int getIrrigValve12() {
            return irrigValve12;
        }

        public void setIrrigValve12(int irrigValve12) {
            this.irrigValve12 = irrigValve12;
        }

        public int getIrrigValve13() {
            return irrigValve13;
        }

        public void setIrrigValve13(int irrigValve13) {
            this.irrigValve13 = irrigValve13;
        }

        public int getIrrigValve14() {
            return irrigValve14;
        }

        public void setIrrigValve14(int irrigValve14) {
            this.irrigValve14 = irrigValve14;
        }

        public int getIrrigValve15() {
            return irrigValve15;
        }

        public void setIrrigValve15(int irrigValve15) {
            this.irrigValve15 = irrigValve15;
        }

        public int getIrrigValve16() {
            return irrigValve16;
        }

        public void setIrrigValve16(int irrigValve16) {
            this.irrigValve16 = irrigValve16;
        }
    }

    public static class FertilizersBean {
        /**
         * fertilizerId : 3
         * fertilizerName : bb
         * addr : asd
         * coordinate : null
         * state : null
         * isOnline : 1
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
