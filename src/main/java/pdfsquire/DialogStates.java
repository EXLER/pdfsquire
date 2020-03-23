package pdfsquire;

public enum DialogStates {
    EXTRACT {
        @Override
        public String toString() {
            return "Page range";
        }
    }, SPLIT, ROTATE_LEFT, ROTATE_RIGHT
}
