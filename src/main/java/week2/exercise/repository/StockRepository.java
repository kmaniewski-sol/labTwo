package week2.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import week2.exercise.domain.Stock;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Serializable> {

    @Query("SELECT MAX(price) FROM Stock s WHERE s.symbol = :symbol AND DATE(s.date) = :date")
    Double findMaxPriceBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT MIN(price) FROM Stock s WHERE s.symbol = :symbol AND DATE(s.date) = :date")
    Double findMinPriceBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT SUM(volume) FROM Stock s WHERE s.symbol = :symbol AND DATE(s.date) = :date")
    int findSumVolumeBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT price FROM Stock s WHERE s.symbol = :symbol AND DATE(s.date) = :date ORDER BY s.date DESC")
    List<Double> findClosingPriceBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);

}

