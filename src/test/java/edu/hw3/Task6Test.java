package edu.hw3;

import edu.hw3.task6.MyStockMarket;
import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task6Test {
    @Test
    public void emptyStockMarketGetTest() {
        StockMarket stockMarket = new MyStockMarket();

        assertNull(stockMarket.mostValuableStock());
    }

    @Test
    public void emptyStockMarketRemoveTest() {
        StockMarket stockMarket = new MyStockMarket();

        assertDoesNotThrow(() -> stockMarket.remove(null));
    }

    @Test
    public void StockMarketTest() {
        StockMarket stockMarket = new MyStockMarket();

        Stock stock1 = new Stock("Gazprom", 170L);
        Stock stock2 = new Stock("RusAgro", 1500L);
        Stock stock3 = new Stock("Vipshop", 15L);

        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        assertThat(stockMarket.mostValuableStock()).isEqualTo(stock2);
        assertDoesNotThrow(() -> stockMarket.remove(stock2));

        assertThat(stockMarket.mostValuableStock()).isEqualTo(stock1);
        assertDoesNotThrow(() -> stockMarket.remove(stock1));

        assertThat(stockMarket.mostValuableStock()).isEqualTo(stock3);
        assertDoesNotThrow(() -> stockMarket.remove(stock3));

        assertThat(stockMarket.mostValuableStock()).isEqualTo(null);
    }
}
