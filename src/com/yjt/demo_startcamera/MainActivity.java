package com.yjt.demo_startcamera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
	private static int REQ_1=1;//拍照返回缩略图
	private static int REQ_2=2;//拍照返回完整图
	private static int REQ_3=3;//调用系统相册
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
	public void getPhotoFromAlbum(View view){
		Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQ_3);
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
				
			
		        ImageUtil.InsertImage(this,this, mFilePath, edt_content);
				
				
				
				
			}
			else if(requestCode==REQ_3){
				//DisplayMetrics dm = new DisplayMetrics();
		        //getWindowManager().getDefaultDisplay().getMetrics(dm);
		        //float screenwid=dm.widthPixels;
				if(data!=null){
					System.out.println(data.getDataString());
					String filePath=ImageUtil.getRealPathFromURI(data.getData(),this);
					//iv_photo.setImageBitmap(getSmallBitmap(filePath, 480, 800));
					ImageUtil.InsertImage(this, this, filePath, edt_content);
					Log.i("Log!!!!!!!!", filePath);
					
					
				}
				else Log.i("Log!!!!!!!!","无数据返回");
				
				//ImageUtil.InsertImage(this,this, filePath, edt_content);
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
