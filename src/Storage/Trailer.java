package Storage;

import Enumerations.Configuration;
import Utilities.Stack;

public class Trailer {
    private final TrailerLayer trailerLayer;
    private int currentLayer = 1;

    public Trailer() {
        trailerLayer = new TrailerLayer();
    }

    public TrailerLayer getTrailerLayer() {
        return trailerLayer;
    }

    public void push(Pallet pallet) {
        if (currentLayer == 1) {
            boolean layerFull = true;
            boolean done = false;

            for (int x = 0; x < trailerLayer.getPalletLayer1().length; x++) {
                for (int y = 0; y < trailerLayer.getPalletLayer1()[x].length; y++) {
                    if (trailerLayer.getPalletLayer1()[x][y] == null) {
                        trailerLayer.getPalletLayer1()[x][y] = pallet;
                        done = true;
                        layerFull = false;
                        break;
                    }
                }
                if (done) {
                    break;
                }
            }
            if (layerFull) {
                currentLayer = 2;
            }
        } else if (currentLayer == 2) {
            boolean layerFull = true;
            boolean done = false;

            for (int x = 0; x < trailerLayer.getPalletLayer2().length; x++) {
                for (int y = 0; y < trailerLayer.getPalletLayer2()[x].length; y++) {
                    if (trailerLayer.getPalletLayer2()[x][y] == null) {
                        trailerLayer.getPalletLayer2()[x][y] = pallet;
                        done = true;
                        layerFull = false;
                        break;
                    } else {
                        layerFull = false;
                    }
                }
                if (done) {
                    break;
                }
            }
            if (layerFull) {
                throw new RuntimeException("Trailer ist voll!");
            }
        }
    }
}
