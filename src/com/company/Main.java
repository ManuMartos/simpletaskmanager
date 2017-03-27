package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> taskList = new ArrayList<String>();

        int option;

        taskList.add("Pescar monedas");
        taskList.add("Cocinarlas");
        taskList.add("Limpiar");

        while ( (option = showMenu(taskList) ) !=0){

            switch (option){
                case 1:
                    //Ver tareas
                    showTasks(taskList);
                    System.out.println();
                    break;
                case 2:
                    addTask(taskList);
                    //Añadir tareas
                    break;
                case 3:
                    if (taskList.size() > 0) {
                        deleteTask(taskList);
                    }
                    //Eliminar tareas
                    break;
                case 4:
                    if (taskList.size() > 1) {
                        editTask(taskList);
                    }
                    break;
                case 5:
                    moveTask(taskList);
                default:
            }
        }
    }

    public static void moveTask(ArrayList<String> myTasks){

        Scanner input = new Scanner(System.in);

        int fromIndexTask, toIndexTask;

        String editedTask;

        showTasks(myTasks);

        //Leer el indice de la tarea a mover
        do {
            System.out.println("Introduzca el indice de la tarea para mover");
            fromIndexTask = input.nextInt();
        }while ( !correctIndex(fromIndexTask, myTasks));

        //Leer la posiscion a mover
        do {
            input.nextLine();
            System.out.println("Introducir la nueva posicion");
            toIndexTask = input.nextInt();
        }while (!correctIndex(toIndexTask, myTasks));

        myTasks.add(toIndexTask, myTasks.get(fromIndexTask));
        myTasks.remove(fromIndexTask + 1);
    }

    public static void editTask(ArrayList<String> myTasks){
        Scanner input = new Scanner(System.in);

        int indextask;

        String editedTask;

        showTasks(myTasks);

        //Leer el indice de la tarea a modficar
        do {
            System.out.println("Introduzca el indice de la tarea a editar");
            indextask = input.nextInt();
        }while ( !correctIndex(indextask, myTasks));

        //Leer la nueva tarea
        do {
            input.nextLine();
            System.out.println("Introducir la nueva tarea");
            editedTask = input.nextLine().trim().replaceAll("\\s+"," ");
        }while (editedTask.length()== 0 || myTasks.contains(editedTask));

        myTasks.set( indextask, editedTask);
    }

    public static void deleteTask(ArrayList<String> myTasks){
        Scanner input = new Scanner(System.in);
        int index;

        do {
            System.out.println("Introducir el indice:");
            index = input.nextInt();
        }while ( !correctIndex(index, myTasks) );

        myTasks.remove(index);

    }

    public static boolean correctIndex(int index, ArrayList<String> myTasks){

        if (index >= 0 && index < myTasks.size() ) {
            return true;
        }else {
            return false;
        }
    }

    public static void addTask(ArrayList<String> myTasks){
        Scanner input = new Scanner(System.in);
        String task;

        do {
            System.out.println("Introducir nueva tarea");
            task = input.nextLine().trim().replaceAll("\\s+"," ");

            if (task.length() <= 0){
                System.out.println("No se puede añadir espacios en blanco.");
            }
        }while (task.length() == 0);

        myTasks.add(task);
    }

    public static void showTasks(ArrayList<String> myTasks){

        int index = 0;
        for (String task: myTasks) {
            System.out.println((index++) + " - " + task);
        }
    }

    public static int showMenu(ArrayList<String> myTasks){
        Scanner input = new Scanner(System.in);

        ArrayList<Integer> options = new ArrayList<Integer>();
        int option;

        System.out.println("************************");
        System.out.println("* 1 - Ver tareas       *");
        options.add(1);
        System.out.println("* 2 - Añadir tareas    *");
        options.add(2);

        if (myTasks.size() > 0){
            System.out.println("* 3 - Eliminar tareas  *");
            options.add(3);
            System.out.println("* 4 - Editar tarea     *");
            options.add(4);
        }

        if (myTasks.size() > 1) {
            System.out.println("* 5 - Mover tarea      *");
            options.add(5);
        }

        System.out.println("* 0 - Salir            *");
        System.out.println("************************");


        do {
            System.out.println("Opcion: ");
            option = input.nextInt();
        }while ( option < 0 || option > options.size());


        return option;

    }
}
