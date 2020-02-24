import com.google.common.collect.Lists;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import rx.Observable;
import rx.functions.Action1;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * author yg
 * description 模拟客户端负载均衡请求
 * date 2020/2/24
 */
public class BalanceTest {
    public static void main(String[] args) {
        List<Server> list = Lists.newArrayList(new Server("localhost", 8081), new Server("localhost", 8082));
        BaseLoadBalancer baseLoadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(list);
        for (int i = 0; i < 5; i++) {
            LoadBalancerCommand.<String>builder().withLoadBalancer(baseLoadBalancer).build().submit(server -> {
                InputStream inputStream = null;
                try {
                    String addr1 = "http://" + server.getHost() + ":" + server.getPort() + "/user/hello";
                    System.out.println("addr:" + addr1);
                    URL url = new URL(addr1);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    inputStream = urlConnection.getInputStream();
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return Observable.just(new String(bytes));
                } catch (Exception e) {
                    e.printStackTrace();
                    return Observable.error(e);
                }
            }).toBlocking().forEach(new Action1<String>() {
                @Override
                public void call(String s) {
                    System.err.println("result:" + s);
                }
            });
        }
    }
}
