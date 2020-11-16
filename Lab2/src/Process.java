<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Random;

public class Process {
    private int necessaryTime;
    private int pID;
    private int value;
    private int sumValue=0;
    private int sumTime=0;
    private ArrayList<Thread> arrThread =  new ArrayList<>();
    Random rand = new Random();

    public Thread getThreadFromId(int index) {
        return arrThread.get(index);
    }

    public void removeThreadFromIndex(int index) { arrThread.remove(index); }

    public int getSizeThread() {
        return arrThread.size();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getpID() {
        return pID;
    }

    public int getSumTime() {
        return sumTime;
    }

    public int getSumValue() {
        return sumValue;
    }

    public Process(int pID, int value) {
        this.pID=pID;
        this.value=value;
    }

    public void createThread() {
        for (int i = 0; i <1+rand.nextInt(10) ; i++) {
            int value=1+rand.nextInt(5);
            int necTime=1+rand.nextInt(10);
            arrThread.add(new Thread(i,value,necTime));
            sumValue+=value;
        }
    }

    public void start() {
        createThread();
    }
}
=======
import java.util.ArrayList;
import java.util.Random;

public class Process {
    private int necessaryTime;
    private int pID;
    private int value;
    private int sumValue=0;
    private int sumTime=0;
    private ArrayList<Thread> arrThread =  new ArrayList<>();
    Random rand = new Random();

    public Thread getThreadFromId(int index) {
        return arrThread.get(index);
    }

    public void removeThreadFromIndex(int index) { arrThread.remove(index); }

    public int getSizeThread() {
        return arrThread.size();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getpID() {
        return pID;
    }

    public int getSumTime() {
        return sumTime;
    }

    public int getSumValue() {
        return sumValue;
    }

    public Process(int pID, int value) {
        this.pID=pID;
        this.value=value;
    }

    public void createThread() {
        for (int i = 0; i <1+rand.nextInt(10) ; i++) {
            int value=1+rand.nextInt(5);
            int necTime=1+rand.nextInt(10);
            arrThread.add(new Thread(i,value,necTime));
            sumValue+=value;
        }
    }

    public void start() {
        createThread();
    }
}
>>>>>>> 19c3e0faca93c68d69f2706f6b351c54f91046f7
//ядро сразу планирует потоки, а не процессы