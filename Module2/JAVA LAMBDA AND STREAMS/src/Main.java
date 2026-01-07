import java.util.List;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Old way

//        Walkable obj = new fastWalk();
//        obj.walk(2);

        // LAMBDA WAY

//        Walkable obj = (steps) ->{
//            System.out.println("Walking fast");
//            return 2*steps;
//        };
//        obj.walk(4);
//
//        }

//        interface  Walkable{
//            int walk(int steps);
//        }
        //OLD WAY OF DEFINING THE INTERFACE

        //        class fastWalk implements Walkable{

        //            @Override
        //            public int walk(int steps) {
        //                System.out.println("Walking fast");
        //                return 2*steps;
        //
        //            }
        //        }
        List<String> fruits = List.of("Apple", "Banana", "Kiwi");
        Stream<String> stream = fruits.stream();
        stream.forEach((fruit) -> {
                    System.out.println(fruit);
                }

        );

    }
}

