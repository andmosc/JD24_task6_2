import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Найти количество несовершеннолетних (т.е. людей младше 18 лет).
 * Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
 * Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
 */
public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons;

        persons = personsAdd(names, families, 100);

        Long minors = numberMinors(persons);
        int averageAge = numberAverageAge(persons);

        List<String> conscript = listConscriptSurname(persons);
        List<Person> workable = listWorkable(persons);

        List<Person> women = listWorkableWomen(persons);
        List<Person> man = listWorkableMan(persons);

        System.out.println("minors: " + minors);
        System.out.println("averageAge: " + averageAge);

        System.out.println("***conscript***");
        conscript.forEach(System.out::println);
        System.out.println("***workable***");
        workable.forEach(System.out::println);

        System.out.println("***workable2***");
        List<Person> newList = Stream.concat(women.stream(),man.stream()).collect(Collectors.toList());
        newList.forEach(System.out::println);

    }

    static List<Person> personsAdd(List<String> names, List<String> families, int number) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        return persons;
    }

    public static Long numberMinors(List<Person> persons) {
        return persons.stream().filter(p -> p.getAge() <= 18).count();
    }

    public static int numberAverageAge(List<Person> persons) {
        return (int) persons.stream().mapToInt(Person::getAge).average().getAsDouble();
    }

    public static List<String> listConscriptSurname(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .map(Person::getSurname).collect(Collectors.toList());
    }

    public static List<Person> listWorkable(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p->p.getAge() >= 18)
                .filter(p-> p.getSex() == Sex.WOMAN && p.getAge() <55
                        || (p.getSex() == Sex.MAN && p.getAge() < 60))
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }

    public static List<Person> listWorkableWomen(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p->p.getAge() >= 18)
                .filter(p-> p.getSex() == Sex.WOMAN && p.getAge() <55)
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }
    public static List<Person> listWorkableMan(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p->p.getAge() >= 18)
                .filter(p->p.getSex() == Sex.MAN && p.getAge() < 60)
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }
}
