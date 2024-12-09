import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> result = new ArrayList<>();
        for (int i : intList) {
            if (i > 0 && i % 2 == 0) {
                result.add(i);
            }
        }
        result.sort(Comparator.naturalOrder());
        for (int i : result) {
            System.out.println(i);
        }


        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream().filter(person -> person.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних: " + count);

        List<String> listFamilies = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN)&& person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person ::getFamily).collect(Collectors.toList());
        System.out.println("Список призывников: " + listFamilies);

        List<Person> listWorking = persons.stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge() < 65)
                        || (person.getSex().equals(Sex.WOMAN)) && person.getAge() < 60)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Отсортированный список работоспособных людей: " + listWorking);
    }
}