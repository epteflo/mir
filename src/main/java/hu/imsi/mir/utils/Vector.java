package hu.imsi.mir.utils;

import java.util.StringJoiner;

public class Vector {

    public static final double EPSILON = 0.01d;

    private double x;
    private double y;

    public Vector(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector scale(double factor) {
        return new Vector(x * factor, y * factor);
    }

    public Vector normalize() {
        return this.scale(1d / this.length());
    }

    public double dot(final Vector other) {
        return x * other.x + y * other.y;
    }

    public Vector plus(final Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector minus(final Vector other) {
        return plus(other.scale(-1d));
    }

    public Vector rotateRadians(final double rads) {
        return new Vector(
                x * Math.cos(rads) - y * Math.sin(rads),
                x * Math.sin(rads) + y * Math.cos(rads)
        );
    }

    //speciális eset, 90 fokkal forgatunk
    public Vector rotateByPiOver2() {
        return new Vector(
                -y,
                x
        );
    }

    //milyen messze vagyunk a két vektor végpontja áltam meghatározott egyenestől?
    public double distanceFromLine(final Vector v1, final Vector v2) {
        //ha azonos a v1 és a v2, akkor a pontól való távolság
        if (v1.equals(v2)) {
            return this.minus(v1).length();
        }
        //egyébként, előállítunk egy normárvektort
        final Vector normal = v1.minus(v2).normalize().rotateByPiOver2();
        //és azzal szorozzuk (this - v1)-et
        return Math.abs(this.minus(v1).dot(normal));
    }

    // benne vagyunk-e a két vektor által meghatározott dobozban, max epsilon eltéréssel
    public boolean isInBoundingBox(Vector v1, Vector v2, final double epsilon) {
        final Vector bottomLeft = new Vector(Math.min(v1.x, v2.x) - epsilon, Math.min(v1.y, v2.y) - epsilon);
        final Vector topRight = new Vector(Math.max(v1.x, v2.x) + epsilon, Math.max(v1.y, v2.y) + epsilon);
        //ha benne vagyunk a dobozban
        return bottomLeft.x <= x && bottomLeft.y <= y
                && topRight.x >= x && topRight.y >= y;
    }

    public boolean isBetween(final Vector v1, final Vector v2, final double epsilon) {
        return isInBoundingBox(v1, v2, epsilon) &&
                distanceFromLine(v1, v2) <= epsilon;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Vector.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vector)) {
            return false;
        }

        final Vector vector = (Vector) o;

        if (Double.compare(vector.x, x) != 0) {
            return false;
        }
        return Double.compare(vector.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
