package duke.time;

import duke.exception.DukeWrongTimeFormatException;

/**
 * Utility class to convert a string into a specified time and date format.
 */
public class TimeConverter {

    /**
     * Converts a text into a date and time format e.g 2nd of December 2019, 6:00pm.
     * @param text is the date and time to be converted into format above.
     * @return a String that consists of date and time with specified format.
     */
    public static String convert(String text) throws DukeWrongTimeFormatException {
        System.out.println(text);
        if(text.split("/").length != 3) {
            throw new DukeWrongTimeFormatException();
        }
        String[] date = text.trim().split(" ");
        String dateWord = TimeConverter.dateToWords(date[0]);
        String time = TimeConverter.timeConverter(date[1]);
        System.out.println(dateWord + ", " + time);
        return dateWord + ", " + time;
    }

    /**
     * Converts a date represented in numerical form to a word form.
     *
     * @param dateFull a date in numerical format.
     * @return a String that represents a date in words.
     */
    public static String dateToWords(String dateFull) {
        String[] date = dateFull.split("/");
        if(date[0].contains("0")) {
            date[0] = date[0].replace("0", "");
        }
        if(date[1].contains("0")) {
            date[1] = date[1].replace("0", "");
        }
        String month;
        switch (date[1]) {
        case "1":
            month = "January";
            break;

        case "2":
            month = "Febuary";
            break;

        case "3":
            month = "March";
            break;

        case "4":
            month = "April";
            break;

        case "5":
            month = "May";
            break;

        case "6":
            month = "June";
            break;

        case "7":
            month = "July";
            break;

        case "8":
            month = "August";
            break;

        case "9":
            month = "September";
            break;

        case "10":
            month = "October";
            break;

        case "11":
            month = "November";
            break;

        case "12":
            month = "December";
            break;

        default:
            month = "month";
            break;
        }
        String day = date[0].equals("1") ? date[0] + "st" : date[0].equals("2") ? date[0] + "nd" :
                date[0].equals("3") ? date[0] + "rd" : date[0] + "th";
        return day + " of " + month + " " + date[2];
    }

    /**
     * Converts a time represented by a string in 24hour format to a 12hour format indicating am or pm.
     *
     * @param time time represented by a string in 24hour format
     * @return time represented by a string in 12hour format.
     */
    public static String timeConverter(String time) {
        int numberTime = Integer.parseInt(time);
        if (numberTime < 1000) {
            return String.valueOf(numberTime).substring(0, 1) + "." +
                    String.valueOf(numberTime).substring(1, 3) + "am";
        } else if (numberTime < 1200) {
            return String.valueOf(numberTime).substring(0, 2) + "." +
                    String.valueOf(numberTime).substring(2, 4) + "am";
        } else if (numberTime < 1300) {
            return String.valueOf(numberTime).substring(0, 2) + "." +
                    String.valueOf(numberTime).substring(2, 4) + "pm";
        } else {
            numberTime -= 1200;
            if (numberTime < 1000) {
                return String.valueOf(numberTime).substring(0, 1) + "." +
                        String.valueOf(numberTime).substring(1, 3) + "pm";
            } else {
                return String.valueOf(numberTime).substring(0, 2) + "." +
                        String.valueOf(numberTime).substring(2, 4) + "pm";
            }
        }
    }
}