package src;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.CoreUtils;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import arvores.Arvore;

/**
 * Modelo de projeto básico da JSGE.
 * 
 * JSGE basic project template.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class Main extends EngineFrame {
    
    private GuiButton adicionarElemento;
    
    
    public Main() {
        
        super(
            800,                 // largura                      / width
            450,                 // algura                       / height
            "Visualizador de Árvore Binária",      // título                       / title
            60,                  // quadros por segundo desejado / target FPS
            true,                // suavização                   / antialiasing
            false,               // redimensionável              / resizable
            false,               // tela cheia                   / full screen
            false,               // sem decoração                / undecorated
            false,               // sempre no topo               / always on top
            false                // fundo invisível              / invisible background
        );
        
    }
    

    @Override
    public void create() {
        
    }


    @Override
    public void update( double delta ) {
        
    }
    

    @Override
    public void draw() {
        
        
    
    }
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
