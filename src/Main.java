import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    final static int population = 10000000;
    final static int majorityAge = 18;
    final static int nonConscriptAge = 27;
    final static int WomanRetirementAge = 60;
    final static int ManRetirementAge = 65;
    final static int longevity = 100;

    public static void main(String[] args){
        List<String> names = Arrays.asList("Paul", "John",
                "Jim", "Clara", "Max",
                "Alex", "Emily",
                "Michal", "Luiza");
        List<String> families = Arrays.asList("Smith",
                "Evans", "Cohen", "Edisson",
                "Jonson", "Hamilton",
                "Wilson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i <= population; i++){
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(longevity),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //getting number of minors
        long count = persons.stream()
                .filter(x -> x.getAge() < majorityAge)
                .count();
        System.out.println("\nthe number of minors: " + new DecimalFormat( "##,###,###" ).format(count));

        //list of conscripts' families
        List <String> conscripts = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getAge() < nonConscriptAge)
                .filter(x -> x.getAge() >= majorityAge)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("\nthe families' list 'Conscripts' is formed. Number of conscripts: " +
                new DecimalFormat( "##,###,###" ).format(conscripts.size()));
        System.out.println("for instance the fourth conscript family: " + conscripts.get(3).toString() + "\n");

        //forming a workforce list
        List<Person> workforce = new ArrayList<>();
        workforce = persons.stream()
                .filter(x -> x.getAge() >= majorityAge)
                .filter(x -> (x.getSex().equals(Sex.WOMAN) && x.getAge() < WomanRetirementAge ||
                        x.getSex().equals(Sex.MAN) && x.getAge() < ManRetirementAge))
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        //random checking the list
        System.out.println("The Workforce List is formed and sorted, a ramdom check please see below: ");
        System.out.println("the first person in the list: " + workforce.get(0).toString());
        System.out.println("the second person in the list: " + workforce.get(1).toString());
        System.out.println("the hundredth person in the list: " + workforce.get(100).toString());
        System.out.println("the thousandth person in the list: " + workforce.get(1000).toString());
    }
}

