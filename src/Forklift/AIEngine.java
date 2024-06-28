package Forklift;

import Enumerations.GPUModel;

public class AIEngine {
    private GPUModel gpuModel;

    public AIEngine() {
        gpuModel = GPUModel.EFFICIENCY;
    }
    public AIEngine(GPUModel gpuModel) {
        this.gpuModel = gpuModel;
    }
    public void switchGpuModel() {
        if (gpuModel == GPUModel.EFFICIENCY) {
            gpuModel = GPUModel.PERFORMANCE;
        } else if (gpuModel == GPUModel.PERFORMANCE) {
            gpuModel = GPUModel.EFFICIENCY;
        }
    }
    public GPUModel getGpuModel() {
        return gpuModel;
    }
}
