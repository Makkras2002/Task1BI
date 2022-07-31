package service;

import dao.DataDao;
import dao.impl.CustomDataDao;
import entity.Data;
import exception.DaoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileService {
    private static FileService instance;
    private DataDao dataDao;
    private FileService(){
        dataDao = new CustomDataDao();
    }
    public static FileService getInstance() {
        if(instance == null) {
            instance = new FileService();
        }
        return instance;
    }
    public void mergeFiles(String filesDir,String outputFileName){
        try {
            Files.deleteIfExists(Path.of(outputFileName));
            Files.list(Paths.get(filesDir)).flatMap(path -> {
                try {
                    return Files.lines(path);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            })
                    .forEach(line -> {
                        try {
                            Files.writeString(Path.of(outputFileName), line + System.lineSeparator(), APPEND, CREATE);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public Integer mergeFiles(String filesDir,String outputFileName, String delSymbols){
        AtomicInteger deletedLines = new AtomicInteger();
        deletedLines.set(0);
        try {
            Files.deleteIfExists(Path.of(outputFileName));
            Files.list(Paths.get(filesDir)).flatMap(path -> {
                try {
                    return Files.lines(path);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            })
                    .forEach(line -> {
                        if(!line.contains(delSymbols)) {
                            try {
                                Files.writeString(Path.of(outputFileName), line + System.lineSeparator(), APPEND, CREATE);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            deletedLines.incrementAndGet();
                        }

                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return deletedLines.get();
    }
    public void saveDataFromFileToDb(String sourceFile) {
        AtomicInteger importedLines = new AtomicInteger();
        AtomicInteger leftLines = new AtomicInteger();
        importedLines.set(1);
        try {
            leftLines.set(Files.lines(Paths.get(sourceFile)).toArray().length);
            Files.lines(Paths.get(sourceFile)).forEach(line -> {
                        String[] dataFromLine = line.split("\\|\\|");
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        Data data = new Data(LocalDate.parse(dataFromLine[0],dateFormatter),dataFromLine[1],dataFromLine[2],
                                Integer.parseInt(dataFromLine[3]),Double.parseDouble(dataFromLine[4].replace(",",".")));
                try {
                    dataDao.create(data);
                    leftLines.decrementAndGet();
                    System.out.println("[Импортировано строк - > "+ importedLines.get() +"||"+" Осталось строк - > "+ leftLines.get()+"]");
                    importedLines.incrementAndGet();
                } catch (DaoException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getSumAndMedFromDb() {
        try {
            System.out.println(dataDao.selectSumAndMedian());
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }
}
