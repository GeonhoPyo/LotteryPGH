package com.example.lotterypgh.Data;

import android.content.Context;

import com.example.lotterypgh.Tool.Dlog;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import jxl.Sheet;
import jxl.Workbook;

public class LotteryDataController {

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
                input = input.replaceAll(",","").replaceAll("원","");
                return Long.parseLong(input);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }



    //랜덤 숫자 제작
    //숫자 한 줄씩(6자리)를 만들어내는데, 조건에 부합하지 않는다면 다시 만듬.
    public void numberController(Context context){

        //int numberOfNum = 0;


        LotteryData lotteryData = makeNumber();
        if(!equalsHistory(context,lotteryData)){

        }else{// 예전에 동일 번호 없다면 true
            //패턴 확인
            int plus = lotteryData.number_1 + lotteryData.number_2+ lotteryData.number_3+ lotteryData.number_4+ lotteryData.number_5+ lotteryData.number_6;
            if(plus >= 138){ //상위 패턴
                Dlog.e("plus : "+plus+", 상위");
            }else { // 하위 패턴
                Dlog.e("plus : "+plus+", 하위");
            }
            if(plus > 238){
                Dlog.e("MAX");
            }else if(plus < 48){
                Dlog.e("MIN");
            }else {
                Dlog.e("MIN < plus < MAX");
            }

        }




        //avgPlusResult();
        //testPattern();

    }

    private LotteryData makeNumber(){

        ArrayList<Integer> resultNumberArray = new ArrayList<>();
        for(int i = 0 ; resultNumberArray.size() < 6 ; i ++){
            int randomNum = (int) (Math.random()*44)+1;
            if(resultNumberArray.isEmpty()){
                resultNumberArray.add(randomNum);
            }else{
                if(!resultNumberArray.contains(randomNum)){
                    resultNumberArray.add(randomNum);
                }
            }

        }

        Collections.sort(resultNumberArray);

        return new LotteryData(resultNumberArray.get(0),resultNumberArray.get(1),resultNumberArray.get(2),
                resultNumberArray.get(3),resultNumberArray.get(4),resultNumberArray.get(5));
    }

    /**
     * 1. 여러개 번호를 생산해 내야함
     * 2. 패턴 추가 필요. ( 총 합산과 평균이 작냐 크냐로 패턴을 제작할 수 있을 듯. )
     *  -> 20000회 결과값 합산에 평균은 135임.
     *  -> 역대 당첨 번호 합산에 평균은 138임
     * */



    private static ArrayList<InputLotteryData> excelLotteryDataArrayList = null;
    private boolean equalsHistory(Context context, LotteryData lotteryData){
        /**
         * 1. 이전 나온 숫자는 다시 나올 수 없음.
         * */
        if(excelLotteryDataArrayList == null){
            excelLotteryDataArrayList = readExcel(context);
        
        }

        for(InputLotteryData inputLotteryData : excelLotteryDataArrayList){
            ArrayList<Integer> integerArrayList = new ArrayList<>();
            integerArrayList.add(lotteryData.number_1);
            integerArrayList.add(lotteryData.number_2);
            integerArrayList.add(lotteryData.number_3);
            integerArrayList.add(lotteryData.number_4);
            integerArrayList.add(lotteryData.number_5);
            integerArrayList.add(lotteryData.number_6);

            int cnt = 0;
            cnt += Collections.frequency(integerArrayList,inputLotteryData.result1);
            cnt += Collections.frequency(integerArrayList,inputLotteryData.result2);
            cnt += Collections.frequency(integerArrayList,inputLotteryData.result3);
            cnt += Collections.frequency(integerArrayList,inputLotteryData.result4);
            cnt += Collections.frequency(integerArrayList,inputLotteryData.result5);
            cnt += Collections.frequency(integerArrayList,inputLotteryData.result6);

            if(cnt == 6){
                return false;
            }else if(cnt == 5){ //2,3 등
                cnt += Collections.frequency(integerArrayList,inputLotteryData.resultBonus);
                if(cnt == 6){
                    // 2등
                    return false;
                }else {
                    // 3등
                    return false;
                }
            }
        }
        Dlog.e("number_1 : " + lotteryData.number_1 + " , number_2 : " + lotteryData.number_2+ " , number_3 : " + lotteryData.number_3
                + " , number_4 : " + lotteryData.number_4+ " , number_5 : " + lotteryData.number_5+ " , number_6 : " + lotteryData.number_6);
        return true;
    }


    /**
     * 아래는 테스트를 위한
     * */
    private void avgPlusResult(){
        Dlog.e("avgPlusResult !! ");
        int result = 0;
        int max = 0;
        int min = 300;
        for(int i = 1 ; i <= 20000 ; i++){
            ArrayList<Integer> resultNumberArray = new ArrayList<>();
            int plus = 0;
            for(int j = 0 ; resultNumberArray.size() < 6 ; j ++){
                int randomNum = (int) (Math.random()*44)+1;
                if(resultNumberArray.isEmpty()){
                    resultNumberArray.add(randomNum);
                    plus+=randomNum;
                }else{
                    if(!resultNumberArray.contains(randomNum)){
                        resultNumberArray.add(randomNum);
                        plus+=randomNum;
                    }
                }
            }

            if(max < plus){
                max = plus;
            }
            if(min > plus){
                min = plus;
            }
            result += plus;

            //int avgResult = (result/i);



        }


        Dlog.e("Test 1111 result : " + result + " , (result/20000) : " + (result/20000) + " , max : " + max + " , min : " + min );
    }
    //(result/20000) : 135 , max : 237 , min : 42
    //(result/20000) : 134 , max : 242 , min : 34

    private void similarTest (){
        Dlog.e("similarTest !! ");
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        int cnt4 = 0;
        int cnt5 = 0;
        for(int i = 0 ; i < excelLotteryDataArrayList.size() ; i ++){
            InputLotteryData inputLotteryData = excelLotteryDataArrayList.get(i);
            for(int j = 0; j < excelLotteryDataArrayList.size() ; j ++){
                if(j != i){
                    InputLotteryData inputLotteryData1 = excelLotteryDataArrayList.get(j);
                    ArrayList<Integer> integerArrayList = new ArrayList<>();
                    integerArrayList.add(inputLotteryData.result1);
                    integerArrayList.add(inputLotteryData.result2);
                    integerArrayList.add(inputLotteryData.result3);
                    integerArrayList.add(inputLotteryData.result4);
                    integerArrayList.add(inputLotteryData.result5);
                    integerArrayList.add(inputLotteryData.result6);

                    int cnt = 0;
                    cnt += Collections.frequency(integerArrayList,inputLotteryData1.result1);
                    cnt += Collections.frequency(integerArrayList,inputLotteryData1.result2);
                    cnt += Collections.frequency(integerArrayList,inputLotteryData1.result3);
                    cnt += Collections.frequency(integerArrayList,inputLotteryData1.result4);
                    cnt += Collections.frequency(integerArrayList,inputLotteryData1.result5);
                    cnt += Collections.frequency(integerArrayList,inputLotteryData1.result6);

                    if(cnt == 6){
                        cnt1 += 1;
                    }else if(cnt == 5){ //2,3 등
                        cnt += Collections.frequency(integerArrayList,inputLotteryData.resultBonus);
                        if(cnt == 6){
                            // 2등
                            cnt2 += 1;
                        }else {
                            // 3등
                            cnt3 += 1;
                        }
                    }else if(cnt == 4){
                        cnt4 += 1;
                    }else if(cnt == 3){
                        cnt5 += 1;
                    }
                }
            }
        }

        Dlog.e("cnt1 : " + cnt1 + " , cnt2 : " + cnt2 + " , cnt3 : " + cnt3+ " , cnt4 : " + cnt4+ " , cnt5 : " + cnt5);

    }

    private void testPattern(){
        Dlog.e("testPattern !! ");
        int high = 0;
        int low = 0;
        int totalPlus = 0;
        int max = 0;
        int min = 300;
        for(InputLotteryData inputLotteryData : excelLotteryDataArrayList){
            int plus = inputLotteryData.result1 + inputLotteryData.result2 + inputLotteryData.result3 + inputLotteryData.result4 + inputLotteryData.result5 + inputLotteryData.result6;
            if(plus >= 138){ //상위 패턴
                Dlog.e("plus : " + plus + ",,상위");
                high +=1;
            }else{ // 하위 패턴
                Dlog.e("plus : " + plus + ",,,,,,하위");
                low +=1;
            }
            if(max < plus){
                max = plus;
            }
            if(min > plus){
                min = plus;
            }
            totalPlus += plus;
        }

        Dlog.e("avg : "+(totalPlus/(high+low))+", high : " + high + ", low : " + low +", max : "+ max + " , min : " + min);
    }
}
