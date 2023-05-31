package GUI.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.Comparator;
import java.util.Date;

public class TableSorter {
    public static final Comparator<Object> STRING_COMPARATOR = (Object o1, Object o2) -> {
        String s1 = (String) o1;
        String s2 = (String) o2;
        return s1.compareTo(s2);
    };
    
    public static final Comparator<Object> DATE_COMPARATOR = (Object o1, Object o2) -> {
        Date s1 = (Date) o1;
        Date s2 = (Date) o2;
        return s1.compareTo(s2);
    };

    public static final Comparator<Object> INTEGER_COMPARATOR = (Object o1, Object o2) -> {
        Integer i1 = (Integer) o1;
        Integer i2 = (Integer) o2;
        return i1.compareTo(i2);
    };

    public static final Comparator<Object> DOUBLE_COMPARATOR = (Object o1, Object o2) -> {
        Double d1 = (Double) o1;
        Double d2 = (Double) o2;
        return d1.compareTo(d2);
    };

    public static final Comparator<Object> VND_CURRENCY_COMPARATOR = (Object o1, Object o2) -> {
        String s1 = (String) o1;
        String s2 = (String) o2;

        String cleanO1 = s1.replaceAll("[^\\d]", "");
        String cleanO2 = s2.replaceAll("[^\\d]", "");

        if (cleanO1.isEmpty() && cleanO2.isEmpty()) {
            return 0;
        } else if (cleanO1.isEmpty()) {
            return -1;
        } else if (cleanO2.isEmpty()) {
            return 1;
        }

        Long n1 = Long.valueOf(cleanO1);
        Long n2 = Long.valueOf(cleanO2);

        return Long.compare(n1, n2);
    };

    public static void configureTableColumnSorter(JTable table, int columnIndex, Comparator<Object> comparator) {
        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        if (sorter == null) {
            sorter = new TableRowSorter<>(tblModel);
            table.setRowSorter(sorter);
        }
        sorter.setComparator(columnIndex, comparator);
    }
}

