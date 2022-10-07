package com.example.lotterypgh.Data;

public class InputLotteryData {

    public int round ;
    public String date ;
    public String resultCount1;
    public long resultMoney1;
    public String resultCount2;
    public long resultMoney2;
    public String resultCount3;
    public long resultMoney3;
    public String resultCount4;
    public long resultMoney4;
    public String resultCount5;
    public long resultMoney5;
    public int result1;
    public int result2;
    public int result3;
    public int result4;
    public int result5;
    public int result6;
    public int resultBonus;





    public InputLotteryData (int round, String date, String resultCount1, long resultMoney1, String resultCount2, long resultMoney2,
                             String resultCount3, long resultMoney3, String resultCount4, long resultMoney4, String resultCount5, long resultMoney5,
                             int result1, int result2, int result3, int result4, int result5, int result6, int resultBonus){
        this.round = round;
        this.date = date;
        this.resultCount1 = resultCount1;
        this.resultMoney1 = resultMoney1;
        this.resultCount2 = resultCount2;
        this.resultMoney2 = resultMoney2;
        this.resultCount3 = resultCount3;
        this.resultMoney3 = resultMoney3;
        this.resultCount4 = resultCount4;
        this.resultMoney4 = resultMoney4;
        this.resultCount5 = resultCount5;
        this.resultMoney5 = resultMoney5;
        this.result1 = result1;
        this.result2 = result2;
        this.result3 = result3;
        this.result4 = result4;
        this.result5 = result5;
        this.result6 = result6;
        this.resultBonus = resultBonus;


    }

    public InputLotteryData(){

    }

    @Override
    public String toString() {
        return "InputLotteryData{" +
                "round=" + round +
                ", date='" + date + '\'' +
                ", resultCount1='" + resultCount1 + '\'' +
                ", resultMoney1=" + resultMoney1 +
                ", resultCount2='" + resultCount2 + '\'' +
                ", resultMoney2=" + resultMoney2 +
                ", resultCount3='" + resultCount3 + '\'' +
                ", resultMoney3=" + resultMoney3 +
                ", resultCount4='" + resultCount4 + '\'' +
                ", resultMoney4=" + resultMoney4 +
                ", resultCount5='" + resultCount5 + '\'' +
                ", resultMoney5=" + resultMoney5 +
                ", result1=" + result1 +
                ", result2=" + result2 +
                ", result3=" + result3 +
                ", result4=" + result4 +
                ", result5=" + result5 +
                ", result6=" + result6 +
                ", resultBonus=" + resultBonus +
                '}';
    }
}
