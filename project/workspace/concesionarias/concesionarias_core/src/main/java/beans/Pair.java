package beans;

import java.util.Objects;

public class Pair<A, B> {
    public final A fst;
    public final B snd;

    public Pair(final A var1, final B var2) {
        this.fst = var1;
        this.snd = var2;
    }

    public static <A, B> Pair<A, B> of(final A var0, final B var1) {
        return new Pair(var0, var1);
    }

    public String toString() {
        return "beans.Pair[" + this.fst + "," + this.snd + "]";
    }

    public boolean equals(final Object var1) {
        return var1 instanceof Pair && Objects.equals(this.fst, ((Pair)var1).fst) && Objects.equals(this.snd, ((Pair)var1).snd);
    }

    public int hashCode() {
        if (this.fst == null) {
            return this.snd == null ? 0 : this.snd.hashCode() + 1;
        } else {
            return this.snd == null ? this.fst.hashCode() + 2 : this.fst.hashCode() * 17 + this.snd.hashCode();
        }
    }
}