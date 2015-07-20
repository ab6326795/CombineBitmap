package com.example.xycombinebitmap;

import java.util.ArrayList;
import java.util.List;

import com.pwdgame.combinebitmap.CombineBitmapTools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private LinearLayout mLinearLayout;
	private int combineWidth;
	
	private List<Bitmap> bitmapLists;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinearLayout = (LinearLayout) findViewById(R.id.activity_main_linear);
        
        test();
    }


    public void test(){
    	Bitmap meinv = BitmapFactory.decodeResource(getResources(), R.drawable.meinv);
    	bitmapLists= new ArrayList<Bitmap>();
    	for(int i =0 ;i<9;i++){
    		bitmapLists.add(meinv);	
    	}    	

    	 //��������Ҫ����300DP��С��ͼƬ
        combineWidth = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, 300, getResources().getDisplayMetrics()));
        
    	for(int i= 1 ;i<=9 ; i++){
    		List<Bitmap> tempLists = new ArrayList<Bitmap>(bitmapLists);
    		//���ɸ���
    		for(int j = 0;j< i -1;j++){
    			tempLists.remove(0);
    		}
    		
    		ImageView imageView = ((ImageView)mLinearLayout.getChildAt(i-1));
    		//ֻ��Ҫ��ôһ�仰�Ϳ��������� �� ��д��ô�����Ϊ������ 9 ����һ����ͼ����ʾ
    		imageView.setImageBitmap(CombineBitmapTools.combimeBitmap(this, combineWidth, combineWidth,
    				tempLists));
    	}
    }
}
