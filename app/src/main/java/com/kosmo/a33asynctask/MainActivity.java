package com.kosmo.a33asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO61";

    ProgressBar mProgress1;
    int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress1 = findViewById(R.id.progressBar1);
    }

    public void onBtnClicked(View v) {
        new CounterTask().execute(0);
    }

    /*
    AsyncTask
        : 해당 클래스는 1.5버전부터 추가된 클래스로 작업쓰레드와 관련된
        복잡한 작업을 쉽게 처리해준다. UI에 대한 비동기 작업을 자동으로
        실행해주며 개발자가 별도로 핸들러 객체를 만들 필요가 없다.
        실행은 execute() 메소드로 한다.

        사용법 : AsyncTask<Param, Progress, Result>
            - Param : 실행 시 작업에 전달되는 값의 타입
            - Progress : 작업의 진행정도를 나타내는 값의 타입
            - Result : 작업의 결과값을 나타내는 타입

            만약 사용 할 필요가 없는 타입이 있다면 Void 라고 표기한다.
            또한 모든 매개변수는 가변인자를 사용하여 여러개의 파라미터를
            처리 할 수 있도록 정의되어 있다.
     */
    class CounterTask extends AsyncTask<Integer, Integer, Integer> {

        // doInBackground() 실행전에 호출되는 메소드
        protected void onPreExecute() { }

        /*
        execute()메소드가 호출되면 자동으로 호출되는 메소드로
        해당 클래스에서 실제 동작을 담당한다.
         */
        protected Integer doInBackground(Integer... value) {
            while (mProgressStatus < 100) {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) { }
                mProgressStatus++;

                //onProgressUpdate
                publishProgress(mProgressStatus);
            }
            return mProgressStatus;
        }

        /*
        doInBackground() 메소드 실행 도중에 언제든지 호출할 수 있는 메소드로
        해당 메소드를 호출할 때는 publishProgress()를 사용한다.
         */
        protected void onProgressUpdate(Integer... value) {
            //프로그레스바의 진행상황(progress)을 설정하는 함수
            mProgress1.setProgress(mProgressStatus);
        }

        /*
        doInBackground() 메소드가 실행된 후 결과값을 해당 메소드로 전송한다.
        즉, 정상종료 되었을 때 호출된다.
         */
        protected void onPostExecute(Integer result) {
            mProgress1.setProgress(mProgressStatus);
        }
    }
}
