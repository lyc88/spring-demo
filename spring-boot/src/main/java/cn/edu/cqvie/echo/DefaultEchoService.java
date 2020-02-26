package cn.edu.cqvie.echo;

public class DefaultEchoService implements EchoService {

    private String echoFormart;

    public DefaultEchoService(String echoFormart) {
        this.echoFormart = (null != echoFormart) ? echoFormart : "%s";
    }

    @Override
    public String getMessage(String message) {
        return String.format(this.echoFormart, message);
    }
}
