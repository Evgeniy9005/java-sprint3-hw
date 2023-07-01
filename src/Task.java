public class Task {
    protected String titleTask;//наименование задачи
    protected String descriptionTask;//описание задачи
    protected Integer idTask;//идентификатор
    protected TaskStatus taskStatus; //статус выполнения задачи

    public Task(String titleTask, String descriptionTask,TaskStatus taskStatus) {
        this.titleTask = titleTask;
        this.descriptionTask = descriptionTask;
        this.idTask = -1;
        this.taskStatus = taskStatus;
    }

    public String getTitleTask() {
        return titleTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public TaskStatus getTaskStatus() {//вернуть статус
        return taskStatus;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "titleTask='" + titleTask + '\'' +
                ", descriptionTask='" + descriptionTask + '\'' +
                ", idTask=" + idTask +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
