package com.codablepixel.starbirthdaycalculator;

/**
 * Created by Jagannath on 10-03-2015.
 */
import android.app.Activity;
import android.util.Log;

import java.io.File;

import swisseph.*;

public class EphemerisQuery
{
    private static String TAG = "EQSBirthday";

    // read data of Sun and Moon from the swiss ephemeris
    public static double[] ephSunMoon(int sbYear, int sbMonth, int sbDay, double sbTime)
    {
        // calculation of local mean time and other time corrections like timezone, DST etc.
        int[] cityLong = Conversions.longlat2dms(80.27); // longitude for chennai in degrees, minutes and seconds notation
        double lmtCorrection = (((cityLong[0] * 4) + ((cityLong[1] * 4) /60.)) / 60.); // local mean time correction for chennai
        double timeZoneCorrection = (5 + 30./60.); // IST
        double diffLmtTz = timeZoneCorrection - lmtCorrection; // difference between local mean time and time zone
        double correctedTime = ((sbTime - timeZoneCorrection) + diffLmtTz);


        SwissEph sbEphemeris = new SwissEph(MainActivity.getContext().getFilesDir() + File.separator + "/ephe");
        SweDate sbDate = new SweDate(sbYear, sbMonth, sbDay, correctedTime);

        // The calculated values will be returned in this array:
        double[] resultsSun = new double[6];
        double[] resultsMoon = new double[6];

        // set flags
        int flags = SweConst.SEFLG_TOPOCTR	|	// for topo-centric calculation
                SweConst.SEFLG_SWIEPH	|	// fastest method, requires data files
                SweConst.SEFLG_SIDEREAL ;	// sidereal zodiac

        sbEphemeris.swe_set_sid_mode(SweConst.SE_SIDM_KRISHNAMURTI); // set sidereal mode. ayanamsa.

        sbEphemeris.swe_set_topo(80.27,13.08,2); // Chennai. set location with longitude, latitude and height above sea level in metre

        StringBuffer sbErrorEx = new StringBuffer();

        String julianDay = sbDate.toString();

        Log.i("Julian day", julianDay);

        int ephCalc1 = sbEphemeris.swe_calc_ut(sbDate.getJulDay(), SweConst.SE_SUN, flags, resultsSun, sbErrorEx); // main calculation method (Sun)
        int ephCalc2 = sbEphemeris.swe_calc_ut(sbDate.getJulDay(), SweConst.SE_MOON, flags, resultsMoon, sbErrorEx); // main calculation method (Moon)

        if (sbErrorEx.length() > 0) { // error messages
            Log.e(TAG, sbErrorEx.toString());
        }
        if (ephCalc1 == SweConst.ERR || ephCalc2 == SweConst.ERR) {
            System.exit(1);
        }

        // just need the longitude values of Sun and Moon
        double[] results = {Conversions.isNegative(resultsSun[0]), Conversions.isNegative(resultsMoon[0])};
        return results;
    }

}
