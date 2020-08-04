package demo.meetakka.createactor;

import lombok.Value;

@Value
public class Greet implements GreetingMessage {
    String name;
    String message;

}
