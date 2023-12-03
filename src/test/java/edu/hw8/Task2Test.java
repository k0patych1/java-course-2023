package edu.hw8;

import edu.hw8.task2.Fibonacci;
import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    public void simpleFibonacciTest() {
        Fibonacci fibonacci = new Fibonacci(1);
        fibonacci.calculate(1);
        assertThat(fibonacci.getNum()).isEqualTo(1);
        assertThat(fibonacci.getValue()).isEqualTo(1);
    }

    @Test
    public void fibonacciBiNumberTest() {
        Fibonacci fibonacci = new Fibonacci(50);
        fibonacci.calculate(50);
        assertThat(fibonacci.getNum()).isEqualTo(50);
        assertThat(fibonacci.getValue()).isEqualTo(12586269025L);
    }

    @Test
    public void parallelFibonacciesCalculationByMyFixedThreadPoolTest() throws Exception {
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        int[] fibonacciNumbers =  {1, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 100, 110, 120, 130, 140, 150, 200, 300};

        List<Fibonacci> fibonaccies = new ArrayList<>();

        try (ThreadPool threadPool = new FixedThreadPool(numOfThreads)) {
            for (int fibonacciNumber : fibonacciNumbers) {
                Fibonacci fibonacci = new Fibonacci(fibonacciNumber);
                fibonaccies.add(fibonacci);
                Runnable task = () ->
                    fibonacci.calculate(fibonacciNumber);
                threadPool.execute(task);
            }

            threadPool.start();
        }

        for (Fibonacci fibonacci : fibonaccies) {
            int num = (int) fibonacci.getNum();
            Fibonacci expectedFibonacci = new Fibonacci(num);
            assertThat(fibonacci.getValue()).isEqualTo(expectedFibonacci.calculate(num));
        }
    }
}
