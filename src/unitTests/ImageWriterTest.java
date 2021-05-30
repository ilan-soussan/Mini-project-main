package unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {


    @Test
    void writeToImage() {
        ImageWriter img = new ImageWriter("Image",800,500);
        for (int i=1;i<800;++i) {
            for (int j = 1; j < 500; j++) {
                if(j%50 ==0 || i%50==0)
                    img.writePixel(i,j, new Color(150,80,60));
                else
                img.writePixel(i,j, new Color(200,150,150));

            }
        }
        img.writeToImage();

    }

}