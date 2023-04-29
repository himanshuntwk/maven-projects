import com.himanshu.RedisMain;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class MainTest {

    private static int PORT = 6379;
    @Container
    private static final GenericContainer redisContainer = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
            .withExposedPorts(PORT);

    @BeforeClass
    public static void before() {
        redisContainer.start();
    }

    @AfterClass
    public static void after() {
        redisContainer.stop();
    }

    @Test
    public void testMethod() {
        String containerHost = redisContainer.getHost();
        int containerPort = redisContainer.getMappedPort(PORT);
        RedisMain main = new RedisMain(containerHost, containerPort);
        Assert.assertTrue(main.redisDemo().equalsIgnoreCase("Himanshu"));
    }

}
