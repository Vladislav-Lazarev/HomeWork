import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Session;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Thread.sleep;

public class Task1 {
    public static void main(String[] args) {
        try {
            Connection connect = new ConnectionImpl();
            Session session = connect.createSession(true);
            DestinationImpl destination = new DestinationImpl("MessageTask1");
            ProducerImpl producer =  new ProducerImpl(destination);

            List<String> list = new ArrayList<>();
            list.add("Раз");
            list.add("Два");
            list.add("Три");
            for (String s : list) {
                producer.send(s);
                sleep(2000);
            }
            session.close();
            connect.close();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}