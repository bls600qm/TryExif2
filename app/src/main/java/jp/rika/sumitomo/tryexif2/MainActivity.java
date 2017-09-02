package jp.rika.sumitomo.tryexif2;

    import java.io.IOException;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.media.ExifInterface;
    import android.os.Bundle;
    import android.os.Environment;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.app.Activity;
    import android.graphics.drawable.Drawable;
    import java.io.File;
    import android.util.Log;
    import java.io.OutputStream;
    import java.io.InputStream;
    import java.io.FileOutputStream;
    import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView sizeText = (TextView)findViewById(R.id.text1);
    TextView dateText = (TextView)findViewById(R.id.text2);
    TextView latlongText = (TextView)findViewById(R.id.text3);
    ImageView thumbnailView = (ImageView)findViewById(R.id.image) ;

        Drawable drawable = getResources().getDrawable(android.R.drawable.sample);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sample);


// 以下のfilenameを自分の画像のパスに変更してください。
        String filename = "/sdcard/DSC_3509.JPG";
        try {
            ExifInterface exif = new ExifInterface(filename);
            ShowExif(exif);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, "Error!",
                    Toast.LENGTH_LONG).show();
        }
}
}

    void createExternalStoragePrivateFile() {
        // Create a path where we will place our private file on external
        // storage.
        File file = new File(getExternalFilesDir(null), "sample.jpg");

        try {
            // Very simple code to copy a picture from the application's
            // resource into the external file.  Note that this code does
            // no error checking, and assumes the picture is small (does not
            // try to copy it in chunks).  Note that if external storage is
            // not currently mounted this will silently fail.
            InputStream is = getResources().openRawResource(R.drawable.ballon);
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

    void deleteExternalStoragePrivateFile() {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        File file = new File(getExternalFilesDir(null), "sample.jpg");
        if (file != null) {
            file.delete();
        }
    }

    boolean hasExternalStoragePrivateFile() {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        File file = new File(getExternalFilesDir(null), "sample.jpg");
        if (file != null) {
            return file.exists();
        }
        return false;
    }