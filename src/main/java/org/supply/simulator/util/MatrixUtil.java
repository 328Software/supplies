package org.supply.simulator.util;

import org.lwjgl.util.vector.Matrix4f;

import static java.lang.Math.PI;

/**
 * Created by Brandon on 5/3/2016.
 */
public class MatrixUtil {
    public static Matrix4f perspective(float left, float right, float bottom, float top, float near, float far)
    {
        Matrix4f matrix = new Matrix4f();

        matrix.setIdentity();

        matrix.m00 = 2*near/(right - left);
        matrix.m11 = 2*near/(top - bottom);
        matrix.m22 = -(far +near)/(far - near);
        matrix.m23 = -1;
        matrix.m32 = -2*far*near/(far - near);
        matrix.m20 = (right+left)/(right -left);
        matrix.m21 = (top + bottom)/(top-bottom);
        matrix.m33 = 0;

        return matrix;
    }

    public static Matrix4f orthogonal(float left, float right, float bottom, float top, float near, float far)
    {
        Matrix4f matrix = new Matrix4f();

        matrix.setIdentity();

        matrix.m00 = 2/(right - left);
        matrix.m11 = 2/(top - bottom);
        matrix.m22 = -2/(far - near);
        matrix.m32 = (far+near)/(far - near);
        matrix.m30 = (right+left)/(right -left);
        matrix.m31 = (top + bottom)/(top-bottom);

        return matrix;
    }

    public static Matrix4f projection(float fov, float aspectRatio, float near, float far)
    {
        Matrix4f matrix = new Matrix4f();

        float yScale = (float) (1f / Math.tan((fov / 2f) * (float) (PI / 180d)));
        float xScale = yScale / aspectRatio;
        float frustum_length = far - near;

        matrix.m00 = xScale;
        matrix.m11 = yScale;
        matrix.m22 = -((far + near) / frustum_length);
        matrix.m23 = -1;
        matrix.m32 = -((2 * near * far) / frustum_length);
        matrix.m33 = 0;

        return matrix;
    }
}
