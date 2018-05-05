package dateFormat;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

public class EnumCombo<T> extends JComboBox<String> {
    private Map<String, T> table = new TreeMap<>();

    /**
     * Constructs an EnumCombo yielding values of type T.
     */
    public EnumCombo(Class<T> cl, String... labels) {
        for (String label : labels) {
            String name = label.toUpperCase().replace(' ', '_');
            try {
                java.lang.reflect.Field f = cl.getField(name);
                @SuppressWarnings("unchecked") T value = (T) f.get(cl);
                table.put(label, value);
            } catch (Exception e) {
                label = "(" + label + ")";
                table.put(label, null);
            }
            addItem(label);
        }
        setSelectedItem(labels[0]);
    }

    /**
     * Returns the value of the field that user selected.
     * @return
     */
    public T getValue() {
        return table.get(getSelectedItem());
    }
}
