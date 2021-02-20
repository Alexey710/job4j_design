package ru.job4j.io.examination.additional;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShellTest {

    @Ignore
    @Test
    public void whenCdBack() {
        Shell shell = new Shell();
        shell.cd("/user/..");
        assertThat(
                shell.pwd(), is("/")
        );
    }

    @Ignore
    @Test
    public void whenCdRoot() {
        Shell shell = new Shell();
        shell.cd("/");
        assertThat(
                shell.pwd(), is("/")
        );
    }

    @Ignore
    @Test
    public void whenCdUserLocal() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("local");
        assertThat(
                shell.pwd(), is("/user/local/")
        );
    }

    @Ignore
    @Test
    public void whenCdUserBack() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("..");
        assertThat(
                shell.pwd(), is("/")
        );
    }

    @Ignore
    @Test
    public void whenCdForward3UserBack1() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("user");
        shell.cd("user");
        shell.cd("..");
        assertThat(
                shell.pwd(), is("/user/user/")
        );
    }

    @Ignore
    @Test
    public void whenCdUserBackOnly() {
        Shell shell = new Shell();
        shell.cd("..");
        assertThat(
                shell.pwd(), is("/")
        );
    }
}