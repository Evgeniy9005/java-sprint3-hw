public class Subtask extends Task{
    private Integer idEpic;

    public Subtask(String titleTask, String descriptionTask, Integer idEpic,TaskStatus taskStatus) {
        super(titleTask, descriptionTask,taskStatus);
        this.idEpic = idEpic;
    }

    public Integer getIdEpic() {
        return idEpic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "idEpic=" + idEpic +
                ", titleTask='" + titleTask + '\'' +
                ", descriptionTask='" + descriptionTask + '\'' +
                ", idTask=" + idTask +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
