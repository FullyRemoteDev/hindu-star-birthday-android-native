package com.codablepixel.starbirthdaycalculator;

/**
 * Created by Jagannath on 10-03-2015.
 */
import java.util.Arrays;
import java.util.Calendar;

public class StarBirthday
{
    private static int dobYear, dobMonth, dobDay; // variables to hold initial user input
    private static int dobHours, dobMinutes;
    private static int sbcYear; // star birthday calculation year

    public static final double SBC_TIME = 6.5; // sunrise time is taken as star birthday calculation time

    public static int[] calculate()
    {
        double dobTime = (dobHours + (dobMinutes / 60.)); // time in hours
        int starBdayDay = 0, starBdayMonth = 0; // variables to hold star birthday day and month after match is found in search

        double results[] = EphemerisQuery.ephSunMoon(dobYear, dobMonth, dobDay, dobTime); // main method to get longitude values of Sun and Moon

        String[] panchang = Panchang.panchangCalc(results[0], results[1]); // main method to get some panchang values from longitude of Sun and Moon
        // results received in the above panchang string array in order: [0] = Rashi, [1] = Sun Sign, [2] = Nakshatra, [3] = Tithi, [4] = Paksha


        int sbcMonth = dobMonth, sbcDay = 1, sbcLastDay = 0; // variables used while searching for star birthday

        // code block STARTS for searching star birthday in the birthday month

        sbcLastDay = lastDayOfMonth(sbcMonth);

        while ((sbcDay < sbcLastDay) && starBdayDay == 0)
        {
            double sbsResults[] = EphemerisQuery.ephSunMoon(sbcYear, sbcMonth, sbcDay, SBC_TIME);
            String[] sbsPanchang = Panchang.panchangCalc(sbsResults[0], sbsResults[1]);

            if (Arrays.equals(panchang, sbsPanchang))
            {
                starBdayDay = sbcDay;
                starBdayMonth = sbcMonth;
            }
            sbcDay++;
        }


        if ((dobDay > 15) && starBdayDay == 0)
        {
            sbcMonth = dobMonth + 1;
            sbcLastDay = lastDayOfMonth(sbcMonth);
            sbcDay = 1;

            while (sbcDay < (sbcLastDay - 15))
            {
                double sbsResults[] = EphemerisQuery.ephSunMoon(sbcYear, sbcMonth, sbcDay, SBC_TIME);
                String[] sbsPanchang = Panchang.panchangCalc(sbsResults[0], sbsResults[1]);

                if (Arrays.equals(panchang, sbsPanchang))
                {
                    starBdayDay = sbcDay;
                    starBdayMonth = sbcMonth;
                }
                sbcDay++;

            }
        }

        if ((dobDay < 16) && starBdayDay == 0)
        {
            sbcMonth = dobMonth - 1;
            sbcLastDay = lastDayOfMonth(sbcMonth);
            sbcDay = sbcLastDay;

            while (sbcDay > 15)
            {
                double sbcResults[] = EphemerisQuery.ephSunMoon(sbcYear, sbcMonth, sbcDay, SBC_TIME);
                String[] sbcPanchang = Panchang.panchangCalc(sbcResults[0], sbcResults[1]);

                if (Arrays.equals(panchang, sbcPanchang))
                {
                    starBdayDay = sbcDay;
                    starBdayMonth = sbcMonth;
                }
                sbcDay--;

            }
        }

        int sbResults[] = {starBdayDay, starBdayMonth, sbcYear};

        return sbResults;

    }


    // gets the last day of the given month
    public static int lastDayOfMonth(int sbcMonth)
    {
        Calendar sbcCal = Calendar.getInstance();
        sbcCal.add(Calendar.MONTH, (sbcMonth - 1));

        return (sbcCal.getActualMaximum(Calendar.DAY_OF_MONTH));
    }



    // Getters and Setters for DOB and TOB variables

    public static int getDobYear() {
        return dobYear;
    }

    public static void setDobYear(int dobYear) {
        StarBirthday.dobYear = dobYear;
    }

    public static int getDobMonth() {
        return dobMonth;
    }

    public static void setDobMonth(int dobMonth) {
        StarBirthday.dobMonth = dobMonth;
    }

    public static int getDobDay() {
        return dobDay;
    }

    public static void setDobDay(int dobDay) {
        StarBirthday.dobDay = dobDay;
    }

    public static int getDobHours() {
        return dobHours;
    }

    public static void setDobHours(int dobHours) {
        StarBirthday.dobHours = dobHours;
    }

    public static int getDobMinutes() {
        return dobMinutes;
    }

    public static void setDobMinutes(int dobMinutes) {
        StarBirthday.dobMinutes = dobMinutes;
    }

    public static int getSbcYear() {
        return sbcYear;
    }

    public static void setSbcYear(int sbcYear) {
        StarBirthday.sbcYear = sbcYear;
    }
}
