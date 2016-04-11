package catdany.tiles;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;

/**
 * Created by Dany on 11.04.2016.
 */
public class DroidUtils {

    public static int getBottomBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static Point getScreenSize(Activity context) {
        Point screenSize = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(screenSize);
        return screenSize;
    }

}
