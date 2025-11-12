import java.util.ArrayList;
import java.util.List;

/**
 * Implements the "friends'" algorithm described in the assignment:
 * Each time you arrive at a potential stop during the day you check if you can
 * reach the next stop before nightfall given the remaining daylight; if you
 * can, you keep driving, otherwise you camp at the current location.
 */
public class rallyDakkar {
    private double L; // total track length
    private double maxDistance; // max km per day (d)
    private double[] checkpoints; // includes start (0) and end (L)
    private List<Double> camps; // chosen camp positions

    public rallyDakkar(double L, double maxDistance, double[] stops) {
        this.L = L;
        this.maxDistance = maxDistance;
        int n = stops == null ? 0 : stops.length;
        checkpoints = new double[n + 2];
        checkpoints[0] = 0.0;
        for (int i = 0; i < n; i++) {
            checkpoints[i + 1] = stops[i];
        }
        checkpoints[n + 1] = L;
        camps = new ArrayList<>();
    }

    /**
     * Runs the friends' algorithm and returns number of camps (stops) required.
     * Returns -1 if impossible (a gap > maxDistance exists between consecutive
     * checkpoints).
     */
    public int computeMinStops() {
        camps.clear();
        int last = checkpoints.length - 1;
        double remaining = maxDistance; // remaining km before night in current day

        for (int i = 0; i < last; i++) {
            double seg = checkpoints[i + 1] - checkpoints[i];
            if (seg > maxDistance) {
                // There is no valid plan since you can't cover this segment in one day
                return -1;
            }

            if (seg <= remaining) {
                // can reach next during current day
                remaining -= seg;
                // continue to next checkpoint
            } else {
                // cannot reach next before night: camp at current checkpoint
                // (note: if i==0 we camp at start which means we couldn't even
                // reach the first checkpoint in the current day - but that only
                // happens if seg>maxDistance which we already checked)
                camps.add(checkpoints[i]);
                // start a new day from this point
                remaining = maxDistance;
                // after camping, travel the segment to next (must fit in one day)
                remaining -= seg;
            }
        }

        return camps.size();
    }

    public double[] getCampStops() {
        double[] res = new double[camps.size()];
        for (int i = 0; i < camps.size(); i++)
            res[i] = camps.get(i);
        return res;
    }
}
