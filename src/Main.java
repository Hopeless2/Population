
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long countOfMinors = persons.stream()
                .filter(minor -> minor.getAge() < 18)
                .count();
        System.out.println(countOfMinors);

        List<String> recruitsFamily = persons.stream()
                .filter(recruit -> recruit.getAge() >= 18 && recruit.getAge() <= 27)
                .map(recruit -> recruit.getFamily())
                .collect(Collectors.toList());
        System.out.println(recruitsFamily);

        List<Person> canWork = persons.stream()
                .filter(worker -> (worker.getSex() == Sex.MAN &&
                        worker.getAge() >= 18 &&
                        worker.getAge() <= 65 &&
                        worker.getEducation() == Education.HIGHER) ||
                        (worker.getSex() == Sex.WOMAN &&
                                worker.getAge() >= 18 &&
                                worker.getAge() <= 60 &&
                                worker.getEducation() == Education.HIGHER)
                )
                .sorted(Comparator.comparing(worker -> worker.getFamily()))
                .collect(Collectors.toList());
        System.out.println(canWork);
    }
}
