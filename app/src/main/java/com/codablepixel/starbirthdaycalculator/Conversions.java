package com.codablepixel.starbirthdaycalculator;

/**
 * Created by Jagannath on 10-03-2015.
 */
public class Conversions
{

    // adds 360 to negative longitude and latitude values
    public static int isNegative(int longLat)
    {
        if (longLat < 0)
        {
            longLat = longLat + 360;
        }

        return longLat;
    }

    public static double isNegative(double longLat)
    {
        if (longLat < 0)
        {
            longLat = longLat + 360.;
        }

        return longLat;
    }

    // converts longitude in decimal form to degrees, minutes and seconds notation
    public static int[] longlat2dms(double longLat)
    {
        int degrees = (int) longLat;
        int minutes = (int) ((longLat - degrees) * 60.);
        int seconds = (int) ((((longLat - degrees) * 60.) - minutes) * 60.);

        int[] dms = new int[3];

        dms[0] = degrees;
        dms[1] = minutes;
        dms[2] = seconds;

        return dms;
    }

    // converts longitude in degrees, minutes and seconds notation to decimal form
    public static double dms2longlat(int degrees, int minutes, int seconds)
    {
        return degrees + (minutes / 60.) + (seconds / 60.);
    }

    // converts longitude in decimal form to all seconds
    public static int longlat2s(double longLat)
    {
        return (int) (longLat * 3600);
    }

    // converts longitude in degrees, minutes and seconds notation to all seconds
    public static int dms2seconds(int degrees, int minutes, int seconds)
    {
        return (degrees + (minutes / 60) + (seconds / 60)) * 3600;
    }

}
