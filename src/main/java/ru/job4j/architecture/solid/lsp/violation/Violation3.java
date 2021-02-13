package ru.job4j.architecture.solid.lsp.violation;

public class Violation3 {
    class Null {
        public boolean checkNull(String ref) {
            return ref == null;
        }
    }

    class NotNull extends Null {
        @Override
        public boolean checkNull(String ref) {
            return ref != null;
        }

    }
}
