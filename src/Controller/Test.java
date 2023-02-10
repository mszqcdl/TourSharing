package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) {
        AtomicInteger i= new AtomicInteger();
        AtomicInteger j= new AtomicInteger();
        String path = "E:\\Project\\Project Specification\\MyFirstGPX.gpx";
        GpxReader gpxFileReader = new GpxReader(path);
        Track track = gpxFileReader.readData();
        List<TrackSegment> segments = track.getSegments();
        ArrayList<Double> locations = new ArrayList<Double>();
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
                locations.add(longitude);
            });
        });
        System.out.println(i);
        System.out.println(j);
        System.out.println(locations.toString());
    }

}
