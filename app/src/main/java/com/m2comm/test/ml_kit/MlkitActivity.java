package com.m2comm.test.ml_kit;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.functions.FirebaseFunctions;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import com.m2comm.test.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MlkitActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Integer> mPaperNumberList;
    private ArrayList<Integer> mEmptyNumberList;
    private ArrayList<Integer> mFill45NumberList;

    private FirebaseFunctions mFunctions;

    private Button bt1 , bt2 , bt3;
    private final int TAKE_PICTURE = 999;
    private TextView mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mlkit);

        init();
        //drawable to Bitmap
        initListener();
        initPermission();

    }

    private void initListener() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    private void btHide() {
        bt1.setVisibility(View.GONE);
        bt3.setVisibility(View.GONE);
    }

    private void btShow() {
        bt1.setVisibility(View.VISIBLE);
        bt3.setVisibility(View.VISIBLE);
    }

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d("MLkitActivity", "onRequestPermissionsResult");
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
//                grantResults[1] == PackageManager.PERMISSION_GRANTED ) {
//            Log.d("MLkitActivity", "Permission: " + permissions[0] + "was " + grantResults[0]);
//        }
    }


    private void initPermission() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                Log.d("MLkitActivity", "권한 설정 완료");

            } else {
                Log.d("MLkitActivity", "권한 설정 요청");
                ActivityCompat.requestPermissions(MlkitActivity.this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
        }
    }


    private void init() {
        mPaperNumberList = new ArrayList<>();
        mEmptyNumberList = new ArrayList<>();
        mFill45NumberList = new ArrayList<>();

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);

        //사진을 찍기전에는 섞기 버튼을 숨긴다.
        btHide();

        mText = findViewById(R.id.lottoText);

    }

    public void lottoWork(Bitmap bitmap) {
        for ( int i = 1 , j = 45; i <= j ; i++ ) {
            mEmptyNumberList.add(i);
        }

        //Bitmap bigPictureBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lotto2);
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getCloudTextRecognizer();

        detector.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        // Task completed successfully
                        // ...
                        textSetting(firebaseVisionText.getTextBlocks());
                        settingSuccessful();
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // ...
                                Toast.makeText(getApplicationContext(),
                                        "AI 분석 오류입니다", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    /**
     * 번호 조합하기
     */
    private void emptyNumberCombination () {

        //섞는다.
        for ( int i = 0 , j = mEmptyNumberList.size(); i < j; i++ ) {
            int ranIdx = (int)(Math.random()*(mEmptyNumberList.size()-1));

            int choiceNum = mEmptyNumberList.get(ranIdx);
            int currentNum = mEmptyNumberList.get(i);

            mEmptyNumberList.set(i, choiceNum);
            mEmptyNumberList.set(ranIdx, currentNum);
        }
    }

    private void fill45NumberCombination () {

        //섞는다.
        for ( int i = 0 , j = mFill45NumberList.size(); i < j; i++ ) {
            int ranIdx = (int)(Math.random()*(mFill45NumberList.size()-1));

            int choiceNum = mFill45NumberList.get(ranIdx);
            int currentNum = mFill45NumberList.get(i);

            mFill45NumberList.set(i, choiceNum);
            mFill45NumberList.set(ranIdx, currentNum);
        }
    }

    private void baseChooseNumber() {
        String value = "";
        for (int i = 0 , j = 5; i < j; i++) {
            emptyNumberCombination();
            ArrayList<Integer> resultArray = new ArrayList<>();
            resultArray.add(mEmptyNumberList.get(0));
            resultArray.add(mEmptyNumberList.get(1));
            resultArray.add(mEmptyNumberList.get(2));
            resultArray.add(mEmptyNumberList.get(3));
            resultArray.add(mEmptyNumberList.get(4));
            resultArray.add(mEmptyNumberList.get(5));
            Collections.sort(resultArray);

            value += resultArray.get(0)+"/"
                    +resultArray.get(1)+"/"
                    +resultArray.get(2)+"/"
                    +resultArray.get(3)+"/"
                    +resultArray.get(4)+"/"
                    +resultArray.get(5)+"\n";
        }
        mText.setText(value);

    }

    private void fullNumberChoose() {
        String value = "";
        for (int i = 0 , j = 5; i < j; i++) {
            fill45NumberCombination();
            ArrayList<Integer> resultArray = new ArrayList<>();
            resultArray.add(mFill45NumberList.get(0));
            resultArray.add(mFill45NumberList.get(1));
            resultArray.add(mFill45NumberList.get(2));
            resultArray.add(mFill45NumberList.get(3));
            resultArray.add(mFill45NumberList.get(4));
            resultArray.add(mFill45NumberList.get(5));
            Collections.sort(resultArray);

            value += resultArray.get(0)+"/"
                    +resultArray.get(1)+"/"
                    +resultArray.get(2)+"/"
                    +resultArray.get(3)+"/"
                    +resultArray.get(4)+"/"
                    +resultArray.get(5)+"\n";


        }

        mText.setText(value);
    }


    private void setFill45() {
        mFill45NumberList = (ArrayList<Integer>) mEmptyNumberList.clone();
        while (true) {
            if (mFill45NumberList.size() >= 45 ) break;

            int ranIdx = (int)(Math.random()*(mPaperNumberList.size()-1));
            int idx = mFill45NumberList.indexOf( mPaperNumberList.get(ranIdx));
            if ( idx == -1 ) {
                mFill45NumberList.add(mPaperNumberList.get(ranIdx));
            } else {
                mPaperNumberList.remove(ranIdx);
            }
        }

    }

    private void settingSuccessful() {
        if ( mPaperNumberList.size() > 0 ) {
            Collections.sort(mPaperNumberList);
            for(Integer row : mPaperNumberList) {
                int idx = mEmptyNumberList.indexOf(row);
                if ( idx != -1 ) {
                    mEmptyNumberList.remove(idx);
                }
            }
            //숫자 채우기
            setFill45();
            btShow();
            mText.setText("번호를 추출완료 되었습니다.\n조합버튼을 사용하여 번호를 추출해주세요..");
        } else {
            mText.setText("조합이 안되었습니다... \n증상이 지속될시 개발사에 문의 해주세요.");
        }
    }

    private void textSetting(List<FirebaseVisionText.TextBlock> blocks) {
        for (FirebaseVisionText.TextBlock block : blocks) {

            for (FirebaseVisionText.Line line: block.getLines()) {
                String lineText = line.getText();
                if (    lineText.startsWith("A") ||
                        lineText.startsWith("B")||
                        lineText.startsWith("C")||
                        lineText.startsWith("D")||
                        lineText.startsWith("E")
                        ) {
                    if (lineText.contains("Blotlery")) continue;
                    Log.d("MLkitActivity", "lineText->" + lineText);

                    for (FirebaseVisionText.Element element : line.getElements()) {
                        String elementText = element.getText();
                        if (checkNumber(elementText)) {
                            mPaperNumberList.add(Integer.parseInt(elementText));
                        }
                    }
                }
            }
        }

    }

    public boolean checkNumber(String str){
        char check;

        if(str.equals("")){
            //문자열이 공백인지 확인
            return false;
        }

        for(int i = 0; i<str.length(); i++){
            check = str.charAt(i);
            if( check < 48 || check > 58){
                //해당 char값이 숫자가 아닐 경우
                return false;
            }

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if ( v.getId() == R.id.bt1 ) {
            fullNumberChoose();
        } else if ( v.getId() == R.id.bt2 ) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, TAKE_PICTURE);


        } else if ( v.getId() == R.id.bt3 ) {
            baseChooseNumber();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case TAKE_PICTURE:

                if (resultCode == RESULT_OK && intent.hasExtra("data")) {
                    Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                    if (bitmap != null) {
                        lottoWork(bitmap);
                        mText.setText("번호를 추출중입니다..");
                    }
                }
                break;
        }
    }
}