package pdfsquire;

public enum DialogActions {
    EXTRACT {
        @Override
        public String toString() {
            return "Page range";
        }
    }, SPLIT {
        @Override
        public String toString() {
            return "Page range";
        }
    }, ROTATE_LEFT {
        @Override
        public String toString() {
            return "Page number";
        }
    }, ROTATE_RIGHT {
        @Override
        public String toString() {
            return "Page number";
        }
    }
}
