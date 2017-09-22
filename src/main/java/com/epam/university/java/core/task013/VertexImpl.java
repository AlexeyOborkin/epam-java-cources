package com.epam.university.java.core.task013;

public class VertexImpl implements Vertex {
    private int x;
    private int y;

    VertexImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int value) {
        this.x = value;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VertexImpl vertex = (VertexImpl) o;

        return x == vertex.x && y == vertex.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public void setY(int value) {
        this.y = value;
    }
}
