import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Produtor {
    private final static String NOME_FILA = "hello";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("mqadmin");
        factory.setPassword("Admin123XX_");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){

            channel.queueDeclare(NOME_FILA, false, false, false, null);
            String mensagem = "Hello Guilherme!";
            channel.basicPublish("", NOME_FILA, null, mensagem.getBytes());
            System.out.println("Enviou: " + mensagem);
        }
    }
}


