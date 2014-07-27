package br.edu.ufcg.maonamassa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

class ImageDownload extends AsyncTask<String, Void, Bitmap> {

	ImageView image;
	String id;
	String token;
	private Context context;
	private SessionManager session;

	public ImageDownload(ImageView image, SessionManager session,
			Context context) {
		this.image = image;
		this.context = context;
		this.session = session;
	}

	@Override
	protected Bitmap doInBackground(String... urls) {
		return downloadImage(session.getUserDetails().getPhoto());// "https://lh6.googleusercontent.com/-AlQuyllLocY/AAAAAAAAAAI/AAAAAAAADsw/Q3EK4Y5bvbs/photo.jpg?sz=50"

	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(Bitmap result) {
		if (result != null) {

			image.setImageBitmap(result);
		}

	}

	private Bitmap downloadImage(String _url) {
		// Prepare to download image
		URL url;
		BufferedOutputStream out;
		InputStream in;
		BufferedInputStream buf;

		// BufferedInputStream buf;
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
