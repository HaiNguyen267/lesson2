package ex3_collection;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> list1 = List.of("a", "b", "c", "d", "e");
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list2.add(i);
        }

        // remove all even numbers from list2
        Iterator<Integer> iter = list2.iterator();
        while (iter.hasNext()) {
            int num = iter.next();
            if (num % 2 == 0) {
                iter.remove();
            }
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(1);
        stack.offerFirst(2);
        stack.offerFirst(3);
        stack.offerFirst(4);
        System.out.println(stack); // [4, 3, 2, 1]

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(1);
        queue.offerLast(2);
        queue.offerLast(3);
        queue.offerLast(4);
        System.out.println(queue); // [1, 2, 3, 4]


        Set<String> set = new HashSet<>(List.of("Hai", "Huy", "Hiep", "Hoa"));
        if (set.contains("Hoa")) {
            System.out.println("Hoa is in the set");
        }

        if (!set.contains("Hoang")) {
            System.out.println("Hoang is not in the set");
        }

        Map<String, String> map = new HashMap<>();
        map.put("One", "Một");
        map.put("Two", "Hai");
        map.put("Three", "Ba");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String numberEnglish = entry.getKey();
            String numberVietnamese = entry.getValue();
            System.out.println(numberEnglish + " = " + numberVietnamese);
        }
    }
}
