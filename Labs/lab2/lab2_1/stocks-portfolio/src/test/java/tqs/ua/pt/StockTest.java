package tqs.ua.pt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockTest {

    @Mock
    IStockmarketService stockMarketService;

    @InjectMocks
    StocksPortfolio stocksPortfolio;


    @Test
    public void getTotalValue() {
        stocksPortfolio.addStock(new Stock("strawberries", 200));
        stocksPortfolio.addStock(new Stock("tuna", 700));
        stocksPortfolio.addStock(new Stock("water", 60));


        when(stockMarketService.lookUpPrice("strawberries")).thenReturn(0.5);
        when(stockMarketService.lookUpPrice("tuna")).thenReturn(1.5);
        when(stockMarketService.lookUpPrice("water")).thenReturn(1.0);


        double expectedResult = 200*0.5 + 700*1.5 + 60*1.0;

        assertEquals(stocksPortfolio.totalValue(), expectedResult);

        assertThat(stocksPortfolio.totalValue(), equalTo(expectedResult));
    }
}
