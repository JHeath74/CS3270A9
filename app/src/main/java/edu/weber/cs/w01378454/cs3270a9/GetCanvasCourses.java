package edu.weber.cs.w01378454.cs3270a9;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import edu.weber.cs.w01378454.cs3270a9.Authorization;
import edu.weber.cs.w01378454.cs3270a9.DB.Course;

public class GetCanvasCourses extends AsyncTask<String, Integer, String>
{

    private String rawJSON;
    private OnCourseListComplete mCallback;

    public interface  OnCourseListComplete
    {
         void processCourseList(Course[] courses);
    }

    public void setOnCourseListComplete(OnCourseListComplete listener)
    {
        mCallback = listener;
    }

    @Override
    protected String doInBackground(String... strings)
    {
        //Request the data,  receive the data,  then process the data.
        try{

            URL url = new URL("https://weber.instructure.com/api/v1/courses/"+strings[0]); // +strings[0]; 0 is where you would place the course number
            HttpURLConnection connection = (HttpsURLConnection)url.openConnection(); //Cast to (https) to force the connection to be secure

            connection.setRequestMethod("GET"); //Get Request.  Asking for Data
            connection.setRequestProperty("Authorization","Bearer " + Authorization.Auth_Token);

            connection.connect();

            int status = connection.getResponseCode();

          switch(status)
          {
              case 200:
              case 201:

                  BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                  rawJSON = br.readLine();

                  Log.d("Test rawJSON","rawJson Length" + rawJSON.length());
                  Log.d("Test rawJSON2","rawJson first 256: " + rawJSON.substring(0, 256));
                  break;

              case 300:
              case 301:
              case 302:
              case 303:
              case 304:
              case 305:
                  Log.d("Get Canvas Courses","New Error Code 300 to 305");
                  break;

              case 400:
              case 401:
              case 402:
              case 403:
              case 404:
              case 405:
                  Log.d("Get Canvas Courses","New Error Code 400 to 405");
                  break;

              case 500:
              case 501:
              case 502:
              case 503:
              case 504:
              case 505:
                  Log.d("Get Canvas Courses","New Error Code 500 to 505");
                  break;


          }


        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            Log.d("Test URL Courses", "Bad URL, unable to connect");
        }
        catch (IOException e)
        {         e.printStackTrace();
                  Log.d("Test I/O Courses","Unable to connect.  Do you have I/O?"); //Network Connectivity Issue
        }


        return rawJSON;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        Course[] courses;

        try
        {
            courses = parseJson(result);
            if(courses != null)
            {
                if(mCallback != null && mCallback instanceof OnCourseListComplete)
                {
                    mCallback.processCourseList(courses);
                    //Send Data - Possibly make it an inner class.  But instead use an interface
                }else{
                    throw new Exception("Must implement OnCourseListComplete interface");
                }

            }
        }
        catch (Exception e)
        {
            Log.d("onPostExecute",e.getMessage());
        }

    }

    private Course[] parseJson(String result)
    {
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();

        Course[] courses = null;

        try
        {
            courses = gson.fromJson(rawJSON,Course[].class);
            Log.d("Courses Test Error","Course Count " + courses.length);
        }
        catch(Exception e)
        {
            Log.d("Course Array Error", e.getMessage());
        }
            return courses;
    }
}
