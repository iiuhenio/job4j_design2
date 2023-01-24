package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).contains("Tet")
                .isNotEqualTo("Sphere")
                .isEqualTo("Tetrahedron");

    }

    @Test
    void itIsNotCube() {
        Box box = new Box(20, 20);
        String name = box.whatsThis();
        assertThat(name).isNotEqualTo("Cube")
                .contains("Unknown object");
    }

    @Test
    void getNumberOfVerticesWhenEdge0() {
        Box box = new Box(4, 0);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(-1)
                .isGreaterThan(-5)
                .isNegative();
    }

    @Test
    void getNumberOfVerticesWhenVertex8() {
        Box box = new Box(8, 2);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isGreaterThan(6)
                .isNotZero()
                .isEven();
    }

    @Test
    void whenIsExistIsTrue() {
        Box box = new Box(8, 2);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue()
                .isNotNull();
    }

    @Test
    void whenIsExistIsFalse() {
        Box box = new Box(8, -5);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse()
                .isNotEqualTo(true);
    }

    @Test
    void getAreaTest1() {
        Box box = new Box(4, 3);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(15.6d, withPrecision(0.04d))
                .isCloseTo(16d, Percentage.withPercentage(10d));

    }

    @Test
    void getAreaTest2() {
        Box box = new Box(2, 3);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(0)
                .isLessThan(17d)
                .isGreaterThan(-2d);
    }
}