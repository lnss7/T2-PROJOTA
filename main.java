public class main {
    public static void main(String[] args) {
        // Example usage of the updated rallyDakkar class.
        double L = 1000.0;
        double d = 200.0;
        // Note: the constructor expects intermediate stops (excluding start 0 and end
        // L)
        double[] stops = new double[] { 150.0, 300.0, 400.0, 550.0, 700.0, 850.0 };

        rallyDakkar rally = new rallyDakkar(L, d, stops);
        int numStops = rally.computeMinStops();

        if (numStops == -1) {
            System.out.println("It is not possible to reach the end of the rally with the given max distance.");
            return;
        }

        System.out.println("Number of stops required: " + numStops);
        double[] camps = rally.getCampStops();
        if (camps.length > 0) {
            System.out.print("Camp positions: ");
            for (int i = 0; i < camps.length; i++) {
                System.out.print(camps[i]);
                if (i < camps.length - 1)
                    System.out.print(", ");
            }
            System.out.println();
        } else {
            System.out.println("No camps required; destination reachable directly.");
        }
    }

}
