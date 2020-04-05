import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> lineSegments = new ArrayList<>();
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        final int numOfPoints = points.length;
        Arrays.sort(points);
        for(int i=0; i<numOfPoints; i++) {
            Point p = points[i];
            for (int j=i+1; j<numOfPoints; j++) {
                Point q = points[j];
                double slope1 = p.slopeTo(q);
                for (int k=j+1; k<numOfPoints; k++) {
                    Point r = points[k];
                    double slope2 = q.slopeTo(r);
                    if (slope1 != slope2) {
                        continue;
                    }
                    for (int m=k+1; m<numOfPoints; m++) {
                        Point s = points[m];
                        double slope3 = r.slopeTo(s);
                        if (slope1 == slope2 && slope2 == slope3) {
                            lineSegments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] ls = new LineSegment[lineSegments.size()];
        ls=lineSegments.toArray(ls);
        return ls;
    }
}
