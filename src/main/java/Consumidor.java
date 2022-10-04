import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


public class Consumidor {
    private final static String NOME_FILA = "hello";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("mqadmin");
        factory.setPassword("Admin123XX_");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(NOME_FILA, false, false, false, null);
        System.out.println("Aguardando mensagens...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody(), "UTF-8");
            System.out.println("Recebeu: " + mensagem);
        };

        channel.basicConsume(NOME_FILA, true, deliverCallback, consumerTag -> { });
    }
}


