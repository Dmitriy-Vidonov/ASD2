package ex_3.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Various_Methods_Test {

    int depth = 3;
    @Test
    @DisplayName("1) Проверка расчета размера массива")
    void getSize() throws Exception {
        aBST treeTest = new aBST(depth);
        System.out.println("Рассчитаный размер массива - " + treeTest.getSize(depth));
    }

    @Test
    @DisplayName("2) Проверка заполнения массива")
    void arrayFilling() throws Exception {
        aBST treeTest = new aBST(depth);
        System.out.println("Реальный размер массива - " + treeTest.Tree.length);
        // Выведем для теста содержимое массива на экран
        treeTest.ShowArray();
        // Добавим корень
        treeTest.AddKey(100);
        treeTest.AddKey(50);
        treeTest.AddKey(150);
        treeTest.AddKey(25);
        treeTest.AddKey(200);
        treeTest.AddKey(75);
        System.out.println("индекс 125 = " + treeTest.AddKey(125));
        System.out.println("-1 если не удалось " + treeTest.AddKey(250));
        // Выведем снова
        treeTest.ShowArray();
    }
}