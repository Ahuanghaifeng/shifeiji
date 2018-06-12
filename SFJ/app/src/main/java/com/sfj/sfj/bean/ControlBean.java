package com.sfj.sfj.bean;

import java.util.List;

public class ControlBean {


    /**
     * isOnline : 1
     * fertilizerName : bb
     * fertilizers : [{"fertilizerId":3,"fertilizerName":"bb","addr":"asd","coordinate":null,"state":null,"isOnline":1,"desc":null,"prodDate":null,"dtuCode":"12345678","zoneId":null,"userId":6},{"fertilizerId":4,"fertilizerName":"cc","addr":"asd","coordinate":null,"state":null,"isOnline":1,"desc":null,"prodDate":null,"dtuCode":"123321","zoneId":null,"userId":6},{"fertilizerId":5,"fertilizerName":"dd","addr":null,"coordinate":null,"state":null,"isOnline":0,"desc":null,"prodDate":null,"dtuCode":"","zoneId":null,"userId":6}]
     * programs : [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * irrigValves : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,0]
     * fertilizerId : 3
     */

    private int isOnline;
    private String fertilizerName;
    private int fertilizerId;
    private List<FertilizersBean> fertilizers;
    private List<Integer> programs;
    private List<Integer> irrigValves;

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public String getFertilizerName() {
        return fertilizerName;
    }

    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
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

    public List<Integer> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Integer> programs) {
        this.programs = programs;
    }

    public List<Integer> getIrrigValves() {
        return irrigValves;
    }

    public void setIrrigValves(List<Integer> irrigValves) {
        this.irrigValves = irrigValves;
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
         * dtuCode : 12345678
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
