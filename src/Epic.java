import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Subtask> subtasks;//храннение подзадач
    public Epic(String titleTask, String descriptionTask,TaskStatus taskStatus) {
        super(titleTask, descriptionTask,taskStatus);
        subtasks=new ArrayList<>(1);
    }
    public void addTask(Subtask subtask) {
        this.subtasks.add(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(ArrayList<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasks.size()=" + subtasks.size() +
                ", titleTask='" + titleTask + '\'' +
                ", descriptionTask='" + descriptionTask + '\'' +
                ", idTask=" + idTask +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
