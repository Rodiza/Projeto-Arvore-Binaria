package src;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.CoreUtils;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import arvores.ArvoreBinaria;
import br.com.davidbuzatto.jsge.math.Vector2;

/**
 * Demonstração visual de Arvore Binária
 * 
 * @author Rodrigo Costa Garcia, Davi Beli Rosa
 */
public class Main extends EngineFrame {
    
    private GuiButton adicionarElemento;
    //arve
    private ArvoreBinaria<Integer, String> arvore;
    private Vector2 posicao;
    
    
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
        arvore = new ArvoreBinaria<>();
        posicao = new Vector2(100, 200);
        
        arvore.put( 5, "cinco" );
        arvore.put( 2, "dois" );
        arvore.put( 10, "dez" );
        arvore.put( 15, "quinze" );
        arvore.put( 12, "doze" );
        arvore.put( 1, "um" );
        arvore.put( 3, "três" );
    }


    @Override
    public void update( double delta ) {
        
    }
    

    @Override
    public void draw() {
        drawNode(arvore, posicao);
        
    
    }
    
    private void drawNode(ArvoreBinaria<Integer, String> tree, Vector2 pos ){
        drawCircle(pos, 40, BLACK);
        drawText(tree.get(5), pos, PINK);
    }
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
