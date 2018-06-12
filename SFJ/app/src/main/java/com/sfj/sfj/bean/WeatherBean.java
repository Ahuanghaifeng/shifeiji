package com.sfj.sfj.bean;

public class WeatherBean {


    /**
     * climatic : {"userId":null,"username":null,"fertilizerId":null,"dtuCode":null,"time":null,"start":null,"end":null,"type":null,"temperature":31.5,"humidity":55,"lighting":23456,"pressure":101.2,"windSpeed":10.3,"windDirection":"东南风","rainFall":2.4,"soilTemperature":34.7,"soilHumidity":70.5}
     */

    private ClimaticBean climatic;

    public ClimaticBean getClimatic() {
        return climatic;
    }

    public void setClimatic(ClimaticBean climatic) {
        this.climatic = climatic;
    }

    public static class ClimaticBean {
        /**
         * userId : null
         * username : null
         * fertilizerId : null
         * dtuCode : null
         * time : null
         * start : null
         * end : null
         * type : null
         * temperature : 31.5
         * humidity : 55
         * lighting : 23456
         * pressure : 101.2
         * windSpeed : 10.3
         * windDirection : 东南风
         * rainFall : 2.4
         * soilTemperature : 34.7
         * soilHumidity : 70.5
         */

        private Object userId;
        private Object username;
        private Object fertilizerId;
        private Object dtuCode;
        private Object time;
        private Object start;
        private Object end;
        private Object type;
        private double temperature;
        private int humidity;
        private int lighting;
        private double pressure;
        private double windSpeed;
        private String windDirection;
        private double rainFall;
        private double soilTemperature;
        private double soilHumidity;

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public Object getFertilizerId() {
            return fertilizerId;
        }

        public void setFertilizerId(Object fertilizerId) {
            this.fertilizerId = fertilizerId;
        }

        public Object getDtuCode() {
            return dtuCode;
        }

        public void setDtuCode(Object dtuCode) {
            this.dtuCode = dtuCode;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
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

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getLighting() {
            return lighting;
        }

        public void setLighting(int lighting) {
            this.lighting = lighting;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getWindDirection() {
            return windDirection;
        }

        public void setWindDirection(String windDirection) {
            this.windDirection = windDirection;
        }

        public double getRainFall() {
            return rainFall;
        }

        public void setRainFall(double rainFall) {
            this.rainFall = rainFall;
        }

        public double getSoilTemperature() {
            return soilTemperature;
        }

        public void setSoilTemperature(double soilTemperature) {
            this.soilTemperature = soilTemperature;
        }

        public double getSoilHumidity() {
            return soilHumidity;
        }

        public void setSoilHumidity(double soilHumidity) {
            this.soilHumidity = soilHumidity;
        }
    }
}
