package catdany.tiles;

import android.graphics.Color;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Dany on 11.04.2016.
 */
public class RenderUtils {

    private static int program;

    private static final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private static final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    public static void init() {
        program = GLES20.glCreateProgram();
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader  = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
    }

    public static void drawQuad(float x, float y, float z, float x1, float y1, float z1, float colorRed, float colorGreen, float colorBlue, float colorRed1, float colorGreen1, float colorBlue1) {
        FloatBuffer vertexBuffer0 = floatArrayToBuffer(
                x, y, z,
                x, y1, z,
                x1, y1, z1
        );
        FloatBuffer vertexBuffer1 = floatArrayToBuffer(
                x, y, z,
                x1, y1, z1,
                x1, y, z1
        );

        GLES20.glUseProgram(program);

        int positionHandle = GLES20.glGetAttribLocation(program, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        int colorHandle = GLES20.glGetUniformLocation(program, "vColor");
        GLES20.glUniform4fv(colorHandle, 1, new float[] {colorRed, colorGreen, colorBlue, 1}, 0);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 12, vertexBuffer0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
        GLES20.glUniform4fv(colorHandle, 1, new float[] {colorRed1, colorGreen1, colorBlue1, 1}, 0);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 12, vertexBuffer1);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    /**
     * Draw a 2D quad
     * @param x X coordinate of the bottom-left vertex
     * @param y Y coordinate of the bottom-left vertex
     * @param x1 X coordinate of the top-right vertex
     * @param y1 Y coordinate of the top-right vertex
     * @param color0 color of the first triangle
     * @param color1 color of the second triangle
     */
    public static void drawQuad(float x, float y, float x1, float y1, int color0, int color1) {
        drawQuad(x, y, 0, x1, y1, 0, Color.red(color0)/255f, Color.green(color0)/255f, Color.blue(color0)/255f, Color.red(color1)/255f, Color.green(color1)/255f, Color.blue(color1)/255f);
    }

    public static FloatBuffer floatArrayToBuffer(float... array) {
        FloatBuffer buffer;
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length * 4);
        bb.order(ByteOrder.nativeOrder());
        buffer = bb.asFloatBuffer();
        buffer.put(array);
        buffer.position(0);
        return buffer;
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
