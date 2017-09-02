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

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView sizeText = (TextView)findViewById(R.id.text1);
    TextView dateText = (TextView)findViewById(R.id.text2);
    TextView latlongText = (TextView)findViewById(R.id.text3);
    ImageView thumbnailView = (ImageView)findViewById(R.id.image);
        try {
        // ExifInterfaceのインスタンス取得
            ExifInterface exif = new ExifInterface(Environment.getExternalStorageDirectory().getPath() + "/sample/sample.jpg");
                if (exif != null) {
            // 画像情報
                String info = String.format("size: %d x %d",
                    exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, -1),
                    exif.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, -1));
                    sizeText.setText(info);
                    info = String.format("date: %s", exif.getAttribute(ExifInterface.TAG_DATETIME));
                    dateText.setText(info);
                    float[] latlong = new float[2];
                    exif.getLatLong(latlong);
                    info = String.format("latlong: %f, %f", latlong[0], latlong[1]);
            }
        } catch (IOException e) {
              e.printStackTrace();
        }
    }
}

