package catdany.tiles;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Dany on 11.04.2016.
 */
public class PlayGLView extends GLSurfaceView {

    private final PlayGLRenderer render;

    public PlayGLView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        render = new PlayGLRenderer();
        setRenderer(render);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
