package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ExtractPoints {
    private Double a = 0.0;
    private Double b = 0.0;
    private ArrayList<Double> locations = new ArrayList<Double>();

    public static void main(String[] args){
        ExtractPoints test = new ExtractPoints("");
    }
    public ExtractPoints(String fileName) {
        AtomicInteger i= new AtomicInteger();
        AtomicInteger j= new AtomicInteger();
        String path = "E:\\Project\\Project Specification\\MyFirstGPX.gpx";
        GpxReader gpxFileReader = new GpxReader(path);
        Track track = gpxFileReader.readData();
        List<TrackSegment> segments = track.getSegments();
        AtomicReference<Double> avgLat= new AtomicReference<>(0.0);
        AtomicReference<Double> avgLon= new AtomicReference<>(0.0);
        segments.forEach(x -> {
            List<TrackPoint> points = x.getPoints();
            i.getAndIncrement();
            points.forEach(y -> {
                j.getAndIncrement();
                System.out.println("======================" );
                double longitude = y.getLongitude();
                System.out.println("经度 = " + longitude);
                double latitude = y.getLatitude();
                System.out.println("纬度 = " + latitude);
                Double elevation = y.getElevation();
                System.out.println("海拔 = " + elevation);
                locations.add(latitude);
                avgLat.updateAndGet(v -> v + latitude);
                locations.add(longitude);
                avgLon.updateAndGet(v -> v + longitude);
            });
        });
        int times = j.get();
        a = avgLat.get();
        b = avgLon.get();
        a = a/times;
        b = b/times;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public ArrayList<Double> getLocations() {
        System.out.println(locations);
        return locations;
    }
}
