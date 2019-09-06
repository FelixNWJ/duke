package duke.storagedata;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import duke.exception.DukeTaskDoneException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.tasklist.TaskList;

/**
 * Represents the Storage that contains the File where the data of the Duke App is stored.
 */
public class StorageData {
    private File data;

    /**
     * Creates the StorageData Object.
     * Checks if the file that is passed into the constructor is present.
     * If present, it uses the file as its data and stores it in the StorageData Object.
     * If not present, a FileNotFound exception is throw, where a new File is created and used instead.
     * @param newData the File that is used to store the data of the Duke App.
     */
    public StorageData(File newData) {
        try {
            boolean exists = newData.exists();
            if(exists) {
                this.data = newData;
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            try {
                new File("data/tasks.txt").createNewFile();
                this.data = newData;
                System.out.println("New save data created");
            } catch (IOException ex) {
                System.out.println(e.getMessage());
            }
        }
    }

    public File getData() {
        return this.data;
    }

    /**
     * Checks what type of Task object to be saved into the file and saves the details accordingly.
     * @param current the Task object to be saved.
     */
    public void addData(Task current) {
        if(current instanceof Todo) {
            this.addTodoData(current.getDescription());
        } else if (current instanceof Event) {
            this.addEventData(current.getDescription(), ((Event) current).getPeriod());
        } else {
            this.addDeadlineData(current.getDescription(), ((Deadline) current).getTime());
        }
    }

    /**
     * Adds the data of the Todo Task into the file.
     * @param toDo contains the details of the Todo Object
     */
    public void addTodoData(String toDo) {
        try {
            FileWriter writer = new FileWriter(data, true);
            writer.write("T - not done - " + toDo + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Adds the data of the Deadline Task into the file.
     * @param description contains the details of the deadline.
     * @param byWhen contains the time when the deadline is due
     */
    public void addDeadlineData(String description, String byWhen) {
        try {
            FileWriter writer = new FileWriter(data, true);
            writer.write("D - not done - " + description + " - " + byWhen + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Adds the data of the Event Task into the file.
     * @param description contains the details of the Event
     * @param duringWhen contains the time when the event takes place.
     */
    public void addEventData(String description, String duringWhen) {
        try {
            FileWriter writer = new FileWriter(data, true);
            writer.write("E - not done - " + description + " - " + duringWhen + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Modifies the data of the Task in the file to mark is as done.
     * @param taskNumber contains the number of the Task is the Duke App.
     */
    public void markTaskDoneInData(int taskNumber, TaskList tasks) throws DukeTaskDoneException {
        try {
            new File("data/tasks.txt").delete();
            new File("data/tasks.txt").createNewFile();
            File newData = new File("data/tasks.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        tasks.done(taskNumber);
        for(int i = 0; i < tasks.size(); i++) {
            this.addData(tasks.get(i));
        }
    }
    /**
     * Deletes the data of the Task in the file.
     * @param taskNumber contains the number of the Task in the Duke App to be deleted.
     * @return the Task object that is being deleted.
     */
    public Task deleteTaskInData(int taskNumber, TaskList tasks) {
        try {
            new File("data/tasks.txt").delete();
            new File("data/tasks.txt").createNewFile();
            File newData = new File("data/tasks.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Task deletedTask = tasks.get(taskNumber - 1);
        tasks.delete(taskNumber);
        for(int i = 0; i < tasks.size(); i++) {
            this.addData(tasks.get(i));
        }
        return deletedTask;
    }

    /**
     * Translates the data from the File stored in the StorageData object into Tasks.
     * The Tasks are then stored in an ArrayList.
     * @return an ArrayList that stores the tasks that have been translated from the data in the File object.
     * @throws FileNotFoundException when the file to used does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskArrayList = new ArrayList<>(100);

        Scanner fileInput = new Scanner(this.data);
        while(fileInput.hasNextLine()) {
            String task = fileInput.next();
            switch(task) {
            case "T":
                String[] contents = fileInput.nextLine().trim().split("-");
                Task current = new Todo(contents[2].trim());
                if(contents[1].trim().equals("done")) {
                    current.markAsDone();
                }
                taskArrayList.add(current);
                break;

            case "D":
                contents = fileInput.nextLine().trim().split("-");
                current = new Deadline(contents[2].trim(), contents[3].trim());
                if(contents[1].trim().equals("done")) {
                    current.markAsDone();
                }
                taskArrayList.add(current);
                break;

            case "E":
                contents = fileInput.nextLine().trim().split("-");
                current = new Event(contents[2].trim(), contents[3].trim());
                if(contents[1].trim().equals("done")) {
                    current.markAsDone();
                }
                taskArrayList.add(current);
                break;

            default:
                break;
            }
        }
        return taskArrayList;
    }
}
