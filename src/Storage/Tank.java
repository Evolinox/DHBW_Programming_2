package Storage;

import Enumerations.Configuration;
import Enumerations.Tastes;

public class Tank {
    private char[][][] content = new char
            [Configuration.INSTANCE.tankDimensionsX]
            [Configuration.INSTANCE.tankDimensionsY]
            [Configuration.INSTANCE.tankDimensionsZ];

    public Tank() {
        fillTank();
    }

    public char[][][] getContent() {
        return content;
    }

    private void fillTank() {
        for (int x = 0; x < Configuration.INSTANCE.tankDimensionsX; x++) {
            for (int y = 0; y < Configuration.INSTANCE.tankDimensionsY; y++) {
                for (int z = 0; z < Configuration.INSTANCE.tankDimensionsZ; z++) {
                    content[x][y][z] = (char) 103;
                }
            }
        }
    }

    public char getGin(Tastes taste) {
        char gin = 0;
        boolean done = false;
        for (int x = Configuration.INSTANCE.tankDimensionsX - 1; x >= 0; x--) {
            for (int y = Configuration.INSTANCE.tankDimensionsY - 1; y >= 0; y--) {
                for (int z = Configuration.INSTANCE.tankDimensionsZ - 1; z >= 0; z--) {
                    if (content[x][y][z] == (char) 103) {
                        gin = content[x][y][z];
                        content[x][y][z] = 0;
                        done = true;
                        break;
                    }
                }
                if (done) {
                    break;
                }
            }
            if (done) {
                break;
            }
        }
        return gin;
    }
}
