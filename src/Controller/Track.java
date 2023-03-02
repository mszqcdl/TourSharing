package Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yixuan
 * @Version 1.0
 * @Description: 整个轨迹信息
 */
public class Track {
    private List<TrackSegment> segments;

    public Track() {
        this.segments = new ArrayList();
    }

    public Track(List<TrackSegment> segments) {
        this.segments = segments;
    }

    public void addTrackSegment(TrackSegment segment) {
        this.segments.add(segment);
    }

    public void removeTrackSegment(int index) {
        this.segments.remove(index);
    }

    public void clearTrackSegments() {
        this.segments.clear();
    }

    public List<TrackSegment> getSegments() {
        return this.segments;
    }

    public void setSegments(List<TrackSegment> segments) {
        this.segments = segments;
    }
}
