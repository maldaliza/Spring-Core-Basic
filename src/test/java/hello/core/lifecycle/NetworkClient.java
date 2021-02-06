package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    // 생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    // url을 외부에서 setter로 주입
    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // 의존관계 주입이 끝나면 호출하겠다.
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈이 종료될 때 호출된다.
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
