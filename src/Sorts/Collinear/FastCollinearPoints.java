import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> lineSegments = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        final int numOfPoints = points.length;
        Arrays.sort(points);
        for(int i=0; i<numOfPoints; i++) {
            Point p = points[i];
            Arrays.sort(points,i+1, numOfPoints, p.slopeOrder());
            for(int j=i+1, k; j<numOfPoints;) {
                Point q = points[j], r = null;
                double currSlope = p.slopeTo(q);
                for(k=j+1; k<numOfPoints;k++) {
                    r = points[k];
                    double nextSlope = p.slopeTo(r);
                    if (currSlope != nextSlope) {
                        break;
                    }
                }
                if (k-j >= 2) {
                    lineSegments.add(new LineSegment(p, r));
                    i+=k;
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
        return (LineSegment[]) lineSegments.toArray();
    }
}
