import java.util.ArrayList;
import java.util.List;

public class rallyDakkar {
    private double fim; // trilha do rally
    private double maxDistance; // Vocês conseguem viajar no máximo 𝑑 quilômetros por dia antes de anoitecer.
    private double[] pontosParada; // pontos de parada
    private List<Double> pontosUsados; // pontos de parada usados
    int iterations = 0;

    public rallyDakkar(double fim, double maxDistance, double[] paradas) {
        this.fim = fim;
        this.maxDistance = maxDistance;
        int n;

        if (paradas == null) {
            n = 0;
        }
        n = paradas.length;

        pontosParada = new double[n + 2];
        pontosParada[0] = 0.0;
        for (int i = 0; i < n; i++) {
            pontosParada[i + 1] = paradas[i];
        }
        pontosParada[n + 1] = fim;
        pontosUsados = new ArrayList<>();
    }

    public int computeMinStops() {
        pontosUsados.clear();
        int last = pontosParada.length - 1;
        double remaining = maxDistance;

        for (int i = 0; i < last; i++) {
            iterations++;
            double seg = pontosParada[i + 1] - pontosParada[i];
            if (seg > maxDistance) {
                return -1;
            }

            if (seg <= remaining) {
                remaining -= seg;
            } else {
                pontosUsados.add(pontosParada[i]);
                remaining = maxDistance;
                remaining -= seg;
            }
        }

        return pontosUsados.size();
    }

    public double[] getCampStops() {
        double[] res = new double[pontosUsados.size()];
        for (int i = 0; i < pontosUsados.size(); i++) {
            res[i] = pontosUsados.get(i);
        }
        return res;
    }
}
