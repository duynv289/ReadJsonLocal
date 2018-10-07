package com.example.liz.readjsonlocal;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_STUDENT = "students";
    public static final String KEY_NAME = "name";
    public static final String KEY_AGE = "age";
    public static final String KEY_CITY = "city";

    private RecyclerView mRecyclerView;
    private StudentAdapter mStudentAdapter;
    private List<Student> mStudents;

    private static String parseJson(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mStudents = readJson(R.raw.data);
        mStudentAdapter = new StudentAdapter(mStudents);
        mRecyclerView.setAdapter(mStudentAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private List<Student> readJson(int resId) {
        List<Student> mList = new ArrayList<>();
        try {
            String jsonText = parseJson(this, resId);
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray studentArray = jsonObject.optJSONArray(KEY_STUDENT);
            for (int i = 0; i < studentArray.length(); i++) {
                JSONObject studentObject = studentArray.optJSONObject(i);
                String name = studentObject.optString(KEY_NAME);
                int age = studentObject.optInt(KEY_AGE);
                String city = studentObject.optString(KEY_CITY);
                Student student = new Student(name, age, city);
                mList.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mList;
    }
}
