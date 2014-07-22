package br.edu.ufcg.maonamassa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import br.edu.ufcg.maonamassa.models.Recipe;


class ImageDownload extends AsyncTask<String, Void, Bitmap> {
	
	ImageView image;
	
	public ImageDownload(ImageView image) {
		this.image = image;
	}
	
    @Override
    protected Bitmap doInBackground(String... urls) {
    	
        return downloadImage(urls[0]);
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(Bitmap result) {
    	image.setImageBitmap(result);
       
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
