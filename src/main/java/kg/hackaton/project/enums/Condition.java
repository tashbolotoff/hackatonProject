package kg.hackaton.project.enums;

public enum Condition {
    Хорошее, Среднее, Евроремонт, ТРЕБУЕТРЕМОНТА, СВОБОДНАЯПЛАНИРОВКА, ЧЕРНАЯОТДЕЛКА, Недостроено, ПОДСАМООТДЕЛКУПСО;

    public String getSecondValue(Condition condition) {
        if (condition.equals(Condition.ТРЕБУЕТРЕМОНТА)) {
            return "Требует ремонта";
        }
        else if (condition.equals(Condition.СВОБОДНАЯПЛАНИРОВКА)) {
            return "Свободная планировка";
        }
        else if (condition.equals(Condition.ЧЕРНАЯОТДЕЛКА)) {
            return "Черная отделка";
        }
        else if (condition.equals(Condition.ПОДСАМООТДЕЛКУПСО)) {
            return "Под самоотделку (псо)";
        }
        return "";
    }
}
