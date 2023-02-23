package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ArgsNameTest {

    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit=", "-111=?msg=Exit=", "-t=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
        assertThat(jvm.get("111")).isEqualTo("?msg=Exit=");
        assertThat(jvm.get("t")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeyDoesNotExist() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-=value"}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void wwhenValueDoesNotExist() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-key="}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenThereIsNoEqualSign() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-key="}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void wwhenThereIsNoLine() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"key=value"}))
                .isInstanceOf(IllegalArgumentException.class);
    }

}