package levels;

import models.Teacher;
import utils.Data;

import java.util.*;
import java.util.stream.Collectors;

public class Level3 {

    public static void main(String[] args) {
        List<Teacher> teachers = Data.employees();

        /* TO DO 1: Retourner une chaine de caractère qui contient tous les noms des enseignants en majuscule séparés par # */
        String names = teachers.stream()
                .map(teacher -> teacher.getName().toUpperCase())
                .reduce((name1, name2) -> name1 + "#" + name2)
                .orElse("");
        System.out.println("Noms des enseignants en majuscule séparés par #: " + names);

        /* TO DO 2: Retourner un set d'enseignants Java dont le salaire > 80000 */
        Set<Teacher> teachers1 = teachers.stream()
                .filter(teacher -> teacher.getSubject().equalsIgnoreCase("Java"))
                .filter(teacher -> teacher.getSalary() > 80000)
                .collect(Collectors.toSet());
        System.out.println("Enseignants Java avec un salaire > 80000: " + teachers1);

        /* TO DO 3: Retourner un TreeSet d'enseignants (tri par nom et en cas d'égalité tri par salaire) */
        TreeSet<Teacher> teachers2 = teachers.stream()
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(Teacher::getName)
                                .thenComparingDouble(Teacher::getSalary))));
        System.out.println("TreeSet trié par nom et salaire: " + teachers2);

        /* TO DO 4: Retourner une Map qui regroupe les enseignants par module */
        Map<String, List<Teacher>> map = new HashMap<>();
        for (Teacher teacher1 : teachers) {
            map.computeIfAbsent(String.valueOf(teacher1.getSubject()), k -> new ArrayList<>()).add(teacher1);
        }
        System.out.println("Enseignants regroupés par module: " + map);

        /* TO DO 5: Retourner une Map qui regroupe les noms des enseignants par salaire */
        Map<Integer, String> map1 = teachers.stream()
                .collect(Collectors.toMap(
                        teacher -> teacher.getSalary(),
                        teacher -> teacher.getName(),
                        (name1, name2) -> name1 + ", " + name2 // Gère les doublons de salaires
                ));
        System.out.println("Noms des enseignants regroupés par salaire: " + map1);

        /* TO DO 6: Afficher les noms des enseignants de chaque module */
        map.forEach((module, teacherList) -> {
            System.out.println("Module: " + module);
            teacherList.forEach(teacher -> System.out.println(" - " + teacher.getName()));
        });
    }
}
