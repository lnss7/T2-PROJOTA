public class main {
    public static void main(String[] args) {
        double L = 1000.0;
        double d = 150.0;
        double[] stops = new double[] { 150.0, 300.0, 400.0, 550.0, 700.0, 850.0 };

        rallyDakkar rally = new rallyDakkar(L, d, stops);

        long startTime = System.nanoTime();
        int numStops = rally.computeMinStops();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("Numero de paradas necessrias: " + numStops);
        double[] camps = rally.getCampStops();
        if (camps.length > 0) {
            System.out.print("Posicoes dos acampamentos: ");
            for (int i = 0; i < camps.length; i++) {
                System.out.print(camps[i]);
                if (i < camps.length - 1)
                    System.out.print(", ");
            }
            System.out.println();
            System.out.println("Iteracoes: " + rally.iterations);
            System.out.println("Tempo de execucao: " + (duration / 1_000_000.0) + " ms");
        } else {
            System.out.println("Nenhum acampamento necessário; destino alcançável diretamente.");
        }
    }

}
