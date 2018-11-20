package com.mockitolimits;

import com.mockitolimits.service.NodeService;
import com.mockitolimits.service.NodeServiceFcty;
import com.mockitolimits.service.NodeServiceUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author sei3
 * on 20/11/2018.
 */
class TestClusterNodes {


    private static final int NB_NODES = 10_000;


    @Test
    void highlightStaticReflexionMockError() {

        for (int i = 0; i < NB_NODES; i++) {
            final NodeService mockedService = Mockito.mock(NodeService.class);
            final NodeType nodeType;
            if (i % 2 == 0) {
                nodeType = NodeType.INDEX;
            } else {
                nodeType = NodeType.STORAGE;
            }
            Mockito.when(mockedService.getNodeType()).thenReturn(nodeType);

            setFieldValue(NodeServiceFcty.getInstance(), "nodeService", mockedService);

            setStaticFieldValue(NodeServiceFcty.class, "STATIC_NODE_SERVICE", mockedService);


            if (i % 10 == 0) {
                System.out.println("node " + i);
            }

            Assertions.assertEquals(nodeType, NodeServiceFcty.getInstance().getNodeService().getNodeType(), "failing static at " + i);
            Assertions.assertEquals(nodeType, NodeServiceFcty.getInstance().getStaticNodeService().getNodeType(), "failing static at " + i);

            Assertions.assertEquals(nodeType.name(), NodeServiceUtils.resovleNodeType(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/0", NodeServiceUtils.resovleNodeType0(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/1", NodeServiceUtils.resovleNodeType1(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/2", NodeServiceUtils.resovleNodeType2(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/3", NodeServiceUtils.resovleNodeType3(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/4", NodeServiceUtils.resovleNodeType4(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/5", NodeServiceUtils.resovleNodeType5(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/6", NodeServiceUtils.resovleNodeType6(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/7", NodeServiceUtils.resovleNodeType7(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/8", NodeServiceUtils.resovleNodeType8(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/9", NodeServiceUtils.resovleNodeType9(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/10", NodeServiceUtils.resovleNodeType10(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/11", NodeServiceUtils.resovleNodeType11(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/12", NodeServiceUtils.resovleNodeType12(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/13", NodeServiceUtils.resovleNodeType13(), "failing at " + i);
            Assertions.assertEquals(nodeType + "/14", NodeServiceUtils.resovleNodeType14(), "failing at " + i);

            Assertions.assertEquals(nodeType, NodeServiceFcty.getInstance().getStaticNodeService().getNodeType(), "failing static at " + i);
            Assertions.assertEquals(nodeType, NodeServiceFcty.getInstance().getStaticNodeService().getNodeType(), "failing static at " + i);
            Assertions.assertEquals(nodeType, NodeServiceFcty.getInstance().getStaticNodeService().getNodeType(), "failing static at " + i);


        }
    }

    private static void setStaticFieldValue(final Class obj, final String fieldName, final Object fieldValue) {
        try {
            final Field field = obj.getDeclaredField(fieldName);
            field.setAccessible(true);
            final Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error while setting field [" + fieldName + "] on object " + obj + " Message " + e.getMessage(), e);
        }
    }

    private static void setFieldValue(final Object obj, final String fieldName, final Object fieldValue) {
        try {
            final Field field = getAccessibleField(obj, fieldName);
            final Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            field.set(obj, fieldValue);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error while setting field [" + fieldName + "] on object " + obj + " Message " + e.getMessage(), e);
        }
    }

    private static Field getAccessibleField(final Object obj, final String fieldName) throws NoSuchFieldException {
        final Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

}