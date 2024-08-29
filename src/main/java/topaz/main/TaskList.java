package topaz.main;

import topaz.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i).getStatus());
        }
    }

    public Task markAsDone(int index) {
        Task task = this.tasks.get(index - 1);
        task.setDone();
        return task;
    }

    public Task markAsUndone(int index) {
        Task task = this.tasks.get(index - 1);
        task.setUndo();
        return task;
    }

    public int getSize() {
        return tasks.size();
    }

    public void write(FileWriter fw) throws IOException {
        for (Task t: this.tasks) {
            fw.write(t.toFileRecord() + "\n");
        }
    }

    /**
     * Find a task by searching for a keyword.
     * @param filter
     * @return task conatins the filter keyword in description
     */
    public TaskList find(String filter) {
        ArrayList<Task> targetTasks = new ArrayList<>();
        for (Task t: tasks) {
            if (t.toString().contains(filter)) {
                targetTasks.add(t);
            }
        }
        return new TaskList(targetTasks);
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof TaskList) {
            TaskList other = (TaskList) object;
            for (int i = 0; i < other.getSize(); i++) {
                if (!this.tasks.get(i).equals(other.tasks.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + ". " + tasks.get(i).getStatus() + "\n";
        }
        return result;
    }
}
