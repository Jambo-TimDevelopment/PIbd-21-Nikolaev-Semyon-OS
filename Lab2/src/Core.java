<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Random;

public class Core {
    private ArrayList<Process> arrProcess = new ArrayList<>();
    Random rnd = new Random();
    private int sumValue = 0;

    public void createProcess() {
        int countProcess = 3 + rnd.nextInt(10);
        for (int i = 0; i < countProcess; i++) {
            int value = 1;
            arrProcess.add(new Process(i, value));
            arrProcess.get(i).start();
            value = arrProcess.get(i).getSumValue();
            arrProcess.get(i).setValue(value);
            sumValue += value;
        }
    }

    public void planProcess() {
        while (!arrProcess.isEmpty()) {
            int lotteryResult = rnd.nextInt(sumValue);
            for (int i = 0; i < arrProcess.size(); i++) {
                if (lotteryResult - arrProcess.get(i).getValue() > 0) {
                    lotteryResult -= arrProcess.get(i).getValue();
                } else {
                    //int valueThr=0;
                    for (int j = 0; j < arrProcess.get(i).getSizeThread(); j++) {
                        if (lotteryResult - arrProcess.get(i).getThreadFromId(j).getValue() > 0) {
                            lotteryResult -= arrProcess.get(i).getThreadFromId(j).getValue();
                        } else {
                            int valueThr = arrProcess.get(i).getThreadFromId(j).getValue();
                            System.out.println("поток " + arrProcess.get(i).getThreadFromId(j).gettID() + " процесса " + arrProcess.get(i).getpID() + " начал выполнение");
                            arrProcess.get(i).getThreadFromId(j).working();
                            arrProcess.get(i).removeThreadFromIndex(j);
                            arrProcess.get(i).setValue(arrProcess.get(i).getValue() - valueThr);
                            sumValue -= valueThr;
                            if (arrProcess.get(i).getValue() == 0) {
                                System.out.println("процесс " + arrProcess.get(i).getpID() + " выполнен успешно");
                                System.out.println();
                                arrProcess.remove(i);
                                break;
                            }
                        }
                    }
                    //выбираем нужный нам поток и обрабатываем его
                    //уменьшаем ценность процесса и вырезаем поток из процесса
                }
            }
        }
    }

    public void startProgram() {
        createProcess();
        planProcess();
    }
}
=======
import java.util.ArrayList;
import java.util.Random;

public class Core {
    private ArrayList<Process> arrProcess = new ArrayList<>();
    Random rnd = new Random();
    private int sumValue = 0;

    public void createProcess() {
        int countProcess = 3 + rnd.nextInt(10);
        for (int i = 0; i < countProcess; i++) {
            int value = 1;
            arrProcess.add(new Process(i, value));
            arrProcess.get(i).start();
            value = arrProcess.get(i).getSumValue();
            arrProcess.get(i).setValue(value);
            sumValue += value;
        }
    }

    public void planProcess() {
        while (!arrProcess.isEmpty()) {
            int lotteryResult = rnd.nextInt(sumValue);
            for (int i = 0; i < arrProcess.size(); i++) {
                if (lotteryResult - arrProcess.get(i).getValue() > 0) {
                    lotteryResult -= arrProcess.get(i).getValue();
                } else {
                    //int valueThr=0;
                    for (int j = 0; j < arrProcess.get(i).getSizeThread(); j++) {
                        if (lotteryResult - arrProcess.get(i).getThreadFromId(j).getValue() > 0) {
                            lotteryResult -= arrProcess.get(i).getThreadFromId(j).getValue();
                        } else {
                            int valueThr = arrProcess.get(i).getThreadFromId(j).getValue();
                            System.out.println("поток " + arrProcess.get(i).getThreadFromId(j).gettID() + " процесса " + arrProcess.get(i).getpID() + " начал выполнение");
                            arrProcess.get(i).getThreadFromId(j).working();
                            arrProcess.get(i).removeThreadFromIndex(j);
                            arrProcess.get(i).setValue(arrProcess.get(i).getValue() - valueThr);
                            sumValue -= valueThr;
                            if (arrProcess.get(i).getValue() == 0) {
                                System.out.println("процесс " + arrProcess.get(i).getpID() + " выполнен успешно");
                                System.out.println();
                                arrProcess.remove(i);
                                break;
                            }
                        }
                    }
                    //выбираем нужный нам поток и обрабатываем его
                    //уменьшаем ценность процесса и вырезаем поток из процесса
                }
            }
        }
    }

    public void startProgram() {
        createProcess();
        planProcess();
    }
}
>>>>>>> 19c3e0faca93c68d69f2706f6b351c54f91046f7
//указываем начало выполнение процесса, заканчиваем выполнение, только когда кончатся все объекты массива процессов