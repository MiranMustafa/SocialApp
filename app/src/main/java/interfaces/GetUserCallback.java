package interfaces;

import android.graphics.Bitmap;

/**
 * Created by Hi-Tech on 10/30/2017.
 */

public interface GetUserCallback {
     void flagged(boolean flag);
     void imgData(Bitmap image);
}