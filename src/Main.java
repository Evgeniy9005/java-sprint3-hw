public class Main {
    public static void main(String[] args) {
        TaskManager taskManager=new TaskManager();
        Task task1=new Task("Позавтракать","Сьесть апельси",TaskStatus.NEW);
        Task task2=new Task("Покушать","Сьесть "+'"'+"Том-Ям"+'"',TaskStatus.NEW);
        taskManager.createTask(task1);//создать задачу
        taskManager.createTask(task2);
        Epic epic1=new Epic(
                "Построить замок","Замок должен быть 5 этажный, на 10000 квадратных метров, на горе",
                TaskStatus.NEW);
        Integer idEpic1=taskManager.createTask(epic1);//создать задачу эпик1
        Subtask subtask11 =new Subtask(
                "Заказать кирпичи","Кирпичи должны быть гранитные",idEpic1,TaskStatus.NEW);
        Subtask subtask21 =new Subtask(
                "Сложить кирпичи","Складывать кирпичи в шахмотном порядке в десять слоев толщены",
                idEpic1,TaskStatus.NEW);
        Subtask subtask31 =new Subtask(
                "Купить материалы для кровли","6 метровые доски и черепицу",idEpic1,TaskStatus.NEW);
        Subtask subtask41 =new Subtask(
                "Накрыть кровлю","Крыть кровлю под 56 градусов",idEpic1,TaskStatus.NEW);
        taskManager.createTask(subtask11);//создать подзадчу
        taskManager.createTask(subtask21);
        taskManager.createTask(subtask31);
        taskManager.createTask(subtask41);

        Epic epic2=new Epic("Эпик 2","Эпик 2 тест", TaskStatus.NEW);
        Integer idEpic2=taskManager.createTask(epic2);//создать задачу эпик2
        Subtask subtask12 =new Subtask("Подзадача 1 эпик 2","подзадача 1 эпик 2 тест",
                idEpic2,TaskStatus.NEW);
        Subtask subtask22 =new Subtask("Подзадача 2 эпик 2","подзадача 2 эпик 2 тест",
                idEpic2,TaskStatus.NEW);
        taskManager.createTask(subtask12);//создать подзадчу
        taskManager.createTask(subtask22);

        System.out.println(taskManager.getTask(task1.getIdTask()));//найти и вывести в командную строку
        System.out.println(taskManager.getTask(task2.getIdTask()));
        System.out.println(taskManager.getTask(epic1.getIdTask()));
        System.out.println(taskManager.getTask(subtask11.getIdTask()));
        System.out.println(taskManager.getTask(subtask21.getIdTask()));
        System.out.println(taskManager.getTask(subtask31.getIdTask()));
        System.out.println(taskManager.getTask(subtask41.getIdTask()));
        System.out.println(taskManager.getTask(epic2.getIdTask()));
        System.out.println(taskManager.getTask(subtask12.getIdTask()));
        System.out.println(taskManager.getTask(subtask22.getIdTask()));
        System.out.println(taskManager.getTask(11));//возвращает null т.к. нет такой задачи
        System.out.println();
        System.out.println("Обновленная задача - " + task2.getTitleTask());
        Task upTask2=new Task("Пообедать","Сьесть "+'"'+"Том-Ям"+'"',TaskStatus.IN_PROGRESS);
        upTask2.setIdTask(task2.getIdTask());//Установить инд. обновляемого задания
        taskManager.updateTask(upTask2); //обновить
        System.out.println(taskManager.getTask(2));

        System.out.println();
        System.out.println("Обновленная задача эпик - " + epic1.getTitleTask()+" и подзадачу - "
                + subtask41.getTitleTask());
        Subtask upSubtask41 =new Subtask(
                "Накрыть кровлю","Крыть кровлю под 56 градусов",idEpic1,TaskStatus.IN_PROGRESS);
        upSubtask41.setIdTask(subtask41.getIdTask());//Установить инд. обновляемого задания
        taskManager.updateTask(upSubtask41);//обновить
        System.out.println(taskManager.getTask(epic1.getIdTask()));
        System.out.println(taskManager.getTask(upSubtask41.getIdTask()));

        System.out.println();
        System.out.println("Обновить только эпик - "+epic1.getTitleTask());
        Epic upEpic1=new Epic("Построить современный замок",
                "Замок должен быть 3 этажный, на 5000 квадратных метров, у моря",
                TaskStatus.NEW);
        upEpic1.setIdTask(epic1.getIdTask());//Установить инд. обновляемого задания
        upEpic1.setSubtasks(epic1.getSubtasks());//добавит старый список подзадач в обновленный эпик
        taskManager.updateTask(upEpic1);//обновить
        System.out.println(taskManager.getTask(epic1.getIdTask()));

        System.out.println();
        System.out.println("Обновленная задача эпик - " + epic2.getTitleTask()+" и подзадачу - "
                + subtask12.getTitleTask());
        Subtask upSubtask12 =new Subtask("Подз. 1 эп. 2","подз.1 эп. 2 тест",
                idEpic2,TaskStatus.DONE);
        upSubtask12.setIdTask(subtask12.getIdTask());//Установить инд. обновляемого задания
        taskManager.updateTask(upSubtask12);//обновить
        System.out.println(taskManager.getTask(epic2.getIdTask()));
        System.out.println(taskManager.getTask(upSubtask12.getIdTask()));
        System.out.println("Обновленная задача эпик - " + epic2.getTitleTask()+" и подзадачу - "
                + subtask22.getTitleTask());
        Subtask upSubtask22 =new Subtask("Подз. 2 эп. 2","подз.2 эп. 2 тест",
                idEpic2,TaskStatus.DONE);
        upSubtask22.setIdTask(subtask22.getIdTask());//Установить инд. обновляемого задания
        taskManager.updateTask(upSubtask22);//обновить
        System.out.println(taskManager.getTask(epic2.getIdTask()));
        System.out.println(taskManager.getTask(upSubtask22.getIdTask()));

        System.out.println();
        System.out.println("Получение списка задач Task = "+taskManager.getTasks());
        System.out.println("Получение списка задач Epic = "+taskManager.getTasksEpic());
        System.out.println("Получение списка задач Subtask = "+taskManager.getSubtasks());

        System.out.println();
        System.out.println("Получение списка задач Epic1 = "+taskManager.getSubtaksEpic(epic1));

        System.out.println();
        System.out.println("Удаление task1 = "+task1.getTitleTask());
        taskManager.deleteTaskById(task1.getIdTask());
        System.out.println("Получение списка задач Task = "+taskManager.getTasks());

        System.out.println();
        System.out.println("Удаление epic2 = "+epic2.getTitleTask());
        taskManager.deleteTaskById(epic2.getIdTask());
        System.out.println("Получение списка задач Epic = "+taskManager.getTasksEpic());
        System.out.println("Получение списка задач Subtask = "+taskManager.getSubtasks());

        System.out.println();
        System.out.println("Удаление subtask41 = "+subtask41.getTitleTask());
        taskManager.deleteTaskById(subtask41.getIdTask());
        System.out.println("Получение списка задач Subtask = "+taskManager.getSubtasks());
        System.out.println("Получение списка задач Epic = "+taskManager.getTasksEpic());

        System.out.println();
        System.out.println("Удаление всех задач Task");
        taskManager.deleteTasks();
        System.out.println("Получение списка задач Task = "+taskManager.getTasks());

        System.out.println();
        System.out.println("Удаление всех задач Subtask");
        taskManager.deleteSubtasks();
        System.out.println("Получение списка задач Subtask = "+taskManager.getSubtasks());

        System.out.println();
        System.out.println("Удаление всех задач Epic");
        taskManager.deleteEpics();
        System.out.println("Получение списка задач Epic = "+taskManager.getTasksEpic());
    }

}