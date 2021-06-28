package com.raj.charbhar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    int tapForFlag0;
    int tapForFlag1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }
    //Player in Game
    //0- O
    //1- X
    int activePlayer = 0;
    int count1=0,count2=0;
    int count=0;
    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};
    //State Meaning
    //0 - X
    //1 - O
    //2 - Null
    int[][] winningPosition={ {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void placeHere(View view) {
        ImageView img= (ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        //if(!gameActive){
            //gameReset(view);
        //}
        if(gameState[tappedImage]==2 && (count1<3 || count2<3)){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                count1++;
                img.setImageResource(R.drawable.ic_cross);
                activePlayer=1;
                TextView status=findViewById(R.id.status);
                status.setText("Turn- Player2");
            }else{
                count2++;
                img.setImageResource(R.drawable.ic_zero);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("Turn- Player1");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }else if(count1>=3 && count2>=3){
            swappingNow(view);
        }
        //check if any player won
        for(int[] winPosition: winningPosition) {
            if(gameState[winPosition[0]]== gameState[winPosition[1]] && gameState[winPosition[1]]== gameState[winPosition[2]]  &&
                    gameState[winPosition[0]]!= 2 ){
                //SomeBody Win the Game
                String winnerStr;
                gameActive=false;
                if(gameState[winPosition[0]]==0){
                    winnerStr="Player1 has won";
                    count1=0;
                    count2=0;
                }else{
                    winnerStr="Player2 has won";
                    count1=0;
                    count2=0;
                }
                //Update the status bar for winner announcement
                TextView status=findViewById(R.id.status);
                status.setText(winnerStr);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder.setTitle(winnerStr);
                alertDialogBuilder.setMessage("Are you want to Reset?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        gameActive=true;
                        activePlayer=0;
                        Arrays.fill(gameState, 2);
                        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
                        TextView status=findViewById(R.id.status);
                        status.setText("Turn- Player1");


                        Toast.makeText(MainActivity.this, " Reset Successfully ", Toast.LENGTH_SHORT).show();

                        //Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        //startActivity(i);
                    }

                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();

                        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();
            }
        }
    }

    private void swappingNow(View view) {
        //Toast.makeText(this, "in swappingNow function", Toast.LENGTH_SHORT).show();
        ImageView img= (ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        //Toast.makeText(this, "count1= "+count1, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "count2= "+count2, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "tappedImage= "+tappedImage, Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "flag= "+flag, Toast.LENGTH_SHORT).show();
        if(gameState[tappedImage]==activePlayer &&count==0){
            //Toast.makeText(this, "gameState[tappedImage]= "+gameState[tappedImage], Toast.LENGTH_SHORT).show();
           // Toast.makeText(this, "activePlayer= "+activePlayer, Toast.LENGTH_SHORT).show();
            tapForFlag0=tappedImage;
           // Toast.makeText(this, "tapForFlag0= "+tapForFlag0, Toast.LENGTH_SHORT).show();

            count++;
            //Toast.makeText(this, "count= "+count, Toast.LENGTH_SHORT).show();
            img.setImageResource(0);

        }if(gameState[tappedImage]==2 &&count==1){
            //Toast.makeText(this, "gameState[tappedImage]= "+gameState[tappedImage], Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "activePlayer= "+activePlayer, Toast.LENGTH_SHORT).show();
            tapForFlag1=tappedImage;
            //Toast.makeText(this, "tapForFlag1= "+tapForFlag1, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "count= "+count, Toast.LENGTH_SHORT).show();
            img.setTranslationY(-1000f);
            if(activePlayer==0 &&count==1){

                img.setTranslationY(-1000f);
                gameState[tapForFlag0]=2;
                gameState[tapForFlag1]=0;
                img.setImageResource(R.drawable.ic_cross);
                count=0;
                activePlayer=1;
                TextView status=findViewById(R.id.status);
                status.setText("Turn- Player2");
            }else if(activePlayer==1 &&count==1){

                img.setTranslationY(-1000f);
                gameState[tapForFlag0]=2;
                gameState[tapForFlag1]=1;
                img.setImageResource(R.drawable.ic_zero);
                count=0;
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("Turn- Player1");
            }
            img.animate().translationYBy(1000f).setDuration(300);


        }else if(gameState[tappedImage]!=activePlayer && gameState[tappedImage]!=2 ){
            Toast.makeText(this, "Tap On Your Icon ", Toast.LENGTH_SHORT).show();
        }

    }



    public void resetGame(View view) {
        gameActive=true;
        activePlayer=0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("Turn- Player1");
        count1=0;
        count2=0;

    }
}
/*
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder.setTitle("Delete This Faculty!");
                alertDialogBuilder.setMessage("Are you sure want to delete?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        DbHelper db = new DbHelper(getApplicationContext());
                        String faculty_Name = classTableArrayList.get(position).getFacultyname();
                        String faculty_Subject = classTableArrayList.get(position).getFacultysubject();
                        db.deleteTeacher(classTableArrayList.get(position).getFacultyname());
                        db.deleteStudentBySubject(faculty_Subject);

                        Toast.makeText(MainActivity.this, " Deleted ", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();

                    }

                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();
 */