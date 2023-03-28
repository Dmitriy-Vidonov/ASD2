package recursion_learning;

public class testRecursion {
    public static void main(String[] args) {
        System.out.println(count(1)); // 100 выведет на экран
        System.out.println(fact(5));
        matryoshka(7);
    }

    public static int count(int a) {
        if(a == 100) { // это условие выхода из метода
            return a; // вернем a как только она станет равна 100
        }
        // если а != 100
        a++; // a равно 100? нет? тогда прибавим 1
        return count(a); // еще раз вызовем метод, но передаем измененный аоооооо
    }

    public static int fact(int n) {
        if(n == 1) return 1;
        return n * fact(n-1);
    }

    public static void matryoshka (int n) { // n - уровень вложенности матрешки
        // внутри рекурсивного метода должно быть ветвление, когда рекурсия не наступает. Как та мышка в сказке про репку
        if(n == 1)
            System.out.println("Last matryoshka!!!");
        else { // представим, что свершим матрешку сверху насквозь и смотрим на срез - видим сначала верхи, потом низушечки
            System.out.println("Top side of matryoshka - " + n); // верх матрешки каждого уровня вложенности
            matryoshka(n-1); // метод может вызвать сам себя. это не мешает стеку хранить нужные адреса. на разных этапах рекурсии n разный
            System.out.println("Bottom side of matryoshka - " + n); // низ матрешки каждого уровня вложенности
        }
    }
}
