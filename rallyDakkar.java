public class rallyDakkar {
    private double L;
    private double maxDistance;
    private double[] checkpoints;
    private double[] camps;
    private int numCamps;

    public rallyDakkar(double L, double maxDistance, double[] stops) {
        this.L = L;
        this.maxDistance = maxDistance;
        int n = stops.length;
        checkpoints = new double[n + 2];
        checkpoints[0] = 0.0;
        for (int i = 0; i < n; i++) {
            checkpoints[i + 1] = stops[i];
        }
        checkpoints[n + 1] = L;
        camps = new double[n];
        numCamps = 0;
    }

    // Greedy: always go to the farthest reachable checkpoint
    public int computeMinStops() {
        numCamps = 0;
        int current = 0, last = checkpoints.length - 1;
        while (current < last) {
            int next = current;
            while (next + 1 <= last && checkpoints[next + 1] - checkpoints[current] <= maxDistance)
                next++;
            if (next == current)
                return -1; // can't move forward
            if (next < last)
                camps[numCamps++] = checkpoints[next];
            current = next;
        }
        return numCamps;
    }

    public double[] getCampStops() {
        double[] res = new double[numCamps];
        if (numCamps > 0)
            System.arraycopy(camps, 0, res, 0, numCamps);
        return res;
    }
}
