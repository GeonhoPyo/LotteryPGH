package com.example.lotterypgh.Data;

import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import jxl.Sheet;
import jxl.Workbook;

public class ReadData {
    //Excel input -> ArrayList
    public ArrayList<InputLotteryData> readExcel(Context context){
        try{
            // 샘플파일 읽어오기
            String sampleFileName = "excel.xls";
            InputStream is = context.getResources().getAssets().open(sampleFileName);
            Workbook wb = Workbook.getWorkbook(is);

            Sheet sheet = wb.getSheet(0);
            int colTotal = sheet.getColumns();
            int rowIndexStart = 1;
            int rowTotal = sheet.getColumn(colTotal-1).length;

            /**
             * b = 회차
             * c = 추첨일
             * d = 1등 당첨자수
             * e = 1등 당첨금액
             * f = 2등 당첨자수
             * g = 2등 당첨금액
             * h = 3등 당첨자수
             * i = 3등 당첨금액
             * j = 4등 당첨자수
             * k = 4등 당첨금액
             * l = 5등 당첨자수
             * m = 5등 당첨금액
             * n = 당첨번호 1
             * o = 당첨번호 2
             * p = 당첨번호 3
             * q = 당첨번호 4
             * r = 당첨번호 5
             * s = 당첨번호 6
             * t = 보너스
             *
             * 4번째 부터
             * */
            ArrayList<InputLotteryData> lotteryDataArrayList = new ArrayList<>();
            for(int row = rowIndexStart ; row < rowTotal ; row ++){
                InputLotteryData inputLotteryData = new InputLotteryData();
                if(row >= 3){
                    for(int col = 1; col < colTotal ; col++){
                        String contents = sheet.getCell(col,row).getContents();
                        switch (col){
                            case 1 :
                                inputLotteryData.round = (int)convertLong(contents);
                                break;
                            case 2 :
                                inputLotteryData.date = (String) contents;
                                break;
                            case 3 :
                                inputLotteryData.resultCount1 = String.valueOf(convertLong(contents));
                                break;
                            case 4 :
                                inputLotteryData.resultMoney1 = convertLong(contents);
                                break;
                            case 5 :
                                inputLotteryData.resultCount2 = String.valueOf(convertLong(contents));
                                break;
                            case 6 :
                                inputLotteryData.resultMoney2 = convertLong(contents);
                                break;
                            case 7 :
                                inputLotteryData.resultCount3 = String.valueOf(convertLong(contents));
                                break;
                            case 8 :
                                inputLotteryData.resultMoney3 = convertLong(contents);
                                break;
                            case 9 :
                                inputLotteryData.resultCount4 = String.valueOf(convertLong(contents));
                                break;
                            case 10 :
                                inputLotteryData.resultMoney4 = convertLong(contents);
                                break;
                            case 11 :
                                inputLotteryData.resultCount5 = String.valueOf(convertLong(contents));
                                break;
                            case 12 :
                                inputLotteryData.resultMoney5 = convertLong(contents);
                                break;
                            case 13 :
                                inputLotteryData.result1 = convertInteger(contents);
                                break;
                            case 14 :
                                inputLotteryData.result2 = convertInteger(contents);
                                break;
                            case 15 :
                                inputLotteryData.result3 = convertInteger(contents);
                                break;
                            case 16 :
                                inputLotteryData.result4 = convertInteger(contents);
                                break;
                            case 17 :
                                inputLotteryData.result5 = convertInteger(contents);
                                break;
                            case 18 :
                                inputLotteryData.result6 = convertInteger(contents);
                                break;
                            case 19 :
                                inputLotteryData.resultBonus = convertInteger(contents);
                                break;
                        }
                    }
                    lotteryDataArrayList.add(inputLotteryData);
                }

            }


            Collections.sort(lotteryDataArrayList, new Comparator<InputLotteryData>() {
                @Override
                public int compare(InputLotteryData inputLotteryData, InputLotteryData t1) {
                    return inputLotteryData.round - t1.round;
                }
            });

            return lotteryDataArrayList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private int convertInteger(String input){
        try{
            if(input != null && !input.equals("")){
                input = input.replaceAll(",","").replaceAll("원","");
                return Integer.parseInt(input);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    private long convertLong(String input){
        try{
            if(input != null && !input.equals("")){
                input = input.replaceAll("[^0-9]","");
                return Long.parseLong(input);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
