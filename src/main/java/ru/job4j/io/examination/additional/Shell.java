package ru.job4j.io.examination.additional;

import java.io.File;

public class Shell {
    private String current = new File(System.getenv("SystemDrive"))
            .getAbsolutePath().substring(0, 2) + "/";

    public  void cd(String userInput) {
        if (userInput.charAt(userInput.length() - 1) == '.'
                && userInput.charAt(userInput.length() - 2) == '.') {
            current = current.substring(0, current.length() - 1);
            int index = current.lastIndexOf("/");
            if (index == -1) {
                current = current + "/";
                return;
            }
            current = current.substring(0, index + 1);
            return;
        }
        if (userInput.equals("/")) {
            return;
        }

        current = current + userInput + "/";
    }

    public String pwd() {
        return current.substring(2);
    }

}
