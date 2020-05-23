package com.example.admin.signup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRGenerator extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView vType,zone,slot,stime,etime;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databasereference;
    ImageView imageView;
    Button button;
    //EditText editText;
    TextView Slot,Name,VType,Zone,STime,ETime;
    String EditTextValue1 ;
    String EditTextValue2 ;
    String EditTextValue3 ;
    String EditTextValue4 ;
    String EditTextValue5 ;
    String EditTextValue6 ;

    Thread thread ;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        imageView = (ImageView)findViewById(R.id.imageView);
       // editText = (EditText)findViewById(R.id.editText);
        Slot=(TextView)findViewById(R.id.textView49);
        Name=(TextView)findViewById(R.id.textView51);
       /* VType=(TextView)findViewById(R.id.textView52);
        Zone=(TextView)findViewById(R.id.textView53);
        STime=(TextView)findViewById(R.id.textView54);
        ETime=(TextView)findViewById(R.id.textView55);*/
        button = (Button)findViewById(R.id.button);
        try {
            DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    ParkingData parkingData = dataSnapshot.child("vehicleType").getValue(ParkingData.class);
                    ParkingData1 parkingData1 = dataSnapshot.child("zone").getValue(ParkingData1.class);
                    ParkingData3 parkingData3 = dataSnapshot.child("Date & Time Slot").getValue(ParkingData3.class);
                    try {
                        Name.setText("Name: " + userProfile.getUserName() +
                                " Vehicle No: " + userProfile.getUserVehicleNo() +
                                " Slot No: " + parkingData2.getSlot() +
                                " Vehcile Type: " + parkingData.getVehicleType() +
                                " Zone: " + parkingData1.getZone() +
                                " Date: " + parkingData3.getDate() +
                                " Start Time: " + parkingData3.getStime() +
                                " End Time: " + parkingData3.getEtime());
               /*     Slot.setText("Slot No. " + parkingData2.getSlot());
                    VType.setText("Vehicle Type: " + parkingData.getVehicleType());
                    Zone.setText("Zone: " + parkingData1.getZone());
                    STime.setText("Stime: " + parkingData3.getStime());
                    ETime.setText("Etime: " + parkingData3.getEtime());*/
                    } catch (NullPointerException e) {
                        Toast.makeText(QRGenerator.this, "QR code cannot be generated, since there are no previous bookings", Toast.LENGTH_SHORT).show();
                        button.setEnabled(false);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (NullPointerException ignored){

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*try {


                    DatabaseReference reference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ParkingData2 parkingData2 = dataSnapshot.child("slot").getValue(ParkingData2.class);
                            Slot.setText("Slot No. " + parkingData2.getSlot());

                            //editText.setText("Slot No. " + parkingData2.getSlot());

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }catch (NullPointerException ignored){

                }*/
                //EditTextValue1 = Slot.getText().toString();
                EditTextValue2=Name.getText().toString();
//                EditTextValue3=VType.getText().toString();

           //  EditTextValue = editText.getText().toString();

                try {
                    bitmap = TextToImageEncode(EditTextValue2);

                    imageView.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        Color.BLACK:Color.WHITE;
                //ContextCompat.getColor(R.color.QRCodeBlackColor):ContextCompat.getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logoutMenu:{
                firebaseAuth.signOut();
                finish();
                Intent intent=new Intent(QRGenerator.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(QRGenerator.this,ViewProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}