package br.edu.ufcg.maonamassa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.utils.HttpURLCon;
import br.edu.ufcg.maonamassa.utils.Routes;


class ImageDownload extends AsyncTask<String, Void, Bitmap> {
	
	ImageView image;
	String id;
	String token;
	private Context context;
	private SessionManager session;
	
	public ImageDownload(ImageView image, SessionManager session, Context context) {
		this.image = image;
		this.context = context;
		this.session = session;
	}
	
    @Override
    protected Bitmap doInBackground(String... urls) {
    	return downloadImage(session.getUserDetails().getPhoto());//"https://lh6.googleusercontent.com/-AlQuyllLocY/AAAAAAAAAAI/AAAAAAAADsw/Q3EK4Y5bvbs/photo.jpg?sz=50"
    
    }
    
    
    
    
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(Bitmap result) {
    	if(result!=null){
    		
    		image.setImageBitmap(result);
    	}
    		
       
   }
    
    
	private Bitmap downloadImage(String _url)
    {
        //Prepare to download image
        URL url;        
        BufferedOutputStream out;
        InputStream in;
        BufferedInputStream buf;

        //BufferedInputStream buf;
        try {
            url = new URL(_url);
            in = url.openStream();


            // Read the inputstream 
            buf = new BufferedInputStream(in);

            // Convert the BufferedInputStream to a Bitmap
            Bitmap bMap = BitmapFactory.decodeStream(buf);
            if (in != null) {
                in.close();
            }
            if (buf != null) {
                buf.close();
            }

            return bMap;

        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }

        return null;
    }
	
	
}
