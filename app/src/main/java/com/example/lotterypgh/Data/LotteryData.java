package com.example.lotterypgh.Data;

import java.util.Objects;

public class LotteryData {
    public int number_1 ;
    public int number_2 ;
    public int number_3 ;
    public int number_4 ;
    public int number_5 ;
    public int number_6 ;

    public LotteryData(int number_1, int number_2, int number_3, int number_4, int number_5, int number_6){
        this.number_1 =number_1;
        this.number_2 =number_2;
        this.number_3 =number_3;
        this.number_4 =number_4;
        this.number_5 =number_5;
        this.number_6 =number_6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryData that = (LotteryData) o;
        return number_1 == that.number_1 && number_2 == that.number_2 && number_3 == that.number_3 && number_4 == that.number_4 && number_5 == that.number_5 && number_6 == that.number_6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number_1, number_2, number_3, number_4, number_5, number_6);
    }


    @Override
    public String toString() {
        return "LotteryData{" +
                "number_1=" + number_1 +
                ", number_2=" + number_2 +
                ", number_3=" + number_3 +
                ", number_4=" + number_4 +
                ", number_5=" + number_5 +
                ", number_6=" + number_6 +
                '}';
    }
}
