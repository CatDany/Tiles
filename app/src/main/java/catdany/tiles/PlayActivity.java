package catdany.tiles;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Point;
import android.graphics.Typeface;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

public class PlayActivity extends Activity {

    public static PlayActivity instance;

    private GLSurfaceView glView;
    private final Random random = new Random();
    private Point screenSize;
    public final LinkedList<Integer> queue = new LinkedList<Integer>();
    public TextView textCount;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        initQueue();
        screenSize = DroidUtils.getScreenSize(this);
        super.onCreate(savedInstanceState);

        glView = new PlayGLView(this);
        setContentView(glView);

        textCount = new TextView(this);
        textCount.setText("0");
        textCount.setTextSize(40f);
        textCount.setTextColor(getResources().getColor(android.R.color.black));
        textCount.setTypeface(Typeface.SERIF);
        textCount.setX(15f);
        textCount.setY(screenSize.y - 40f - DroidUtils.getBottomBarHeight(this));
        addContentView(textCount, new WindowManager.LayoutParams());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void initQueue() {
        count = 0;
        queue.clear();
        for (int i = 0; i < 3; i++) {
            queue.addLast(random.nextInt(4));
        }
    }

    public void updateQueue() {
        count++;
        queue.pop();
        queue.addLast(random.nextInt(4));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if ((float)event.getX()/screenSize.x >= queue.getFirst()*0.25f && (float)event.getX()/screenSize.x <= 0.25f + queue.getFirst()*0.25f) {
                updateQueue();
            }
            else
            {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Congratulations.\nYou've scored " + count);
                dlgAlert.setTitle("Tiles");
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                initQueue();
            }
            textCount.setText("" + count);
            glView.requestRender();
            return true;
        }
        return false;
    }
}
