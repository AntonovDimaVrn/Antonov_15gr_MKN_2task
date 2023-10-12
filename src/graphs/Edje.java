package graphs;

import java.util.Objects;

public class Edje {

    private Integer to;
    private Integer weight;

    public Edje(Integer to, Integer weight) {
        this.to = to;
        this.weight = weight;
    }

    public Edje(Integer to) {
        this.to = to;
        this.weight = 1;
    }

    public Integer getTo() {
        return to;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edje edje = (Edje) o;
        return Objects.equals(to, edje.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, weight);
    }
}
