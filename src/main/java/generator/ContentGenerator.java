package generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class ContentGenerator {
    private static ContentGenerator instance;
    private ContentGenerator() {
    }
    public static ContentGenerator getInstance() {
        if(instance == null) {
            instance = new ContentGenerator();
        }
        return instance;
    }
    public void generateContentForFile(BufferedWriter bufferedWriter) throws IOException {
        LocalDate start = LocalDate.of(2017, Month.JANUARY, 1);
        LocalDate end = LocalDate.now();
        int counter = 0;
        while (counter < 100000) {
            LocalDate randomDate = between(start, end);
            String randomLatinCharacters = RandomStringUtils.randomAlphabetic(10);
            String randomRussianCharacters = CyrrilicStringGeneretor.getInstance().generateRandomCyrrillicString(10);
            int minForInt = 1;
            int maxForInt = 100000000;
            Integer randomNumberInt = (int) Math.floor(Math.random()*(maxForInt-minForInt+1)+minForInt);
            if(randomNumberInt%2 != 0) {
                randomNumberInt+=1;
            }
            double minForDouble = 1.00000000;
            double maxForDouble = 20.00000000;
            Double randomNumberDouble = ThreadLocalRandom.current().nextDouble(minForDouble, maxForDouble);;
            DecimalFormat df = new DecimalFormat("###.########");
            bufferedWriter.flush();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            bufferedWriter.write(randomDate.format(dateFormatter).toString() + "||" +randomLatinCharacters+"||"+randomRussianCharacters+
                    "||"+randomNumberInt+"||"+df.format(randomNumberDouble)+"||");
            bufferedWriter.newLine();
            counter++;
        }
    }
    private LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}
