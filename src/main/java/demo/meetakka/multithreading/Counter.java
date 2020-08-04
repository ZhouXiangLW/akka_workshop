package demo.meetakka.multithreading;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Counter {

    private int count = 0;

    public void add() {
        count++;
    }

    public void sub() {
        count--;
    }

}
