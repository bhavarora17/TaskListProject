import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SpringProject {

    public static void main(String[] args) {
        SpringProject springProject = new SpringProject();
    }

}

class Sequence {

    private static final AtomicInteger counter = new AtomicInteger();

    public static int nextValue() {
        return counter.getAndIncrement();
    }
}

class Task extends Sequence {

    private int ID;
    private int rank;
    private int priority;
    private int estimatedTime;
    private int completionTime;
    private String status;
    private boolean isRecurring;


    public Task(int priority, int estimatedTime, boolean isRecurring) {

        this.ID = nextValue();
        this.priority = priority;
        this.estimatedTime = estimatedTime;
        this.isRecurring = isRecurring;
        this.status = "NOT STARTED";
    }

    public int getID() {
        return ID;
    }

    public int getRank() {
        return rank;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }


}


abstract class Person extends Sequence {

    int ID;
    String name;
    List<String> notes = new ArrayList<>();

    Task createTask(int priority, int estimatedTime, boolean isRecurring) {
        Task task = new Task(priority, estimatedTime, isRecurring);
        return task;

    }

    private void setID() {

        this.ID = generateUserID();

    }

    int getID() {

        return this.ID;

    }

    int generateUserID () {

        return nextValue();

    }

    String getName() {

        return this.name;

    }

    abstract void updateNotes();
    abstract void checkAllTaskStatus();
    abstract void checkTaskStatusByID(int taskID);

}

class Manager extends Person{

    Map<Integer, User> userMap;

    @Override
    public void updateNotes() {

        notes.add("This is an example");

    }

    @Override
    public void checkAllTaskStatus() {

        for (User user : userMap.values()) {

            List<Task> taskList = user.taskList;

            for (Task task : taskList) {

                System.out.println(user.getID() + " " + user.getName() + " " +
                        task.getID() + " " + task.getPriority() + " " + task.getEstimatedTime() + " " +
                        task.getCompletionTime() + " " + task.getStatus());

            }
        }
    }

    @Override
    public void checkTaskStatusByID(int taskID) {

        for (User user : userMap.values()) {

            List<Task> taskList = user.taskList;

            for (Task task : taskList) {

                if (task.getID() == taskID) {
                    System.out.println(user.getName() + " " + user.getID() + " "
                            + task.getID() + " " + task.getPriority() + " " + task.getEstimatedTime()
                            + " " + task.getCompletionTime() + " " + task.getStatus());
                }
            }
        }
    }

    void createUser(String name) {

        User user = new User(name);
        userMap.put(user.getID(), user);

    }

    void deleteUser(int userID, String name) {

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {

            if (entry.getKey() == userID && entry.getValue().getName().equals(name))
                userMap.remove(entry.getKey());

        }

    }

    void  viewUsers() {

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {

            System.out.println(entry.getKey() + " " + entry.getValue() );

        }

    }

    void viewTaskList() {

        for (User user : userMap.values()) {

            List<Task> taskList = user.taskList;
            for (Task task : taskList)
                System.out.println( task.getID()  + " " + user.getID() + " " + user.getName());

        }
    }

    void giveFeedBack(int userID, int userName) {

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {

            if (entry.getKey() == userID && entry.getValue().getName().equals(userName))
                entry.getValue().feedBacks.add("This is some feedback");

        }
    }

}


class User extends Person {

    List<String> feedBacks;
    List<Task> taskList, finishedTask;

    public User( String name) {
        this.name = name;
        taskList = new ArrayList<>();
    }

    @Override
    public void updateNotes() {

        notes.add("This is an example");

    }

    @Override
    public void checkAllTaskStatus() {

        for (Task task : taskList)
            System.out.println(task.getStatus());

    }

    @Override
    public void checkTaskStatusByID(int taskID) {

        for (Task task : taskList) {
            if (task.getID() == taskID)
                System.out.println(task.getStatus());
        }
    }

    void addTaskToTaskList(int priority, int estimatedTime, boolean isRecurring) {

        taskList.add(createTask(priority, estimatedTime, isRecurring));

    }

    void viewTaskList() {

        for (Task task : taskList)
            System.out.println(task.getID());

    }

    void viewFeedbacks() {

        for (String string : feedBacks)
            System.out.println(string);

    }

}
