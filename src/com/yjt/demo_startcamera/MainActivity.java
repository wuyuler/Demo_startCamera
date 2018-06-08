package com.yjt.demo_startcamera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv_photo;
	private EditText edt_content;
	private static int REQ_1=1;
	private static int REQ_2=2;
	private String mFilePath;
	private String displayUrl="/storage/emulated/0/image1.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initShow();
		mFilePath=Environment.getExternalStorageDirectory().getPath();
		mFilePath=mFilePath+"/"+"image1.png";
		
	}
	
	private void initShow(){
		iv_photo=(ImageView)findViewById(R.id.iv_photo);
		edt_content=(EditText)findViewById(R.id.edt_content);
		edt_content.setText("<img src=/storage/emulated/0/image1.png/>");
		
	}
	public void displayImage(View view){
		
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float screenwid=dm.widthPixels;
		ImageUtil.DisplayImage(this, displayUrl, edt_content, screenwid);
		
	}
	public void startCamera(View view){
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, REQ_1);
		
	}
	public void startCamera2(View view){
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri photoUri=Uri.fromFile(new File(mFilePath));
		intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
		startActivityForResult(intent, REQ_2);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK){
			if(requestCode==REQ_1){
				Bundle bundle=data.getExtras();
				Bitmap bitmap=(Bitmap)bundle.get("data");
				iv_photo.setImageBitmap(bitmap);
			}else if(requestCode==REQ_2){
//				FileInputStream fis=null;
//				try {
//					fis=new FileInputStream(mFilePath);
//					Bitmap bitmap=BitmapFactory.decodeStream(fis);
//					//iv_photo.setImageBitmap(bitmap);让图片显示在imageview上
//					Matrix matrix=new Matrix();
//					DisplayMetrics dm = new DisplayMetrics();
//			        getWindowManager().getDefaultDisplay().getMetrics(dm);
//			        float screenwid=dm.widthPixels;
//					float scaleWidth = ((float) screenwid) / bitmap.getWidth();
//			        // 取得想要缩放的matrix参数
//			        
//			        matrix.postScale(scaleWidth, scaleWidth);
//					bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);
//					ImageSpan imagespan = new ImageSpan(this,bitmap);
//					String tempUrl = "<img src="+mFilePath+"/>";
//					SpannableString spannableString = new SpannableString(tempUrl);
//					spannableString.setSpan(imagespan, 0, tempUrl.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//					
//					int index = edt_content.getSelectionStart(); // 获取光标所在位置
//			        Editable edit_text = edt_content.getEditableText();
//			        edit_text.append("\n");
//			        if (index < 0 || index >= edit_text.length()) {
//			            edit_text.append(spannableString);
//			        } else {
//			            edit_text.insert(index, spannableString);
//			        }
//			        edit_text.insert(index + spannableString.length(), "\n");
//			        //Log.i("Log", edit_text.toString());
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}finally {
//					try {
//						fis.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				
				//尝试封装
				
				DisplayMetrics dm = new DisplayMetrics();
		        getWindowManager().getDefaultDisplay().getMetrics(dm);
		        float screenwid=dm.widthPixels;
		        ImageUtil.InsertImage(this, mFilePath, edt_content, screenwid);
				
				
				
				
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
