package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "two", "three", "four", "five");
        assertThat(list).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(6);
                    assertThat(e.length()).isGreaterThan(2);
                })
                .anySatisfy(e -> {
                    assertThat(e.length()).isEqualTo(3);
                    assertThat(e.length()).isGreaterThan(1);
                });
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("a", "b", "c", "d", "e");
        assertThat(set).first().isEqualTo("a");
        assertThat(set).element(0).isNotNull().isEqualTo("a");
        assertThat(set).last().isNotNull().isEqualTo("e");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("a", "b", "c", "d", "e");
        assertThat(map).hasSize(5)
                .containsKeys("a", "b", "c", "e")
                .containsValues(3, 1, 4)
                .doesNotContainKey("f")
                .doesNotContainValue(8)
                .containsEntry("a", 0);
    }
}