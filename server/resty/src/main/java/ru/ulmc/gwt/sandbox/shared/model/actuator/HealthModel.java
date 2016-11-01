package ru.ulmc.gwt.sandbox.shared.model.actuator;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;

public class HealthModel implements IsSerializable {
    private static long commonID = 0;
    private Long id = commonID++;
    private String status;
    private DiskSpace diskSpace;

    public HealthModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DiskSpace getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(DiskSpace diskSpace) {
        this.diskSpace = diskSpace;
    }

    public static class DiskSpace implements IsSerializable {
        private String status;
        private String total;
        private String free;
        private String threshold;

        public DiskSpace() {
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getFree() {
            return free;
        }

        public void setFree(String free) {
            this.free = free;
        }

        public String getThreshold() {
            return threshold;
        }

        public void setThreshold(String threshold) {
            this.threshold = threshold;
        }
    }

    public interface HealthProperties extends PropertyAccess<HealthModel> {
        @Editor.Path("id")
        ModelKeyProvider<CityModel> key();

        ValueProvider<HealthModel, String> status();
    }
}
