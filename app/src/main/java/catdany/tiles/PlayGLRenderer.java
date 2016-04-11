package catdany.tiles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Dany on 11.04.2016.
 */
public class PlayGLRenderer implements GLSurfaceView.Renderer {

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1f, 1f, 1f, 1f);
        RenderUtils.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // Vertical lines
        RenderUtils.drawQuad(-0.495f, -1f, -0.505f, 1f, 0, 0);
        RenderUtils.drawQuad(-0.005f, -1f, 0.005f, 1f, 0, 0);
        RenderUtils.drawQuad(0.495f, -1f, 0.505f, 1f, 0, 0);

        // Drawing tiles from the bottom to the
        for (int i = 0; i < 3; i++)
        {
            int pos = PlayActivity.instance.queue.get(i);
            RenderUtils.drawQuad(-1f + pos*0.5f, -0.5f + i*0.5f, -0.5f + pos*0.5f, i*0.5f, 0, 0x333333);
        }
    }
}
