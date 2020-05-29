import schedule.Schedule;

public class Main {

    public static void main(String[] args) {
        if(args.length!=1)
        {
            System.out.println ("erreur dans le nombre d'arguments");
        }
        Schedule schedule=new Schedule("ressource/"+args[0]);
        schedule.Observer();
    }

}
