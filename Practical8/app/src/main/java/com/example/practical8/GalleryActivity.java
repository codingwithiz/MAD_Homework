package com.example.practical8;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Size;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GalleryActivity extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }

        List<Image> ImageList = getPhotos();
        ImageView IVThumb1 = findViewById(R.id.IVThumb1);
        ImageView IVThumb2 = findViewById(R.id.IVThumb2);
        ImageView IVThumb3 = findViewById(R.id.IVThumb3);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            try {
                if (!ImageList.isEmpty()) {
                    Bitmap thumbnail1 = getApplicationContext().getContentResolver().loadThumbnail(ImageList.get(0).uri, new Size(640, 480), null);
                    IVThumb1.setImageBitmap(thumbnail1);
                    Bitmap thumbnail2 = getApplicationContext().getContentResolver().loadThumbnail(ImageList.get(1).uri, new Size(640, 480), null);
                    IVThumb2.setImageBitmap(thumbnail2);
                    Bitmap thumbnail3 = getApplicationContext().getContentResolver().loadThumbnail(ImageList.get(2).uri, new Size(640, 480), null);
                    IVThumb3.setImageBitmap(thumbnail3);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static final Uri IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    class Image {
        private final Uri uri;
        private final String name;
        private final String date_taken;

        public Image(Uri uri, String name, String date_taken) {
            this.uri = uri;
            this.name = name;
            this.date_taken = date_taken;
        }
    }

    protected List<Image> getPhotos() {
        List<Image> ImageList = new ArrayList<>();
        Uri collection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN
        };
        String sortOrder = MediaStore.Images.Media.DATE_TAKEN + " ASC";

        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                collection,
                projection,
                null,
                null,
                sortOrder
        )) {
            if (cursor != null) {
                int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
                int date_takenColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN);
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(idColumn);
                    String name = cursor.getString(nameColumn);
                    String date_taken = cursor.getString(date_takenColumn);
                    Uri contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                    ImageList.add(new Image(contentUri, name, date_taken));
                }
                System.out.println("No. of photos retrieved from shared storage: " + ImageList.size());
            } else {
                System.out.println("Cursor is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ImageList;
    }
}