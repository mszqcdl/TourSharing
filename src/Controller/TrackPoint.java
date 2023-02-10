package Controller;

/**
 * @Author: Yixuan
 * @Version 1.0
 * @Description: 每一个点的信息属性【其对应接收GPX中标签属性值】
 */
public class TrackPoint {

    private double latitude;
    private double longitude;
    private Double elevation;
//    private Date time;

    public TrackPoint() {
    }

    public TrackPoint(double latitude, double longitude, Double elevation) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
//        this.time = time;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getElevation() {
        return this.elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

//    public Date getTime() {
//        return this.time;
//    }

//    public void setTime(Date time) {
//        this.time = time;
//    }

    public double distanceTo(TrackPoint point) {
        boolean r = true;
        double latDistance = Math.toRadians(point.latitude - this.latitude);
        double lonDistance = Math.toRadians(point.longitude - this.longitude);
        double a = Math.sin(latDistance / 2.0D) * Math.sin(latDistance / 2.0D) + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(point.latitude)) * Math.sin(lonDistance / 2.0D) * Math.sin(lonDistance / 2.0D);
        double c = 2.0D * Math.atan2(Math.sqrt(a), Math.sqrt(1.0D - a));
        double distance = 6371.0D * c * 1000.0D;
        if (point.elevation != null && this.elevation != null) {
            double height = point.elevation - this.elevation;
            distance = Math.pow(distance, 2.0D) + Math.pow(height, 2.0D);
        }

        return Math.sqrt(distance);
    }
}
