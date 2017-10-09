package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;
import com.epam.university.java.core.task015.PointFactoryImpl;

import java.util.Collection;

public class Task021Impl implements Task021 {
    @Override
    public Point calculate(Collection<Point> minePositions) {
        final Point[] points = (Point[]) minePositions.toArray();

        // check for valid arguments
        isValidArguments(points);

        Point result = checkForFermatPoint(points);
        // check for angle < 120
        if (result != null) {
            return result;
        }

        // vertexes of initial triangle
        double firstX = points[0].getX();
        double firstY = points[0].getY();
        double secondX = points[1].getX();
        double secondY = points[1].getY();
        double thirdX = points[2].getX();
        double thirdY = points[2].getY();
        
        // additional vertex of first external triangle
        double additionalFirstX = (thirdX + secondX + (thirdY - secondY) * Math.sqrt(3)) / 2;
        double additionalFirstY = (thirdY + secondY + (secondX - thirdX) * Math.sqrt(3)) / 2;

        // additional vertex of second external triangle
        double additionalSecondX = (firstX + secondX + (firstY - secondY) * Math.sqrt(3)) / 2;
        double additionalSecondY = (firstY + secondY + (secondX - firstX) * Math.sqrt(3)) / 2;

        // segment's parameters
        double a1 = thirdY - additionalSecondY;
        double b1 = additionalSecondX - thirdX;
        double a2 = firstY - additionalFirstY;
        double b2 = additionalFirstX - firstX;

        double d = a1 * b2 - a2 * b1;

        double c1 = additionalSecondY * thirdX - additionalSecondX * thirdY;
        double c2 = additionalFirstY * firstX - additionalFirstX * firstY;

        // calculating X and Y of fermat point
        double resultX = (b1 * c2 - b2 * c1) / d;
        double resultY = (a2 * c1 - a1 * c2) / d;
        
        return new PointFactoryImpl().newInstance(resultX, resultY);
    }

    /**
     * Check existing triangle for angels >= 120°.
     *
     * @param points collection of vertexes of triangle.
     * @return new instance of Point where angle >= 120°. If there is no angle >= 120°, return null.
     */
    private Point checkForFermatPoint(Point[] points) {
        double ax = points[0].getX();
        double ay = points[0].getY();
        double bx = points[1].getX();
        double by = points[1].getY();
        double cx = points[2].getX();
        double cy = points[2].getY();

        double abLength = Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2));
        double acLength = Math.sqrt(Math.pow(cx - ax, 2) + Math.pow(cy - ay, 2));
        double bcLength = Math.sqrt(Math.pow(cx - bx, 2) + Math.pow(cy - by, 2));

        double acosA = Math.acos(
                (Math.pow(abLength, 2) + Math.pow(acLength, 2) - Math.pow(bcLength, 2))
                        / (2 * abLength * acLength));
        double acosB = Math.acos(
                (Math.pow(abLength, 2) + Math.pow(bcLength, 2) - Math.pow(acLength, 2))
                        / (2 * abLength * bcLength));
        double acosC = Math.acos(
                (Math.pow(acLength, 2) + Math.pow(bcLength, 2) - Math.pow(abLength, 2))
                        / (2 * acLength * bcLength));

        if (acosA >= (2 * Math.PI / 3)) {
            return points[0];
        } else if (acosB >= (2 * Math.PI / 3)) {
            return points[1];
        } else if (acosC >= (2 * Math.PI / 3)) {
            return points[2];
        }

        return null;
    }

    /**
     * Check for valid arguments.
     *
     * @param arg array of triangle's points.
     */
    private void isValidArguments(Point[] arg) {
        if (arg.length != 3) {
            throw new IllegalArgumentException("3 points required");
        }
    }
}