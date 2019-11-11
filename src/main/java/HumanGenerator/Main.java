package HumanGenerator;

import HumanGenerator.generators.HumanCreator;
import HumanGenerator.generators.localGenerator.Generator;
import HumanGenerator.generators.remoteApiGenerator.ApiReader;
import HumanGenerator.generators.remoteApiGenerator.JsonParser;
import HumanGenerator.model.Human;
import HumanGenerator.model.UserPojo;
import HumanGenerator.outCreators.ExcelCreator;
import HumanGenerator.outCreators.PdfCreator;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) {
        int countHumans = 0;
        if (args.length!=0 && !args[0].isEmpty()) {
            try {
                countHumans = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex){
                System.out.println("Вы ввели не число. Будет взято произвольное число пользователей");
                countHumans = Generator.getRand(InputParameters.MIN_COUNT_USERS, InputParameters.MAX_COUNT_USERS);
            }
        } else {
            System.out.println("Число пользователей не введено. Будет взято произвольное число");
            countHumans = Generator.getRand(InputParameters.MIN_COUNT_USERS, InputParameters.MAX_COUNT_USERS);
        }

        System.out.println("Запущен генератор " + countHumans + " пользователей...\nПожалуйста подождите...");
        Generator.initGlossary();
        ArrayList<Human> humans = new ArrayList<>();
       // humans = getHumansWithAPI(countHumans);

        System.out.println("Получено пользователей  " + (humans.size()));
        int countNotAddedHumans = countHumans - humans.size();
        for (int i = 0; i < countNotAddedHumans; i++) {
            humans.add(HumanCreator.getHuman());
        }
        System.out.println("Сгенерировано пользователей  " + (countNotAddedHumans));
        ExcelCreator.createExcelTable(humans, InputParameters.getListNamesColumn());

        try {
            PdfCreator.createPdfDocument(humans, InputParameters.getListNamesColumn());
        } catch (DocumentException | IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private static ArrayList<Human> getHumansWithAPI(int countHumans) {
        ArrayList<Human> humans = new ArrayList<>();
        for (int i = 0; i < countHumans; i++) {
            String response;
            try {
                response = ApiReader.get();
            } catch (IOException ex) {
                System.out.println(ex.toString());
                break;
            }
            UserPojo upj;
            try {
                upj = JsonParser.getHuman(response);
            } catch (IOException ex) {
                System.out.println(ex.toString());
                continue;
            }
            humans.add(HumanCreator.getHumanFromUserPojo(upj));
        }
        return humans;
    }


}
