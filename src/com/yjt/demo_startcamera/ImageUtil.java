package com.yjt.demo_startcamera;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.EditText;

public class ImageUtil {
	
	static public void  InsertImage(Context context,String mFilePath,EditText edt_content,float screenwid){
		
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(mFilePath);
			Bitmap bitmap=BitmapFactory.decodeStream(fis);
			//iv_photo.setImageBitmap(bitmap);让图片显示在imageview上
			Matrix matrix=new Matrix();
//			DisplayMetrics dm = new DisplayMetrics();
//	        getWindowManager().getDefaultDisplay().getMetrics(dm);
//	        float screenwid=dm.widthPixels;
			float scaleWidth = ((float) screenwid) / bitmap.getWidth();
	        // 取得想要缩放的matrix参数
	        
	        matrix.postScale(scaleWidth, scaleWidth);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);
			ImageSpan imagespan = new ImageSpan(context,bitmap);
			String tempUrl = "<img src="+mFilePath+"/>";
			SpannableString spannableString = new SpannableString(tempUrl);
			spannableString.setSpan(imagespan, 0, tempUrl.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			int index = edt_content.getSelectionStart(); // 获取光标所在位置
	        Editable edit_text = edt_content.getEditableText();
	        edit_text.append("\n");
	        if (index < 0 || index >= edit_text.length()) {
	            edit_text.append(spannableString);
	        } else {
	            edit_text.insert(index, spannableString);
	        }
	        edit_text.insert(index + spannableString.length(), "\n");
	        //Log.i("Log", edit_text.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
static public void  DisplayImage(Context context,String mFilePath,EditText edt_content,float screenwid){
		
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(mFilePath);
			Bitmap bitmap=BitmapFactory.decodeStream(fis);
			//iv_photo.setImageBitmap(bitmap);让图片显示在imageview上
			Matrix matrix=new Matrix();
//			DisplayMetrics dm = new DisplayMetrics();
//	        getWindowManager().getDefaultDisplay().getMetrics(dm);
//	        float screenwid=dm.widthPixels;
			float scaleWidth = ((float) screenwid) / bitmap.getWidth();
	        // 取得想要缩放的matrix参数
	        
	        matrix.postScale(scaleWidth, scaleWidth);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);
			ImageSpan imagespan = new ImageSpan(context,bitmap);
			String tempUrl = "<img src="+mFilePath+"/>";
			SpannableString spannableString = new SpannableString(tempUrl);
			spannableString.setSpan(imagespan, 0, tempUrl.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			//int index = edt_content.getSelectionStart(); // 获取光标所在位置
			String content = edt_content.getText().toString();
			
			int index = content.indexOf("<img src="+mFilePath+"/>");
			content=content.replace("<img src="+mFilePath+"/>", "")+" ";
			Log.i("Log!!!!!!!!!!!!!!!!!!!", content);
			edt_content.setText(content);
	        Editable edit_text = edt_content.getEditableText();
//	        edit_text.append("\n");
//	        if (index < 0 || index >= edit_text.length()) {
//	            edit_text.append(spannableString);
//	        } else {
//	            edit_text.insert(index, spannableString);
//	        }
	        
	        edit_text.insert(index, spannableString);
	        
	        //Log.i("Log", edit_text.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
