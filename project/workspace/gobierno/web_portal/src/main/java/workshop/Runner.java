package workshop;

// DAO
interface Foo<T> {
    T foo();

    String bar(T t);
}

public class Runner {
    public static void main(final String[] args) {
        final Bar bar = new Bar();
        System.out.println(bar.foo().otherField);
    }
}

// DaoImpl
abstract class ShittyImpl<T> implements Foo<T> {
    public abstract T baz(String s);
}

// DynamicActionForm
class DyAF {
    String filed;

    public void setField(final String of) {
        this.filed = of;
    }

    public String getFiled() {
        return filed;
    }
}

// Bean
class MBean extends DyAF {
    String otherField;

    public String getOtherField() {
        return otherField;
    }

    public void setOtherField(final String of) {
        this.otherField = of;
    }
}

class Bar extends ShittyImpl<MBean> {

    @Override
    public MBean foo() {
        final MBean bean = new MBean();
        bean.setField("Field BarMBean");
        bean.setOtherField("Other field BarMBean");
        return bean;
    }

    @Override
    public String bar(final MBean mBean) {
        return mBean.getClass().getName();
    }

    @Override
    public MBean baz(final String s) {
        final MBean bean = new MBean();
        bean.setField("Field BarMBean" + s);
        bean.setOtherField("Other field BarMBean" + s);
        return null;
    }
}