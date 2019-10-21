package dataAccessLayer;

import javax.swing.*;
import java.lang.reflect.Field;

public class AbstractDAO {

    public static void viewAllObjects(Object[] objects, JFrame viewObjectsJF, int nrOfRows) {
        String[][] rowData;
        String[] columnNames;

        int columnNumber = 0;
        Object auxObject = objects[1];
        for (Field field : auxObject.getClass().getDeclaredFields()) {
            columnNumber++;
        }
        columnNames = new String[columnNumber];
        rowData = new String[nrOfRows][columnNumber];

        int index = 0;
        for (Field field : auxObject.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            columnNames[index] = field.getName();
            index++;
        }

        for (int i = 1; i <= nrOfRows; i++) {
            int j = 0;
            for (Field field : objects[i].getClass().getDeclaredFields()) {
                j++;
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(objects[i]);
                    rowData[i-1][j-1] = value.toString();
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                } catch (IllegalAccessException e) {
                    System.out.println(e);
                }
            }
        }

        JTable allObjectsJT = new JTable(rowData, columnNames);
        allObjectsJT.setBounds(40, 40, 400, 280);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(40,40,400,280);
        jScrollPane.getViewport().add(allObjectsJT, null);
        viewObjectsJF.add(jScrollPane);
    }
}
