import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TaskManager {
private HashMap<Integer,Task> tasks;
private HashMap<Integer,Epic> tasksEpic;
private HashMap<Integer,Subtask> subtasks;
private Integer globalIdTask;//сквозной идентификатор для задач
    public TaskManager() {
        this.tasks = new HashMap<>();
        this.tasksEpic = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.globalIdTask=0;
    }

    public Integer createTask(Task task) {
        int id=++globalIdTask;
        task.setIdTask(id);
        addListType(task);
        return id;
    }

    public void updateTask(Task updatedTask) {//обновление
        if(taskType(updatedTask)==1) {//провека что это просто задача
            tasks.remove(updatedTask.getIdTask());
            tasks.put(updatedTask.getIdTask(), updatedTask);
        } else if(taskType(updatedTask)==2){//провека что это эпик
                Epic epic = upStatusEpic((Epic) updatedTask);//обновит статус нового эпика
                if(epic !=null) {//проверить что обновление статуса прошло успешно
                    tasksEpic.remove(epic.getIdTask());//удалить старый эпик из списка
                    tasksEpic.put(epic.getIdTask(), epic);//добавит новый эпик в список
            }
        } else if(taskType(updatedTask)==3){//провека что это подзадача
            int idEpic= ((Subtask) updatedTask).getIdEpic();
            subtasks.remove(updatedTask.getIdTask());
                subtasks.put(updatedTask.getIdTask(),(Subtask) updatedTask);
                Epic epic = upStatusEpic(tasksEpic.get(idEpic));//обновит статус нового эпика
                if(epic !=null) {//проверить что обновление статуса прошло успешно
                    tasksEpic.remove(epic.getIdTask());//удалить старый эпик из списка
                    tasksEpic.put(epic.getIdTask(), epic);//добавит новый эпик в список
            }
        }
    }

    public Task getTask(int idTask) {//возвращает задачу по иденификатору
        Task task=tasks.get(idTask);
        if(task==null) {
            task=tasksEpic.get(idTask);
        }
        if (task==null){
            task=subtasks.get(idTask);
        }
        return task;
    }

    private int taskType(Task task){//внутренний метод для определения типа задачи
        if (task!=null) {//проверка что задача есть
            if (task.getClass().getName().equals("Task")) {
                return 1;
            } else if (task.getClass().getName().equals("Epic")) {
                return 2;
            } else if (task.getClass().getName().equals("Subtask")) {
                return 3;
            }
        }
        return 0;
    }

    private void addListType(Task task){//добавить задачу в список задач
        if(taskType(task)==1) {
            tasks.put(task.getIdTask(),task);
        } else if(taskType(task)==2) {
            tasksEpic.put(task.getIdTask(),(Epic) task);
        } else if(taskType(task)==3){
            tasksEpic.get(((Subtask)task).getIdEpic()).addTask((Subtask)task);
            subtasks.put(task.getIdTask(),(Subtask)task);
        }
    }

    private Epic upStatusEpic(Epic epic) {//возвращает измененный эпик при обновлении подзадачи
        int idEpic=epic.getIdTask();
        int statusNew = 0;
        int statusInProgress = 0;
        int statusDone = 0;

            int numberSubtasks = epic.getSubtasks().size();//вернуть количество подзадачь эпика
            if (numberSubtasks>0) {//проверка на имеющиеся подзадачи
                for (Subtask value : subtasks.values()) {//поиск подзадачь в принадлижащих данному эпику
                    if (idEpic == value.getIdEpic()) {
                        switch (value.getTaskStatus()) {//вычисление статусов подзадач
                            case NEW:
                                ++statusNew;
                                break;
                            case IN_PROGRESS:
                                ++statusInProgress;
                                break;
                            case DONE:
                                ++statusDone;
                                break;
                        }
                    }
                }

        Epic retEpic;
        if (statusNew == numberSubtasks || numberSubtasks==0) {//изменение статуса на NEW
            retEpic = new Epic(epic.getTitleTask(), epic.getDescriptionTask(), TaskStatus.NEW);
            retEpic.setIdTask(epic.getIdTask());//установить иде. обновляемого эпика
            retEpic.setSubtasks(epic.getSubtasks());//добавит старый список подзадач в обновленный эпик
        return retEpic;
        } else if ((statusInProgress <= numberSubtasks && statusInProgress > 0) ||  //изменение статуса на IN_PROGRESS
                (statusDone < numberSubtasks && statusDone >0 && statusDone!= numberSubtasks)) {
                    retEpic = new Epic(epic.getTitleTask(), epic.getDescriptionTask(), TaskStatus.IN_PROGRESS);
                    retEpic.setIdTask(epic.getIdTask());
                    retEpic.setSubtasks(epic.getSubtasks());
               return retEpic;
        } else if (statusDone == numberSubtasks) {//изменение статуса на DONE
                    retEpic = new Epic(epic.getTitleTask(), epic.getDescriptionTask(), TaskStatus.DONE);
                    retEpic.setIdTask(epic.getIdTask());
                    retEpic.setSubtasks(epic.getSubtasks());
               return retEpic;
                }
            }
        return null;
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getTasksEpic() {
        return tasksEpic;
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public ArrayList<Subtask> getSubtaksEpic(Epic epic) {
        return epic.getSubtasks();
    }

    public void deleteTaskById(int idTask){//удаление по идентификатору
        if(tasks.remove(idTask)==null){
            Subtask subtask=subtasks.get(idTask);
            if(subtask!=null) {
                subtasks.remove(idTask);//удалить подзадачу из списка
                Epic epic = tasksEpic.get(subtask.getIdEpic());//получить эпик
                epic.setSubtasks(makeList(epic));//обновить список подзадач эпика
                tasksEpic.remove(epic.getIdTask());//удалить эпик из списка задач
                tasksEpic.put(epic.getIdTask(),upStatusEpic(epic));//обновить эпик и добавить в список задач
            } else {
                for (Subtask value : tasksEpic.get(idTask).getSubtasks()) {//удаление подзадачь эпика
                    if(value.getIdEpic()==idTask){
                        subtasks.remove(value.getIdTask());
                    }
                }
                tasksEpic.remove(idTask);//удаление эпика
            }
        }
    }


    public void deleteTasks(){//удаление всех просто задач
        tasks.clear();
    }//удаление всех задач

    public void deleteEpics(){//удаление всех эпиков и подзадач
        tasksEpic.clear();
        subtasks.clear();
    }

    public void deleteSubtasks(){//удаление всех подзадач
        subtasks.clear();
        for (Epic value : tasksEpic.values()) {//обновить статус всех эпиков
            upStatusEpic(value);
        }
    }

 private ArrayList<Subtask> makeList(Epic epic){
     ArrayList<Subtask> list=new ArrayList<>(1);
        for (Subtask value : subtasks.values()) {
         if(value.getIdEpic()==epic.getIdTask()){
             list.add(value);
         }
     }
        return list;
 }
}
