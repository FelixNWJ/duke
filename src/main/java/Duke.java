import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        while(input.hasNext()) {
            String command = input.next();

            switch(command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                    break;

                case "done":
                    try {
                        int taskNumber = input.nextInt();
                        Task current = tasks.get(taskNumber - 1);
                        if(current.getStatus()) {
                            throw new DukeTaskDoneException();
                        }
                        current.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(current.toString());
                    } catch (InputMismatchException e) {
                        System.out.println("OOPS!!! Number of completed task required.");
                        input.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Input is an invalid task number.");
                    } catch (DukeTaskDoneException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "delete":
                    try {
                        int taskNumber = input.nextInt();
                        Task current = tasks.get(taskNumber - 1);
                        tasks.remove(current);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(current.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (InputMismatchException e) {
                        System.out.println("OOPS!!! Number of task to delete required.");
                        input.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Input is an invalid task number.");
                    }
                    break;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < tasks.size(); i++) {
                        Task current = tasks.get(i);
                        System.out.println((i + 1) + "." + current.toString());
                    }
                    break;

                case "todo":
                    try {
                        String toDo = input.nextLine().trim();
                        if(toDo.isEmpty()) {
                            throw new DukeEmptyDescriptionException("todo");
                        } else {
                            System.out.println("Got it. I've added this task:");
                            Task current = new Todo(toDo);
                            tasks.add(current);
                            System.out.println(current.toString());
                            System.out.println("Now you have " + tasks.size() +" task in the list.");
                        }
                    } catch (DukeEmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "deadline":
                    try {
                        String deadlineTask = input.nextLine().trim();
                        String[] information = deadlineTask.split("/by");
                        if (information.length != 2 && deadlineTask.isEmpty()) {
                            throw new DukeEmptyDescriptionException("deadline");
                        } else if (information.length != 2 && !deadlineTask.isEmpty()) {
                            throw new DukeMissingDescriptionException(("deadline"));
                        } else {
                            System.out.println("Got it. I've added this task:");
                            String[] dateAndTime = information[1].trim().split(" ");
                            String[] date = dateAndTime[0].split("/");
                            String dateInWords = Duke.dateToWords(date);
                            String time = Duke.timeConverter(dateAndTime[1]);
                            Deadline current = new Deadline(information[0].trim(), dateInWords +", " + time);
                            tasks.add(current);
                            System.out.println(current.toString());
                            System.out.println("Now you have " + tasks.size() + " task in the list.");
                        }
                    } catch (DukeEmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (DukeMissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "event":
                    try {
                        String eventTask = input.nextLine().trim();
                        String[] information = eventTask.split("/at");
                        if (information.length != 2 && eventTask.isEmpty()) {
                            throw new DukeEmptyDescriptionException("event");
                        } else if (information.length != 2 && !eventTask.isEmpty()) {
                            throw new DukeMissingDescriptionException("event");
                        } else {
                            System.out.println("Got it. I've added this task:");
                            String[] dateAndTime = information[1].trim().split(" ");
                            String[] date = dateAndTime[0].split("/");
                            String dateInWords = Duke.dateToWords(date);
                            String time = Duke.timeConverter(dateAndTime[1]);
                            Event current = new Event(information[0].trim(), dateInWords +", " + time);
                            tasks.add(current);
                            System.out.println(current.toString());
                            System.out.println("Now you have " + tasks.size() + " task in the list.");
                        }
                    } catch (DukeEmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (DukeMissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    try {
                        input.nextLine();
                        throw new DukeWrongInputException();
                    } catch (DukeWrongInputException e) {
                        System.out.println(e.getMessage());
                    }
            }

        }
    }

    public static String dateToWords(String[] date) {
        String month;
        switch(date[1]) {
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
        return day + " of " + month + " 20" + date[2];
    }

    public static String timeConverter(String time) {
        int numberTime = Integer.parseInt(time);
        if (numberTime < 1000) {
            return String.valueOf(numberTime).substring(0, 1) + "." + String.valueOf(numberTime).substring(1, 3) + "am";
        } else if (numberTime < 1200) {
            return String.valueOf(numberTime).substring(0, 2) + "." + String.valueOf(numberTime).substring(2, 4) + "am";
        } else if (numberTime < 1300) {
            return String.valueOf(numberTime).substring(0, 2) + "." + String.valueOf(numberTime).substring(2, 4) + "pm";
        } else {
            numberTime -= 1200;
            if (numberTime < 1000) {
                return String.valueOf(numberTime).substring(0, 1) + "." + String.valueOf(numberTime).substring(1, 3) + "pm";
            } else {
                return String.valueOf(numberTime).substring(0, 2) + "." + String.valueOf(numberTime).substring(2, 4) + "pm";
            }
        }
    }
}


