import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Session;

import java.io.*;

import static java.lang.Thread.sleep;

public class Task2 {
    public static void main(String[] args){
        String fileName = args[0];
        try {
            Connection connection = new ConnectionImpl();
            Session session = connection.createSession(true);
            DestinationImpl destination = new DestinationImpl("messageTask2");
            ProducerImpl producer =  new ProducerImpl(destination);

            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            String message = (String) ois.readObject();
            boolean a = true;
            while (a) {
                producer.send(message);
                sleep(2000);
            }
            session.close();
            connection.close();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}