<<<<<<< HEAD
public class Thread {
    private int tID;
    private int necessaryTime;
    private int value;

    public Thread (int tID,int value,int necessaryTime){
        this.tID=tID;
        this.value=value;
        this.necessaryTime=necessaryTime;
    }

    public int getValue() {
        return value;
    }

    public int gettID() {
        return tID;
    }

    public int getNecessaryTime() {
        return necessaryTime;
    }

    public void working() {
        System.out.println("поток "+ tID + " выполнился успешно за время "+ necessaryTime);
    }
}
=======
public class Thread {
    private int tID;
    private int necessaryTime;
    private int value;

    public Thread (int tID,int value,int necessaryTime){
        this.tID=tID;
        this.value=value;
        this.necessaryTime=necessaryTime;
    }

    public int getValue() {
        return value;
    }

    public int gettID() {
        return tID;
    }

    public int getNecessaryTime() {
        return necessaryTime;
    }

    public void working() {
        System.out.println("поток "+ tID + " выполнился успешно за время "+ necessaryTime);
    }
}
>>>>>>> 19c3e0faca93c68d69f2706f6b351c54f91046f7
