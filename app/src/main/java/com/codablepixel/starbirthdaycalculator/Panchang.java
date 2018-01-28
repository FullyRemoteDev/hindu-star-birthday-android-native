package com.codablepixel.starbirthdaycalculator;

/**
 * Created by Jagannath on 10-03-2015.
 */
public class Panchang
{
    public static String[] panchangCalc(double sunLong, double moonLong)
    {
        // 12 signs of the zodiac or rashis
        String[] rashis = {"Mesha","Vrishabha","Mithuna","Karka","Simha","Kanya",
                "Tula","Vrishchika","Dhanu","Makara","Kumbha","Meena"};

        // 27 constellations of the zodiac or nakshatras (stars)
        String[] nakshatras = {"Ashwini","Bharani","Krithika","Rohini","Mrigashirsa","Ardra",
                "Punarvasu","Pushya","Aslesha","Magha","Poorva Phalguni",
                "Uttara Phalguni","Hasta","Chitra","Swathi","Vishaka","Anuradha",
                "Jyeshta","Moola","Poorvashada","Uttarashada","Sravana",
                "Dhanishta","Shatabisha","Poorvabhadra","Uttarabhadra","Revathi"};

//		// 30 tithis
//		String[] tithis = {"Prathame","Dwithiya","Trithiya","Chathurthi","Panchami","Sashti","Saptami",
//							"Ashtami","Navami","Dashami","Ekadashi","Dwadashi","Trayodashi","Chaturdashi",
//							"Poornima",
//							"Prathame","Dwithiya","Trithiya","Chathurthi","Panchami","Sashti","Saptami",
//							"Ashtami","Navami","Dashami","Ekadashi","Dwadashi","Trayodashi","Chaturdashi",
//							"Amavasya"};


//		System.out.println("\nLongitude of Sun\t: " + sunLong);
//		System.out.println("Longitude of Moon\t: " + moonLong);
//
//		int[] sunLongDMS = Conversions.longlat2dms(sunLong);
//		int[] moonLongDMS = Conversions.longlat2dms(moonLong);
//
//		System.out.println("\nLongitude of Sun\t: " + sunLongDMS[0] + " Deg. " + sunLongDMS[1] + " Min. " + sunLongDMS[2] + " Sec.");
//		System.out.println("Longitude of Moon\t: " + moonLongDMS[0] + " Deg. " + moonLongDMS[1] + " Min. " + moonLongDMS[2] + " Sec.");



//		// calculation of rashi (zodiac sign)
//		String rashi = rashis[((int) Math.floor((moonLong / (360. / 12.))))]; // Math.floor is used as array starts from 0

        String sunSign = rashis[((int) Math.floor((sunLong / (360. / 12.))))]; // calculation of sun sign

        String nakshatra = nakshatras[((int) Math.floor((moonLong / (360. / 27.))))]; // calculation of the nakshatra (star)

        int nakshatraPada = ((int) Math.ceil(moonLong / (360. / (27. * 4.)))) % 4; // calculation of the nakshatra pada (4 padas for each nakshatra)

        if (nakshatraPada == 0) {
            nakshatraPada = 4;
        }

        // calculation of tithi
        if (moonLong < sunLong) {
            moonLong = moonLong + 360.;
        }
        double diffLong = moonLong - sunLong;
//		String tithi = tithis[(int) (Math.floor(diffLong / 12.))];

        String paksha;

        if (diffLong > 180 && diffLong < 360) {
            paksha = "Krishna Paksha";
        } else if (diffLong > 0 && diffLong < 180) {
            paksha = "Shukla Paksha";
        } else {
            paksha = "-";
        }


//		System.out.print("\nRashi\t\t: " + rashi);
//		System.out.print("\nSun Sign\t: " + sunSign);
//		System.out.print("\nNakshatra\t: " + nakshatra);
//		System.out.print("\nNakshatra Pada\t: " + nakshatraPada);
//		System.out.print("\nTithi\t\t: " + tithi);
//		System.out.print("\nPaksha\t\t: " + paksha);

//		String[] panchangWithTithi = {rashi, sunSign, nakshatra, tithi, paksha};
//		String[] panchangWithRashi = {rashi, sunSign, nakshatra, paksha};

        String[] panchang = {sunSign, nakshatra, paksha};

        return panchang;
    }

}
