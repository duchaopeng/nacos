package com.example.demo.design.pattern;

public enum DayEnum {
    Monday {
        @Override
        public String toDo() {
            return "今天上英语课";
        }
    },
    Tuesday {
        @Override
        public String toDo() {
            return "今天上语文课";
        }
    },
    Wednesday {
        @Override
        public String toDo() {
            return "今天上数学课";
        }
    },
    Thursday {
        @Override
        public String toDo() {
            return "今天上音乐课";
        }
    };

    public abstract String toDo();
}
