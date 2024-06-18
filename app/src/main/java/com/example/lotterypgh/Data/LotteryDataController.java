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




    //랜덤 숫자 제작
    //숫자 한 줄씩(6자리)를 만들어내는데, 조건에 부합하지 않는다면 다시 만듬.
    public void numberController(Context context){

        //int numberOfNum = 0;

        /*Dlog.e("test 1111 : " + equalsHistory(context,new LotteryData(5,11,16,25,26,38)));
        Dlog.e("test 2222 : " + equalsHistory(context,new LotteryData(6,7,10,14,15,38)));
        Dlog.e("test 3333 : " + equalsHistory(context,new LotteryData(2,7,20,26,39,45)));
        Dlog.e("test 4444 : " + equalsHistory(context,new LotteryData(5,9,14,17,18,32)));
        Dlog.e("test 5555 : " + equalsHistory(context,new LotteryData(1,2,14,27,42,44)));*/


        LotteryData lotteryData = makeNumber();
        if(!equalsHistory(context,lotteryData)){
            numberController(context);
        }else{// 예전에 동일 번호 없다면 true
            //패턴 확인
            Dlog.e("result : " +lotteryData.number_1 +" , "+ lotteryData.number_2 +" , "+ lotteryData.number_3+" , "+ lotteryData.number_4+" , "+ lotteryData.number_5+" , "+ lotteryData.number_6 );
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
    /*
     * 1. 단순 랜덤 숫자 출력
     * 2. 연속되거나 나오지 않을 것이라고 가정된 번호 제거
     * 3. 기존 1,2,3등 당첨번호 제거
     * 4. 당첨번호의 합을 정규화하여 3분위로 나눠서. 직전회차에 나온 3분위 제거
     * 5. 기존 4등 당첨번호로 제한 ( 통계상 높은 확률 )
     * 6. 2개 이상 숫자가 10 미만일 수 없음
     * 7. 연속적인 숫자 존재 하지 않음
     * */


    private static ArrayList<InputLotteryData> excelLotteryDataArrayList = null;
    private boolean equalsHistory(Context context, LotteryData lotteryData){
        /**
         * 1. 이전 나온 숫자는 다시 나올 수 없음.
         * */
        if(excelLotteryDataArrayList == null){
            excelLotteryDataArrayList = new ReadData().readExcel(context);
        }
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(lotteryData.number_1);
        integerArrayList.add(lotteryData.number_2);
        integerArrayList.add(lotteryData.number_3);
        integerArrayList.add(lotteryData.number_4);
        integerArrayList.add(lotteryData.number_5);
        integerArrayList.add(lotteryData.number_6);

        boolean grade1 = false;
        boolean grade2 = false;
        boolean grade3 = false;
        boolean grade4 = false;

        for(InputLotteryData inputLotteryData : excelLotteryDataArrayList){
            int cnt = 0 ;
            for(int number : integerArrayList){
                if(number == inputLotteryData.result1){
                    cnt+= 1;
                }else if(number == inputLotteryData.result2){
                    cnt+= 1;
                }else if(number == inputLotteryData.result3){
                    cnt+= 1;
                }else if(number == inputLotteryData.result4){
                    cnt+= 1;
                }else if(number == inputLotteryData.result5){
                    cnt+= 1;
                }else if(number == inputLotteryData.result6){
                    cnt+= 1;
                }
            }
            if(cnt == 6){
                grade1 = true;
            }else if(cnt == 5){ //2,3 등
                for(int number : integerArrayList){
                    if(number == inputLotteryData.resultBonus){
                        cnt+=1;
                    }
                }
                if(cnt == 6){
                    // 2등
                    grade2 = true;
                }else {
                    // 3등
                    grade3 = true;
                }
            }else if(cnt == 4){
                grade4 = true;
            }
        }

        if(grade1||grade2||grade3){
            if(grade1){
                Dlog.e("result 1등");
            }else if(grade2){
                Dlog.e("result 2등");
            }else if(grade3){
                Dlog.e("result 3등");
            }
            return false;
        }else if(grade4){
            return true;
        }


        /*Dlog.e("number_1 : " + lotteryData.number_1 + " , number_2 : " + lotteryData.number_2+ " , number_3 : " + lotteryData.number_3
                + " , number_4 : " + lotteryData.number_4+ " , number_5 : " + lotteryData.number_5+ " , number_6 : " + lotteryData.number_6);*/
        return false;
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

    public void resultTest(Context context){
        if(excelLotteryDataArrayList == null){
            excelLotteryDataArrayList = new ReadData().readExcel(context);
        }
        for(InputLotteryData inputLotteryData : excelLotteryDataArrayList){
            Dlog.e("inputLottery : " + inputLotteryData.result1 + " , " + inputLotteryData.result2 + " , " + inputLotteryData.result3 + " , " + inputLotteryData.result4 + " , " + inputLotteryData.result5 + " , " + inputLotteryData.result6);
            Dlog.e("equls : " + equalsHistory(context,new LotteryData(inputLotteryData.result1,inputLotteryData.result2,inputLotteryData.result3,inputLotteryData.result4,inputLotteryData.result5,inputLotteryData.result6)));
        }
    }
}
