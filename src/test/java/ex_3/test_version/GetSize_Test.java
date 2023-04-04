package ex_3.test_version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetSize_Test {

    @Test
    @DisplayName("1) Проверка размера")
    void getSize() throws Exception {
        int depth = 4;
        aBST treeTest = new aBST(depth);
        System.out.println(treeTest.getSize(depth));
    }
}