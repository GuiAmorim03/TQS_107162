package tqs.ua.pt;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    
    private IStockmarketService stockmarket;
    private List<Stock> stocks = new ArrayList<Stock>();

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public double totalValue(){
        double total = 0;
        for (Stock s : stocks){
            total += stockmarket.lookUpPrice(s.getLabel()) * s.getQuantity();
        }
        return total;
    }
}
