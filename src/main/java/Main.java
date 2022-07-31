import generator.FileGeneretor;
import service.FileService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileGeneretor.getInstance().generate100Files("C:\\100Files");
        Scanner scanner = new Scanner(System.in);
        FileService fileService = FileService.getInstance();
        while (true) {
            System.out.println("1. Реализовать объединение файлов в один. При объединении должна быть возможность\n" +
                    "удалить из всех файлов строки с заданным сочетанием символов, например, «abc» с выводом\n" +
                    "информации о количестве удаленных строк;");
            System.out.println("2. Создать процедуру импорта файлов с таким набором полей в таблицу в СУБД. При импорте\n" +
                    "должен выводится ход процесса (сколько строк импортировано, сколько осталось);");
            System.out.println("3. Реализовать хранимую процедуру в БД (или скрипт с внешним sql-запросом), который считает\n" +
                    "сумму всех целых чисел и медиану всех дробных чисел;");
            System.out.println("4. Выйти.");
            System.out.println("");
            System.out.println("Введите команду: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Удалить строки содержащие последовательность символов(1- Да, 2- Нет)? ");
                    Integer del = scanner.nextInt();
                    if(del == 1) {
                        System.out.println("Введите последовательность символов: ");
                        String detSymbols = scanner.next();
                        System.out.println(fileService.mergeFiles("C:\\100Files","C:\\100Files\\MergedFiles\\MergedFile.txt",detSymbols) +
                                " строк удалено.");
                    } else if(del == 2) {
                        fileService.mergeFiles("C:\\100Files","C:\\100Files\\MergedFiles\\MergedFile.txt");
                    }else {
                        System.out.println("Неверный ввод данных...");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Введите путь у файлу с данными: ");
                    String filePath = scanner.next();
                    fileService.saveDataFromFileToDb(filePath);
                    break;
                }
                case 3: {
                    fileService.getSumAndMedFromDb();
                    break;
                }
                case 4: {
                    return;
                }
            }
        }
    }
}
