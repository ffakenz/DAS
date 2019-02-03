package util;

import ar.edu.ubp.das.src.jobs.consumo.forms.NotificationUpdateForm;
import beans.NotificationUpdate;
import org.junit.Test;
import utils.JsonUtils;

import static junit.framework.TestCase.assertNotNull;

public class DynamicActionFormSpec {

    @Test
    public void test_transform_empty_NotificationUpdate_to_empty_NotificationUpdateForm_success() throws IllegalAccessException {
        final NotificationUpdate update = new NotificationUpdate();
        final NotificationUpdateForm notificationUpdateForm = JsonUtils.transformer(update, NotificationUpdateForm.class);
        System.out.println("notificationUpdateForm " + notificationUpdateForm);
        assertNotNull(notificationUpdateForm);
    }

    @Test
    public void test_transform_not_empty_NotificationUpdate_to_not_empty_NotificationUpdateForm_success() throws IllegalAccessException {
        final String json = "{\"plan_id\":1,\"plan_estado\":\"en_proceso\",\"plan_fecha_alta\":\"2018-01-08 08:58:00.000\",\"plan_fecha_ultima_actualizacion\":\"2018-12-09 11:42:13.287\",\"plan_tipo_de_plan\":\"GOBIERNO-INCENTIVO-2018\",\"cuota_nro_cuota\":1,\"cuota_fecha_vencimiento\":\"2018-02-08 11:59:59.000\",\"cuota_monto\":100,\"cuota_fecha_pago\":\"2018-01-18 08:58:00.000\",\"cuota_fecha_alta\":\"2018-01-08 08:58:00.000\",\"cliente_documento\":1000,\"cliente_nombre\":\"AXIS\",\"cliente_apellido\":\"Puto\",\"cliente_nro_telefono\":\"4444444\",\"cliente_email\":\"axis.puto@gmail.com\",\"vehiculo_id\":1,\"vehiculo_tipo\":\"taxi\",\"vehiculo_nombre\":\"Corsa\",\"vehiculo_precio\":10000,\"vehiculo_marca\":\"chevrolet\",\"vehiculo_modelo\":\"v1\",\"vehiculo_color\":\"c1\"}";
        final NotificationUpdate update = JsonUtils.toObject(json, NotificationUpdate.class);
        final NotificationUpdateForm notificationUpdateForm = JsonUtils.transformer(update, NotificationUpdateForm.class);
        System.out.println("notificationUpdateForm " + notificationUpdateForm);
        assertNotNull(notificationUpdateForm);
    }
}
