package edu.hw9;

import edu.hw9.task1.Metrics;
import edu.hw9.task1.StatsCollector;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    public void EmptyDataStatsCollectorTest() {
        StatsCollector collector = new StatsCollector();

        assertThrows(IllegalArgumentException.class, () ->  collector.push("test", new double[]{}));
    }

    @Test
    public void StatsCollectorTest() {
        StatsCollector collector = new StatsCollector();
        double[] data = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 1; i <= 1000; ++i) {
            collector.push("data" + i, data);
        }

        Metrics expectedMetrics = new Metrics(9.0, 1.0, 45.0, 5);

        for (int i = 1; i <= 1000; i++) {
            assertThat(collector.stats().get("data" + i)).isEqualTo(expectedMetrics);
        }

        collector.clear();
    }
}
