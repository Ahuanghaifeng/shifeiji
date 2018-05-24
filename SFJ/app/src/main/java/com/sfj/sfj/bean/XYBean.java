package com.sfj.sfj.bean;

import java.util.List;

public class XYBean {


    private List<PointsBean> points;

    public List<PointsBean> getPoints() {
        return points;
    }

    public void setPoints(List<PointsBean> points) {
        this.points = points;
    }

    public static class PointsBean {
        /**
         * x : 1527145225753
         * y : 2466
         */

        private long x;
        private int y;

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
